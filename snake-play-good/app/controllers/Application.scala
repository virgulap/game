package controllers

import play.api.mvc.{Action, Controller}
import de.htwg.snake.controller._
import de.htwg.snake.wui._
import de.htwg.snake.tui._
import de.htwg.snake.gui._



object Application extends Controller {
  var snakeController = new snakeController
  var gui =new guic(snakeController)
  //var tui =new tuic(snakeController)
  var wui =new WUI(snakeController)

  def index = Action {
    if(snakeController.snake1.registered==0) {
      gui.rg
      wui.register
      //tui.register
      snakeController.snake1.registered=1;
      snakeController.notifyObservers
    }
    Ok(views.html.index(snakeController))
  }
  def socket = wui.socket
  def quit = Action {
  snakeController.quit
  Ok(views.html.index(snakeController))
  }
  def init(x:Int) = Action {
    snakeController.initilise(x)
    Ok(views.html.index(snakeController))
  }

}
