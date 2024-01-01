Http Server
-----------
>Http server and client built using JDK 21, virtual threads and Scala 3.

Server
------
>See Server.
1. sbt clean compile run

Client
------
>See Client.
1. curl -v http://localhost:7979/now

Reference
---------
1. [JDK Http Server and Virtual Threads](https://piotrminkowski.com/2022/12/22/java-http-server-and-virtual-threads/)
2. [JDK Http Server Benchmark](https://www.reddit.com/r/java/comments/18vysrr/jdk_http_server_handles_100000_reqsec_with_100_ms/)
3. [HttpServer Javadoc](https://download.java.net/java/early_access/panama/docs/api/jdk.httpserver/com/sun/net/httpserver/package-summary.html)
4. [Virtual Threads: JEPS 425](openjdk.org/jeps/425)
5. [Structured Concurrency: JEPS 428](openjdk.org/jeps/428)
6. [Loom](www.marcobehler.com/guides/java-project-loom)
7. [Virtual Threads](https://github.com/objektwerks/virtual.threads)
