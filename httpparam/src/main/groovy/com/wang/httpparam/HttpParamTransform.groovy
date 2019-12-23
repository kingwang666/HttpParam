package com.wang.httpparam

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.wang.httpparam.mode.GradleConfig
import com.wang.httpparam.visitor.InsertMethodClassVisitor
import org.apache.commons.codec.digest.DigestUtils
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream
import java.util.zip.ZipEntry

/**
 * Author: wangxiaojie6
 * Date: 2019/3/21
 */
class HttpParamTransform extends Transform {

    private Project mProject
    private GradleConfig mConfig
    private boolean mFirst

    HttpParamTransform(Project project) {
        mProject = project
        mFirst = true
    }

    void setConfig(GradleConfig config) {
        mConfig = config
    }

    @Override
    String getName() {
        return getClass().getSimpleName()
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        if (mConfig.debug) {
            println "============================== transform start incremental is $transformInvocation.incremental ============================== "
            println ""
        }
        def time = System.currentTimeMillis()
        Collection<TransformInput> inputs = transformInvocation.inputs
        TransformOutputProvider outputProvider = transformInvocation.outputProvider
        boolean incremental
        if (mFirst){
            incremental = false
            mFirst = false
        }else {
            incremental = transformInvocation.incremental
        }
        //删除之前的输出
        if (!incremental) {
            outputProvider.deleteAll()
        }

        for (TransformInput input : inputs) {
            for (JarInput jar : input.getJarInputs()) {
                handleJarInputs(jar, outputProvider, incremental)
            }
            for (DirectoryInput directory : input.getDirectoryInputs()) {
                handleDirectoryInput(directory, outputProvider, incremental)
            }
        }

        if (mConfig.debug) {
            println ""
            println "==================================== transform end cost time: ${System.currentTimeMillis() - time} ===================================="
        }
    }

    /**
     * 处理文件目录下的class文件
     */
    void handleDirectoryInput(DirectoryInput directoryInput, TransformOutputProvider outputProvider, boolean incremental) {
        //获得产物的目录
        def dest = outputProvider.getContentLocation(directoryInput.name, directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
        if (!incremental) {
            //列出目录所有文件（包含子文件夹，子文件夹内文件）
            directoryInput.file.eachFileRecurse { File file ->
                if (file.isFile()) {
                    String name = file.name
                    String root = directoryInput.file.absolutePath
                    String classPath = file.absolutePath.substring(root.endsWith(File.separator) ? root.length() : (root.length() + 1))
                    if (filterClass(name, classPath)) {
                        FileInputStream is = new FileInputStream(file)
                        ClassReader cr = new ClassReader(is)
                        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor cv = new InsertMethodClassVisitor(Opcodes.ASM5, cw, mConfig)
                        cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        byte[] code = cw.toByteArray()
                        if (file.exists()) {
                            file.delete()
                        }
                        FileOutputStream fos = new FileOutputStream(
                                file.parentFile.absolutePath + File.separator + name)
                        fos.write(code)
                        is.close()
                        fos.close()
                    }
                }
            }
            FileUtils.copyDirectory(directoryInput.file, dest)
        } else {
            directoryInput.changedFiles.each { file, status ->
                String name = file.name
                String root = directoryInput.file.absolutePath
                String classPath = file.absolutePath.substring(root.endsWith(File.separator) ? root.length() : (root.length() + 1))
                File destFile = new File(dest, classPath)
                if (status == Status.REMOVED) {
                    destFile.delete()
                } else if (status == Status.CHANGED || status == Status.ADDED) {
                    if (filterClass(name, classPath)) {
                        FileInputStream is = new FileInputStream(file)
                        ClassReader cr = new ClassReader(is)
                        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                        ClassVisitor cv = new InsertMethodClassVisitor(Opcodes.ASM5, cw, mConfig)
                        cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        byte[] code = cw.toByteArray()
                        if (destFile.exists()) {
                            destFile.delete()
                        }
                        FileOutputStream fos = new FileOutputStream(destFile)
                        fos.write(code)
                        is.close()
                        fos.close()
                    } else {
                        if (destFile.exists()) {
                            destFile.delete()
                        }
                        FileUtils.copyFile(file, destFile)
                    }
                }
            }
        }

    }

    void handleJarInputs(JarInput jarInput, TransformOutputProvider outputProvider, boolean incremental) {
        if (!incremental || jarInput.status != Status.NOTCHANGED) {
            String jarName = jarInput.name
            File src = jarInput.file
            //重名名输出文件,因为可能同名,会覆盖
            String md5Name = DigestUtils.md5Hex(src.getAbsolutePath())
            if (jarName.endsWith(".jar")) {
                jarName = jarName.substring(0, jarName.length() - 4)
            }
            File dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)

            if (dest.exists()) {
                dest.delete()
            }

            JarFile jarFile = new JarFile(src)
            Enumeration enumeration = jarFile.entries()
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(dest))
            //用于保存
            while (enumeration.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) enumeration.nextElement()
                String classPath = jarEntry.getName()
                String name = classPath.split("/").last()
                ZipEntry zipEntry = new ZipEntry(classPath)
                InputStream inputStream = jarFile.getInputStream(jarEntry)
                jarOutputStream.putNextEntry(zipEntry)
                //插桩class
                if (filterClass(name, classPath)) {
                    ClassReader cr = new ClassReader(inputStream)
                    ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                    ClassVisitor cv = new InsertMethodClassVisitor(Opcodes.ASM5, cw, mConfig)
                    cr.accept(cv, ClassReader.EXPAND_FRAMES)
                    byte[] code = cw.toByteArray()
                    jarOutputStream.write(code)
                } else {
                    jarOutputStream.write(IOUtils.toByteArray(inputStream))
                }
                inputStream.close()
                jarOutputStream.closeEntry()
            }
            //结束
            jarOutputStream.close()
            jarFile.close()
        }

    }

    boolean filterClass(String name, String classPath) {
        if (!name.endsWith(".class")
                || name == "R.class"
                || name.startsWith("R\$")
                || name == "BuildConfig.class") {
            return false
        }
        for (String packageName : mConfig.exclude) {
            if (classPath.startsWith(packageName)) {
                return false
            }
        }
        return true
    }
}
