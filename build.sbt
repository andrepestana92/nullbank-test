lazy val root = (project in file(".")).
  settings(
    name := "nullbank",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies += "com.assembla.scala-incubator" %% "graph-core" % "1.10.1"
  )
