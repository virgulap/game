package de.htwg.snake.wui

import de.htwg.snake.controller.snakeController
import de.htwg.snake.util.Observer
import play.api.mvc.{Action, WebSocket, Controller}
import play.api.libs.iteratee.{Concurrent, Iteratee}
import play.api.libs.concurrent.Execution.Implicits._


class WUI(controller: snakeController) extends Controller with Observer{
  var x=1;
  var m:String="update"
  var c:Char='f'
  val (out,channel) = Concurrent.broadcast[String]
  def register {
    controller.register(this)
  }
  def init(id:String) = Action {
    x=id.toInt
    controller.initilise(x)
    Ok(views.html.index(controller))
  }
  def update = {
    channel push(m)
    Ok(views.html.index(controller))
  }
  def socket = WebSocket.using[String] { request =>

    val in = Iteratee.foreach[String] {
      msg => {
        c=msg(0)
        controller.turn(c)
        controller.notifyObservers
       // channel push(msg)
      }
    }
    (in,out)
  }
}
