Http Server
-----------
>Http server built using JDK 19 and virtual threads.

JDK 19
------
See .jvmopts.

Build
-----
1. sbt clean compile

Test
----
1. sbt clean test

Server
------
1. sbt clean compile run

Client
------
1. curl -v http://localhost:7979/now

Reference
---------
1. [Java HTTP Server and Virtual Threads] (https://piotrminkowski.com/2022/12/22/java-http-server-and-virtual-threads/)