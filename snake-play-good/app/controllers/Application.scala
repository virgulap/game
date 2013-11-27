package controllers

import play.api.mvc.{Action, Controller}
import controllers._

object Application extends Controller {
  var snakeController = new snakeController
  def index = Action {
    Ok(views.html.index(snakeController))
  }
}