Http Server
-----------
>Http server built using JDK 19 and virtual threads. See .jvmopts.

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