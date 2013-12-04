package controllers

import play.api.mvc.{Action, Controller}
import controllers._

object Application extends Controller {
  var snakeController = new snakeController
  def index = Action {
    Ok(views.html.index(snakeController))
  }
}
object gameController extends Controller{
  var snakeController = new snakeController
  var x=1;
  def init(id:String) = Action {
    x=id.toInt
    snakeController.initilise(x)
    Ok(views.html.index(snakeController))
  }
}