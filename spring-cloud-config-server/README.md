
Below urls will point to base dir if below prop is not present 
in application prop file

spring.cloud.config.server.git.search-paths= {application},{profile}

http://localhost:8888/limits-service/default
http://localhost:8888/limits-service/dev
http://localhost:8888/limits-service/qa

in above case  limits-service is the property file name and default/dev/qa are profiles


The HTTP service has resources in the following form:

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
For example:

curl localhost:8888/foo/development
curl localhost:8888/foo/development/master
curl localhost:8888/foo/development,db/master
curl localhost:8888/foo-development.yml
curl localhost:8888/foo-db.properties
curl localhost:8888/master/foo-db.properties


{application}, which maps to spring.application.name on the client side.

{profile}, which maps to spring.profiles.active on the client (comma-separated list).

{label}, which is a server side feature labelling a "versioned" set of config files.






https://docs.spring.io/spring-cloud-config/docs/current/reference/html/
