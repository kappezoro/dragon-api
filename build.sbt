import com.github.play2war.plugin._

name := "dragon-api"

version := "1.0"

lazy val `dragon-api` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

Play2WarPlugin.play2WarSettings

Play2WarKeys.servletVersion := "3.0"