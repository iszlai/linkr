import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "linkr",
    libraryDependencies ++= Seq(
    "org.http4s" % "http4s-dsl_2.12" % "0.15.0a",
    "org.http4s" % "http4s-blaze-server_2.12" % "0.15.0a",
    "org.http4s" % "http4s-blaze-client_2.12" % "0.15.0a",
    "org.http4s" % "http4s-argonaut_2.12" % "0.15.0a",
    "org.slf4j" % "slf4j-simple" % "1.6.2",
    "io.argonaut" % "argonaut_2.12" % "6.1",
    "org.mindrot" % "jbcrypt" % "0.3m",
    "com.auth0" % "java-jwt" % "3.1.0",
    "org.tpolecat" % "doobie-core_2.12" % "0.3.0a",
    "org.tpolecat" % "doobie-contrib-h2_2.12" % "0.3.0a",
    "junit" % "junit" % "4.12",
    "org.scalatest" % "scalatest_2.12" % "3.0.1",
    "com.typesafe.slick" %% "slick" % "3.2.0-M2",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0-M2",
    "com.typesafe" % "config" % "1.2.1",
    "com.h2database" % "h2" % "1.3.175"
  )
  )

  //wartremoverErrors ++= Warts.unsafe
  

