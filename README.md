
WarO_Java
=========

a Java submission for War-O as a [code kata](https://en.wikipedia.org/wiki/Kata_(programming))

- February 2015 
- this project now uses Java 8 streams
- goals include: a functional style, immutable objects, minimal use of for-loops
- Spring's Java configuration is used to configure players
- update 2022: Java 8 [is ancient](https://howoldisjava8.today/en/)

To Build:
---------

download Gradle at http://gradle.org

useful commands:

- gradle clean test
- gradle run
- gradle build

See test output in ~build/reports/tests/index.html

See executable zip in ~/build/distributions

To Run:
---------

- configure src/main/java/org/peidevs/waro/config/Config.java
- gradle run

Rules:
---------

Rules are [here](Rules.md).
