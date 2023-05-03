# HttpParam #


这是一个通过字节码插着帮你自动构建请求体的 Gradle插件。如果你需要IntelliJ IDEA 或者 Android Studio的插件，请移步到[OkHttpParamsGet](https://github.com/kingwang666/OkHttpParamsGet)  

这里不讲插件的实现方式。想了解字节码的插桩可看

- [教你用Java字节码做点有趣的事](https://juejin.im/post/5b51ff276fb9a04f914a922e)
- [如何理解 Transform API](https://www.jianshu.com/p/37df81365edf)
- [在AndroidStudio中自定义Gradle插件](https://blog.csdn.net/huachao1001/article/details/51810328)
- [你知道什么是Android中的函数插桩吗？](https://mp.weixin.qq.com/s/G3VS1GgCPSpzgH3WEfPP2Q)

个人感觉都是写的不错的博客


## 支持生成类型 ##
1. Map&lt;String, String&gt;  
2. Map&lt;String, Object&gt;
3. Map&lt;String, RequestBody&gt;
4. List&lt;MultipartBody.Part&gt;
5. MultipartBody.Builder

## 使用 ##

### 低版本Gradle ###

    buildscript {
        .
        .
        .
    	dependencies {
            .
            .
            .
    		classpath 'com.wang.httpparam:httpparam:1.2.1'  
    	}
    }


​     

    //annotations 
    implementation 'com.wang.httpparam:httpparam-annotation:1.2.1'

### Gradle7.0 ###

    buildscript {
        .
        .
        .
    	dependencies {
            .
            .
            .
    		classpath 'io.github.kingwang666:httpparam:3.0.1'
    	}
    }



    //annotations
    implementation 'io.github.kingwang666:httpparam-annotation:3.0.1'


你可以自定义一些参数

    apply plugin: 'com.wang.httpparam'
    
    httpParam {
    
        //是否开启日志
        debug = true
        //当参数为空时 是否已空字符串形式加入
        add = true
        //使用support的ArrayMap 还是 androidx的ArrayMap
        androidx = true
        //是否为okhttp 4.x版本
        okHttpV4 = true
    
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


文件上传结构体请参考[FileInput或者直接使用File类](https://github.com/kingwang666/HttpParam/blob/master/app/src/main/java/com/example/httpparam/FileInput.java)

使用注解@Params、@Parts或者@Body来标注类即可。支持多个一起  

	@Params(
	        type = ParamsType.BODY
	)
	@Body
	@Parts
	public class FileReq extends BaseReq {
	
	    @NonNull
	    public String name = "wang";
	
	    @ParamName("fakeAge")
	    public Integer age = 18;
	
	    @PostFile
	    public File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
	    
	} 

编译后生成的类  

	@Params(
	    type = ParamsType.BODY
	)
	@Body
	@Parts
	public class FileReq extends BaseReq {
	    @NonNull
	    public String name = "wang";
	    @ParamName("fakeAge")
	    public Integer age = 18;
	    @PostFile
	    public File file;
	
	    public FileReq() {
	        this.file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
	    }
	
	    public Map<String, RequestBody> getParams() {
	        Map<String, RequestBody> params = super.getParams();
	        params.put("name", RequestBody.create(MediaType.parse("text/plain"), this.name));
	        if (this.age != null) {
	            params.put("fakeAge", RequestBody.create(MediaType.parse("text/plain"), this.age.toString()));
	        }
	
	        if (this.file != null) {
	            params.put("file\"; filename=\"" + this.file.getName(), RequestBody.create(MediaType.parse(this.guessMimeType(this.file.getName())), this.file));
	        }
	
	        return params;
	    }
	
	    public Builder getBody() {
	        Builder params = super.getBody();
	        params.addFormDataPart("name", this.name);
	        if (this.age != null) {
	            params.addFormDataPart("fakeAge", this.age.toString());
	        }
	
	        if (this.file != null) {
	            params.addFormDataPart("file", this.file.getName(), RequestBody.create(MediaType.parse(this.guessMimeType(this.file.getName())), this.file));
	        }
	
	        return params;
	    }
	
	    public List<Part> getParts() {
	        List<Part> params = super.getParts();
	        params.add(Part.createFormData("name", this.name));
	        if (this.age != null) {
	            params.add(Part.createFormData("fakeAge", this.age.toString()));
	        }
	
	        if (this.file != null) {
	            params.add(Part.createFormData("file", this.file.getName(), RequestBody.create(MediaType.parse(this.guessMimeType(this.file.getName())), this.file)));
	        }
	
	        return params;
	    }
	}

ps：编译后的类可以到app\build\intermediates\transforms\HttpParamTransform目录下查看。<span style="color:red">**特别注意：如果涉及到文件上传并且没有指定MimeType，必须在基类中写一个`public String guessMimeType(String name)`方法**</span>。

### @Params ###

返回Map&lt;String, String&gt;, Map&lt;String, Object&gt;, Map&lt;String, RequestBody&gt;

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.CLASS)
	public @interface Params {
	
	    /**
	     * 方法名称
	     * @return 名称
	     */
	    String name() default "getParams";
	
	    /**
	     * 返回的参数类型 支持Map<String, String>{@link ParamsType#STRING}, 
	     * Map<String, Object>{@link ParamsType#OBJECT}, Map<String, RequestBody>{@link ParamsType#BODY}
	     * @return 参数类型
	     */
	    ParamsType type() default ParamsType.STRING;
	
	    /**
	     * 是否替换原有存在相同名称的方法
	     * @return true 替换
	     */
	    boolean replace() default false;
	
	    /**
	     * 是否需要父类方法返回参数加入到请求体
	     * @return true 不需要
	     */
	    boolean root() default false;
	
	}

### @Parts ###

返回List&lt;MultipartBody.Part&gt;

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.CLASS)
	public @interface Parts {
	
	    /**
	     * 方法名称
	     * @return 名称
	     */
	    String name() default "getParts";
	
	    /**
	     * 是否替换原有存在相同名称的方法
	     * @return true 替换
	     */
	    boolean replace() default false;
	
	    /**
	     * 是否需要父类方法返回参数加入到请求体
	     * @return true 不需要
	     */
	    boolean root() default false;
	}

### @Body ###

返回MultipartBody.Builder

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.CLASS)
	public @interface Body {
	
	    /**
	     * 方法名称
	     * @return 名称
	     */
	    String name() default "getBody";
	
	    /**
	     * 是否替换原有存在相同名称的方法
	     * @return true 替换
	     */
	    boolean replace() default false;
	
	    /**
	     * 是否需要父类方法返回参数加入到请求体
	     * @return true 不需要
	     */
	    boolean root() default false;
	}

### Field注解 ###

- @Ignore: 不添加进自动生成的请求参数中
- @PostFile: 文件参数
- @ParamName: 自定义请求参数名称
- <span style="color:#59ABFD"><S>@PostFiles: 多个文件参数</S> 已经移除请用@PostFile替代</span> 

