# Distributed Tracing:

## WHY:
With microservice one more problem is to be tracing a request. Since one request might go through diff MS in that case it is difficult to trace the request across Services/multi-threaded env.
In monolith we use to get the thread name and search that, meaning all log with this thread represent the same request for that time span.
But in MS it will go through different thread in different services to tracing with thread name will not be helpful.

###Solution:
Let’s consider if we can generate one ID and pass it across services for each request. Meaning we will generate one traceId that will flow with the request across service till the end of that request. In that case we can use that traceId to find the log for request.

##Now another issue will be as it will go thru diff MS and all will have the same ID then how to differentiate the log from MS specific, I mean how will know which log is from what MS belongs to.
###Solution:
We can have one more span-id that will be created every time when req flows to new MS and it will be always associated with parent trace Id. 
So finally, we will have [traceId (scope request), spanId (for MS)]
And, within request in same MS we need to segregate some part of process to track the log for that part, we can create new span for that process. In that case we can easily identify the part of request process.

##How: Spring provides library to generate the traceId and spanId related feature called sleuth.

##Spring–sleuth:
It has Tracer to manage the traceId and append this with log level(internal).
Ex: [Service Name, TraceId, SpanId]
2023-05-28 12:48:45.943 INFO [sleuth-zipkin,b8014fd9cadc055c,b8014fd9cadc055c] 38356 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
 ** To enable this, we have to add dependency as:
<dependency>
    		<groupId>org.springframework.cloud</groupId>
   		<artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>


This will enable the sleuth feature. It will add traceID and spanId to log.
*** With upgraded version of sleuth, it has inbuilt brave feature too.
“brave”: Opensource lib to achieve the same managing Tracer like Sleuth. It has some additional features too. So now both merged and provided together.
<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-sleuth-brave</artifactId>
</dependency>

Q.  How this is handled with async and multi-threaded cases.
In case of multi-threaded we need to customize impl to pass the tracing info.
Ex: @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor
         = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(1);
        threadPoolTaskExecutor.initialize();

        return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
    }
the use of LazyTraceExecutor. This class comes from the Sleuth library and is a special kind of executor that will propagate our traceID to new threads and create new spanIds in the process.
For more details to Handle Async/schedule, etc. see below post:
https://www.baeldung.com/spring-cloud-sleuth-single-application

Q. How sleuth propagates the info for HTTP call to other services?
This uses header to propagate the context across services. We can customize this also.
Q. How sleuth add details to logger?
This overrides the logging property.
For details refer below
https://stackoverflow.com/questions/65846859/how-did-spring-cloud-sleuth-add-tracing-information-to-logback-log-lines

Terminology:
[application name, traceId, spanId, export]

Application name – This is the name we set in the properties file and can be used to aggregate logs from multiple instances of the same application.
TraceId – This is an id that is assigned to a single request, job, or action. Something like each unique user initiated web request will have its own traceId.
SpanId – Tracks a unit of work. Think of a request that consists of multiple steps. Each step could have its own spanId and be tracked individually. By default, any application flow will start with same TraceId and SpanId.
Export – This property is a Boolean that indicates whether or not this log was exported to an aggregator like Zipkin. Zipkin is beyond the scope of this article but plays an important role in analyzing logs created by Sleuth.


***** Notes*****:
spring-cloud-starter-zipkin is deprecated, you should not use it anymore. You can use spring-cloud-starter-sleuth and spring-cloud-sleuth-zipkin (3.x).
If you check the dependencies of spring-cloud-starter-zipkin you will see that it depends on spring-cloud-starter-sleuth and spring-cloud-sleuth-zipkin so it is pulling in Sleuth and Sleuth's Zipkin support (which pulls in Brave).
From the high-level point of view Sleuth is doing three things:

It provides an API abstraction for Tracing libraries (Brave is the default library under the hood, OTel is incubating, and you can implement your own tracing library integration)
Instruments other Spring Projects
Integrates with other components (e.g.: log correlation, Tomcat access log support, etc.)
Please see the docs: https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/htmlsingle/

https://docs.spring.io/spring-cloud-sleuth/docs/current/reference/htmlsingle/spring-cloud-sleuth.html
spring.sleuth.async.enable = false




##Zipkin
###WHY:  We discussed why we need sleuth. It is next step of that.
After sleuth we have log where each request has traceId and spanId that will represent part of that req processing.
Now to trace req we have to search that traceId to find out all related logs. It will be difficult find and club manually.
Solution: We can have a tool that we club all logs with same traceID and spanid show together.
How: Zipkin provides that capability it will get the log from sleuth and show it in easily understandable UI.

Install Step:
     Go to:  https://zipkin.io/pages/quickstart

 
OR get the docker image and run
OR:
Download jar 
from https://repo1.maven.org/maven2/io/zipkin/java/zipkin-server/2.12.9/
go to jar dir.:
run java -jar zipkin-server-2.12.9-exec.jar
After that go to port 9411
Here will see nice UI that has diff filter ex: serviceName, spanId,etc

 
Zipkin: SB2.x sleuth->brave ->Zipkin
SB3.x micrometer->open telemetry->Zipkin
Spring boot 3 has replaced sleuth with micrometer.
https://github.com/in28minutes/spring-microservices-v3/blob/main/v3-upgrade.md


