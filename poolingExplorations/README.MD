
## Context

Often, there is a need for a application to fetch data from another service which is commonly done using a http request.
In springboot applications, one of the commonly used way to make a http request, is using [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html)
.By default, RestTemplate creates a new http connection for every request.

However, when the number of such requests is high, it might not be efficient to use the default implementation.
Instead, pooled http connections can be used. Reference for this: [PoolingHttpClientConnectionManager](http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/impl/conn/PoolingHttpClientConnectionManager.html)

#### The purpose of this project was to analyze and compare the performance of pooled and non-pooled http connections.

This folder contains three simple spring boot applications:
1. httpclients
2. httpclients-withpooling
3. httpclients-withoutpooling

httpclients exposes a API performanceStatsGet to fetch some CPU stats of the JVM process hosting the application.

httpclients-withpooling exposes a API getRemotePerformanceStats which when called, calls the performanceStatsGet API using pooled http connections using RestTemplate

httpclients-withpooling exposes a API getRemotePerformanceStats which when called, calls the performanceStatsGet API using RestTemplate which creates a new HttpConnection on every call.



## Testing reports:
For both pooled and non-pooled connections, the read timeout and connection timeout was configured to be 5 seconds.
Number of pooled connections for the case where pooling is used, is = 25.
All the three applications were hosted on docker containers using the docker-compose and the tool used to fire requests is Apache JMeter. The below reports summarize the results.

### Report

![Report Table](PoolingTableReport.png?raw=true)

![Report Table](PoolingErrorGraph.png?raw=true)

![Report Table](PoolingThroughputGraph.png?raw=true)

#### Conclusion: The conclusion that can be drawn based on the above reports is that the pooled connections yeild better results, especially when the load on the application is high.


## Installation:
To install this application, clone the project onto to your machine and navigate to the root folder of this project where docker-compose.yml file resides.
Then run the below command:
docker-compose up --build

## Usage:
Fire below API's:
localhost:8082/getRemotePerformanceStats -> For Testing non-pooled http connections using RestTemplate.

localhost:8083/getRemotePerformanceStats -> For Testing pooled http connections using RestTemplate.
