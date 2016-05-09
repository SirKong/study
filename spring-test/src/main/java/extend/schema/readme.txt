实现基于spring-schema的扩展需要如下步骤：
	1.设计配置属性和JavaBean；
	2.编写XSD文件；
	3.编写NameSpaceHandler和BeanDefinitionParser完成解析工作；
	4.编写spring.handlers和spring.schemas串联所有组件；
	5.在spring-bean.xml文件中配置使用；