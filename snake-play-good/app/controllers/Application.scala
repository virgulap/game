package controllers

import play.api.mvc.{Action, Controller}
import de.htwg.snake.controller._
import de.htwg.snake.wui._
import de.htwg.snake.tui._


object Application extends Controller {
  var snakeController = new snakeController
  var tui =new tuic(snakeController)
  var wui =new WUI(snakeController)
  def index = Action {
    wui.register
    tui.register
    Ok(views.html.index(snakeController))
  }

}
