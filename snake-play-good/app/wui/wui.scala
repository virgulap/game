package de.htwg.snake.wui

import de.htwg.snake.controller.snakeController
import de.htwg.snake.util.Observer
import play.api.mvc.{Action, WebSocket, Controller}
import play.api.libs.iteratee.{Iteratee, Enumerator}
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global


class WUI(controller: snakeController) extends Controller with Observer{
  var x=1;
  def register {
    controller.register(this)
  }
  def init(id:String) = Action {
    x=id.toInt
    controller.initilise(x)
    Ok(views.html.index(controller))
  }
  def update = {
    Ok(views.html.index(controller))
    socket
  }
  def socket = WebSocket.using[String] { request =>

  // Log events to the console
    val in = Iteratee.foreach[String](content => {
    println(content)
    })
    // Send a single 'Hello!' message
    val out = Enumerator("hello")

    (in, out)
  }
}
