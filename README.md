# HttpParam #
httpparam  
[ ![Download](https://api.bintray.com/packages/kingwang666/maven/httpparam/images/download.svg?version=1.1.0) ](https://bintray.com/kingwang666/maven/httpparam/1.1.0/link)  
httpparam-annotation  
[ ![Download](https://api.bintray.com/packages/kingwang666/maven/httpparam-annotation/images/download.svg?version=1.1.0) ](https://bintray.com/kingwang666/maven/httpparam-annotation/1.1.0/link)  


这是一个通过字节码插着帮你自动构建请求体的 Gradle插件。如果你需要IntelliJ IDEA 或者 Android Studio的插件，请移步到[OkHttpParamsGet](https://github.com/kingwang666/OkHttpParamsGet)

    
    buildscript {
        .
        .
        .
		dependencies {
	        .
	        .
	        .
			classpath 'com.wang.httpparam:httpparam:1.1.0'  
		}
    }
    
     

    //annotations 
    implementation 'com.wang.httpparam:httpparam-annotation:1.1.0'


你可以自定义一些参数

    apply plugin: 'com.wang.httpparam'
	
	httpParam {
	
	    //是否开启日志
	    debug = true
	    //当参数为空时 是否已空字符串形式加入
	    add = true
	    //使用support的ArrayMap 还是 androidx的ArrayMap
	    androidx = true
	
	    file = [
	            //文件上传结构体key值参数名称 必须为public 类型String，默认为"key"
	            key     : "key",
	            //文件上传结构体filename值参数名称 必须为public 类型String，默认为"filename"
	            filename: "filename",
	            //文件上传结构体mimeType值参数名称 必须为public 类型String，默认为 null
	            mimeType: null,
	            //文件上传结构体data值参数名称 必须为public 类型java.io.File, 默认为"data"
	            data    : "data"
	    ]
	
	    //排除类
	    exclude = [
	            "com.wang.asmdemo.exclude"
	    ]
	}


文件上传结构体请参考[FileInput](https://github.com/kingwang666/HttpParam/blob/master/app/src/main/java/com/example/httpparam/FileInput.java)

## 支持生成类型 ##
1. Map&lt;String, String&gt;  
2. Map&lt;String, Object&gt;
3. Map&lt;String, RequestBody&gt;
4. List&lt;MultipartBody.Part&gt;
5. MultipartBody.Builder

## 使用 ##
使用注解@Params、@Parts或者@Body来标注类。支持多个一起  

    @Params(
        replace = true,
        type = ParamsType.STRING
	)
	@Parts
	@Body
	public class BaseTest {
	}