import play.core.PlayVersion.current
import play.sbt.PlayImport._
import sbt.Keys.libraryDependencies
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc"             %% "bootstrap-play-25"          % "4.9.0",
    "uk.gov.hmrc"             %% "govuk-template"             % "5.28.0-play-25",
    "uk.gov.hmrc"             %% "play-ui"                    % "7.32.0-play-25"
  )

  val scope = "test, it"

  val test = Seq(
    "com.github.tomakehurst"  %  "wiremock"                 % "2.21.0"        % scope,
    "com.typesafe.play"       %% "play-test"                % current         % scope,
    "org.mockito"             %  "mockito-core"             % "2.24.5"        % scope,
    "org.pegdown"             %  "pegdown"                  % "1.6.0"         % scope,
    "org.scalatest"           %% "scalatest"                % "3.0.4"         % scope,
    "org.scalatestplus.play"  %% "scalatestplus-play"       % "2.0.1"         % scope,
    "uk.gov.hmrc"             %% "hmrctest"                 % "3.5.0-play-25" % scope,
    "uk.gov.hmrc"             %% "http-verbs-test"          % "1.3.0"         % scope,
    "uk.gov.hmrc"             %% "service-integration-test" % "0.5.0-play-25" % scope
  )

}
