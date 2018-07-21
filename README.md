# cloc 

[![Build Status](https://travis-ci.org/adrianulbona/cloc.svg?branch=master)](https://travis-ci.org/adrianulbona/cloc)
[![Coverage Status](https://coveralls.io/repos/github/adrianulbona/cloc/badge.svg)](https://coveralls.io/github/adrianulbona/cloc)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.adrianulbona/cloc/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.adrianulbona/cloc)

This is a self-contained Java library able to do country localization based on [geohashes](https://en.wikipedia.org/wiki/Geohash). 

The project puts together [discretized borders](http://adrianulbona.github.io/2017/01/25/borders.html) in a [trie](https://en.wikipedia.org/wiki/Trie) data structure.

## Gradle

```groovy
compile 'io.github.adrianulbona:cloc:0.3.2'
```

## Maven
```xml
<dependency>
    <groupId>io.github.adrianulbona</groupId>
    <artifactId>cloc</artifactId>
    <version>0.3.2</version>
</dependency>
```
## Code sample - Java
```java
final CountryLocator countryLocator = CountryLocator.create();
final List<String> countries = countryLocator.locate("u10hb1"); // ["United Kingdom"]
``` 

## Code sample - Scala - Spark
```scala
val locator = spark.sparkContext.broadcast(CountryLocator.create())
val locate = udf { (geohash: String) => locator.value.locate(geohash).asScala }

val pointsDF : DataFrame = Seq(
  Point("u10hb1", 51.47, 0.00),
  Point("u33ff3", 52.52, 13.81)).toDF

pointsDF.withColumn("countries", locate($"geohash"))
  .show()
  
// +-------+-----+-----+----------------+
// |geohash|  lat|  lon|       countries|
// +-------+-----+-----+----------------+
// | u10hb1|51.47|  0.0|[United Kingdom]|
// | u33ff3|52.52|13.81|       [Germany]|
// +-------+-----+-----+----------------+
``` 
