package de.htwg.snake.controller
import org.specs2.mutable._
class ControllerTest extends Specification {
  "initialising " should{
    var snakeController=new snakeController
    snakeController.initilise(2)
    " make a snake of desired dimension " in{
      snakeController.snake1.size must be_==(2)
    }
    " set the status as initialised" in{
      snakeController.snake1.status must be_==("initialised")
    }
  }
}