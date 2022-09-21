# 指定的基础镜像
FROM openjdk:8
# 镜像维护者姓名或邮箱地址
MAINTAINER Jerry tcl1043@qq.com
# 指定容器挂载点到宿主机自动生成的目录，用于作为缓存目录
VOLUME /tmp
# 拷贝宿主机jar包到容器中，这里是将application-server.jar拷贝成app.jar【***修改这里的application-server.jar即可***】
ADD doubao_community-0.0.1-SNAPSHOT.jar /doubao.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# 暴露出端口（提示）
EXPOSE 8000
# 运行容器时执行的shell命令
ENTRYPOINT ["java", "-jar", "doubao.jar"]