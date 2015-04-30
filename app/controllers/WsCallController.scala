package controllers

import play.api.libs.ws.{WSResponse, WS}
import play.api.mvc.{Controller, Action}
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
 * Ws calling test
 *
 * Created by tsujiokaatsuko on 2015/04/27.
 */
object WsCallController extends Controller{
  def execute = Action.async {

    val urls = Seq(
      "http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true",
      "http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true",
      "http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true",
      "http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true",
      "http://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver&mode=bicycling&language=fr-FR&sensor=false")
    val requests: Seq[Future[WSResponse]] = urls.map(WS.url(_).get())
    val futures = Future.sequence(requests).map { responses =>
      println(responses.map(_.body).mkString(","))
      Ok(responses.map(_.body).mkString(","))
    }

    futures recover {
      case e: java.net.ConnectException => NotFound(e.getMessage)
    }
  }
}
