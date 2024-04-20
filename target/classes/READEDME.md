1. 三层架构: Controller  Service    Dao
    对应  1.请求与响应  2.数据处理  3.数据操作,访问与储存
2. 分层解耦合: 控制反转与依赖注入(接口多态)
    使用容器接收对象 IOC/DI 
    将需要管理的类加上注解 @Component {@Controller  @Service  @Repository} @Primary
    将需要注入依赖的变量加上注解 @Autowired
3. 请求与响应: 类@RestController  方法@RequestMapping("/")