/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.bankholidays.controllers

import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import uk.gov.hmrc.bankholidays.config.AppConfig
import uk.gov.hmrc.bankholidays.connector.WSProxyGet
import uk.gov.hmrc.play.bootstrap.controller.FrontendController

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

@Singleton
class IndexController @Inject()(
                                 client: WSProxyGet,
                                 appConfig: AppConfig,
                                 cc: MessagesControllerComponents
                               ) extends FrontendController(cc) {

  private lazy val url: String = {
    val url = appConfig.bankHolidaysUrl
    Logger.info(s"Proxying $url")
    url
  }

  def get: Action[AnyContent] = Action.async {
    implicit request =>
      client.GET(url).map(_.body).map { body =>
        Try(Json.parse(body))
          .map(Ok(_))
          .getOrElse(Ok(body).as("text/html"))
      }
  }

}
