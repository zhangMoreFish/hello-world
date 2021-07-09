API：https://spring.io/projects/spring-cloud
查看版本对应关系：
https://spring.io/projects/spring-cloud#learn，点击想要查看的版本后的Reference Doc，查看版本和各个组件的对应版本
查看当前最新版本：https://docs.spring.io/spring-cloud/docs/current/reference/html/
遇到的问题：
1.RestTemplate
Spring Boot<=1.3 无需定义，Spring Boot自动为您定义了一个,
Spring Boot >= 1.4 Spring Boot不再自动定义一个RestTemplate,
而是定义了一个RestTemplateBuilder允许您更好地控制所RestTemplate创建的对象
2.feign与openFeign
2.1.openFeign可以使用springMVC的注解，现在更多使用openFeign
2.2.@EnableFeignClients需要指定basePackages路径才能扫描feignClient注解，否则启动时可能报错(路径写到启动类!)
例：@EnableFeignClients(basePackages = "com.example.demo.*")
3.feignClient
3.1.feign传参必须写参数注解(@RequestParam("param")、@PathVariable("param")，并且value值不能缺省，即使名称与形参一致)
3.2.feignClient接口注入时编译器可能报错，不影响服务功能
4.版本问题
springboot版本2.5以上对应spring cloud G版以上openFeign，openFeign 3.0以上；并且openFeign已经依赖了ribbon，重复引用会导致冲突(nacos默认集成了ribbon)，需要exclude掉
5.spring-cloud-starter-netflix-eureka-server包含client
6.circuitbreaker：熔断
spring cloud 2020的熔断使用的是circuitbreaker，默认为关闭，配置中开启：feign.circuitbreaker.enabled=true
7.feign中使用fallback(熔断后执行方法)
spring-cloud-starter-openfeign版本一定要与spring cloud版本对应，openFeign中包含了circuitbreaker；
fallbackFactory比fallback多了失败原因，fallback处理类需要交给spring bean 管理
8.hystrix：熔断
需要@EnableHystrix注解开启熔断支持，触发熔断会执行@HystrixCommand注解中的fallbackMethod属性指定的方法(当前类中)
9.spring cloud 2020后的网关由zuul替换为gateway,引入gateway后默认启用，关闭：cloud.gateway.enable=false
10.gateway routes属性:id，自定义，不能重复；uri：路由到的地址，使用服务名时加前缀lb://；predicates：Path(匹配路径),Method(匹配请求方法类型),Cookie(匹配cookie)
11.组件依赖中容易有重复的引用导致冲突报错
