name := "neo4j"
 
version := "1.0"
 
scalaVersion := "2.10.3"
 
resolvers ++= Seq(
  "Neo4j-Contrib" at "http://m2.neo4j.org/content/groups/everything"
)

libraryDependencies += "org.neo4j" % "neo4j-rest-graphdb" % "2.0.1"

libraryDependencies += "org.neo4j" % "neo4j" % "2.1.1"

libraryDependencies += "junit" % "junit" % "4.8.1"

libraryDependencies += "edu.arizona.sista" % "processors" % "2.1"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.3"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.2.1-M3"
