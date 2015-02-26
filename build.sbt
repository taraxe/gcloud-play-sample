import NativePackagerKeys._
import com.typesafe.sbt.SbtNativePackager._

name := """gcloud-play-sample"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

maintainer in Docker := "Antoine Labbe antoine@taraxe.com"

dockerExposedPorts in Docker := Seq(8080)

bashScriptExtraDefines ++= Seq("addJava \"-Dhttp.port=8080\"")

stage in Docker <<= (stage in Docker, target) map { (result, target )=>
    IO.copyFile(file("conf/app.yaml"), target / "docker" / "app.yaml")
    result
}

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)
