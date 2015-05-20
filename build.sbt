name := "spark-var"

version := "0.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.2.0" % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.2.0" % "provided"
)

libraryDependencies += "spark.jobserver" % "job-server-api" % "0.5.0" % "provided"

resolvers += "AkkaRepository" at "http://repo.akka.io/releases/"
resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"
