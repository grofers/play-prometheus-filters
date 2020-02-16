name := "play-prometheus-filters"
organization := "com.github.grofers"

version := "0.2.3"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    publishTo := {
      val nexus = "http://artifactory.retail.grofer.io:8081/"
      if (version.value.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "artifactory/libs-snapshot-local")
      else
        Some("releases" at nexus + "artifactory/libs-release-local")
    },
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    makePomConfiguration ~= { _.copy(configurations = Some(Seq(Compile, Runtime, Optional))) },
    pomExtra :=
      <url>https://github.com/grofers/play-prometheus-filters</url>
      <licenses>
        <license>
          <name>MIT License</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:grofers/play-prometheus-filters.git</url>
        <connection>scm:git:git@github.com:grofers/play-prometheus-filters.git</connection>
      </scm>
      <developers>
        <developer>
          <id>stijndehaes</id>
          <name>Stijn De Haes</name>
        </developer>
      </developers>

  )
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "io.prometheus"             % "simpleclient"          % "0.0.23",
  "io.prometheus"             % "simpleclient_servlet"  % "0.0.23"
)

libraryDependencies ++= Seq(
  "org.scalatestplus.play"    %% "scalatestplus-play"         % "2.0.0"     % Test,
  "org.mockito"               % "mockito-core"                % "2.7.22"    % Test
)