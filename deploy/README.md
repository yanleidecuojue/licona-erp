#### licona-erp-backend

##### 1.安装docker与docker-compose

[docker安装](https://docs.docker.com/engine/install/)

[docker-compose安装](https://docs.docker.com/compose/install/)

##### 2.安装mysql

```dockerfile
cd ./docker-compose/mysql
docker-compose up -d
```

user: root

password: licona-erp-mysql-password

##### 3.安装redis

```dockerfile
cd ./docker-compose/redis
docker-compose up -d
```

password:  licona-erp-redis-password

##### 4.安装nacos

```shell
cd ./docker-compose/nacos/
docker-compose -f example/standalone-derby.yaml up -d
```

nacos_user: nacos
nacos_password: nacos

grafana_user: admin

默认grafana_password: admin

登陆后修改为grafana_password: licona-erp-grafana-password

##### 5.安装elk日志组件

```pro
cd ./docker-compose/elk
docker-compose up -d
```

此时我们已经将elasticsearch,logstash,kibana安装成功

###### 为elasticsearch安装ik分词器

```
// 进入容器
docker exec -it elk-single-elasticsearch /bin/bash
// 使用bin目录下的elasticsearch-plugin install安装ik插件
bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.13.1/elasticsearch-analysis-ik-7.13.1.zip
// 再重启下容器
docker restart elk-single-elasticsearch

// 可以在插件目录下写入自己的词典
```







