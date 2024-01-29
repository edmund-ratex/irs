https://blog.51cto.com/u_15349616/4218544

protoc.exe版本：
3.12.0
https://repo1.maven.org/maven2/com/google/protobuf/protoc/

protoc-gen-grpc-java插件版本：
protoc-gen-grpc-java-1.30.1-windows-x86_32.exe
https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/

maven:
<dependencies>
		<dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-protobuf</artifactId>
        <version>1.30.2</version>
    </dependency>
    <dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-stub</artifactId>
        <version>1.30.2</version>
</dependency>

在window执行：gen_java.bat 会生成对应grpc代码


参考：https://blog.51cto.com/u_15349616/4218544
