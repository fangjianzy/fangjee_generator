1、新建数据库
crate tabe xxx
2、修改数据库连接
[
	修改：config.properties
	必须配置：schema，jdbc_url，jdbc_username，jdbc_password
]
3、配置生成文件路径
修改DemoCode.java中相关配置属性
4、生成文件
执行DemoCode.java的main方法，会弹出生成文件
5、拷贝文件到所在工程路径下
拷贝文件即可
6、完成