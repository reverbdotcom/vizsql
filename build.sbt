name := "vizsql"

scalaVersion in ThisBuild := "2.12.4"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.2.0-SNAP10",
  "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % Test,
  "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4")
organization := "com.criteo"
version := "1.0.0"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/criteo/vizsql</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/criteo/vizsql.git</connection>
      <developerConnection>scm:git:git@github.com:criteo/vizsql.git</developerConnection>
      <url>github.com/criteo/vizsql</url>
    </scm>
    <developers>
      <developer>
        <name>Guillaume Bort</name>
        <email>g.bort@criteo.com</email>
        <url>https://github.com/guillaumebort</url>
        <organization>Criteo</organization>
        <organizationUrl>http://www.criteo.com</organizationUrl>
      </developer>
    </developers>
}
