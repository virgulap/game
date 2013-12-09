package de.htwg.snake.wui

import de.htwg.snake.controller.snakeController
import de.htwg.snake.util.Observer
import play.api.mvc.{Action, WebSocket, Controller}
import play.api.libs.iteratee.{Concurrent, Iteratee, Enumerator}
import play.api.libs.concurrent.Execution.Implicits._


class WUI(controller: snakeController) extends Controller with Observer{
  var x=1;
  val (out,channel) = Concurrent.broadcast[String]
  def register {
    controller.register(this)
  }
  def init(id:String) = Action {
    x=id.toInt
    controller.initilise(x)
    Ok(views.html.index(controller))
  }
  def update = Action {
    channel push ("update")
    Ok(views.html.index(controller))
  }
  def socket = WebSocket.using[String] { request =>

    val in = Iteratee.foreach[String] {
      msg => println(msg)
        channel.push(msg)
    }
    channel.push("pusssh")
    (in,out)
  }
}
