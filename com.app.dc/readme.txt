https://blog.51cto.com/u_15349616/4218544

protoc.exe：
3.12.0
https://repo1.maven.org/maven2/com/google/protobuf/protoc/
：
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

windows：gen_java.bat 


https://blog.51cto.com/u_15349616/4218544
