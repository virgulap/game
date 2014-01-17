package de.htwg.snake.controller

import org.specs2.mutable._
import scala.collection.mutable.ListBuffer

class ControllerTest extends Specification {
  
"initialisation" should {
      var snake3=new snakeController
      snake3.initilise(2)
    "initialise the game" in{
      snake3.snake1.snake must be_==(ListBuffer(List(1,1)))
      snake3.rx must be_!=(0)
      snake3.ry must be_!=(0)
      snake3.snake1.status must be_==("initialised")
      List(snake3.rx,snake3.ry) must be_!=(List(1,1))
    }
  }  
  	
   
   "move the snake into a free space" should {
    var snake4=new snakeController
    snake4.initilise(4)
    snake4.rx=2
    snake4.ry=2
    snake4.checkSpace(0,1)
    "remove the old state" in {
      snake4.snake1.snake must be_!=(ListBuffer(List(1,1)))
    }
    "add the new state" in {
      snake4.snake1.snake must be_==(ListBuffer(List(1,2)))
    }
  }
  "move the snake into food" should {
    var snake5=new snakeController
    snake5.initilise(2)
    snake5.rx=1
    snake5.ry=2
    snake5.checkSpace(0,1)  
    "have new state" in {
      snake5.snake1.snake must be_==(ListBuffer(List(1,1),List(1,2)))
    }
  }
    "move the snake into a wall" should {
    var snake6=new snakeController
    snake6.initilise(2)
    snake6.checkSpace(-1,0)
    "finish the game" in {
      snake6.snake1.status must be_==("finished")
    }
  }
  "move the snake into itself" should {
    var snake7=new snakeController
    snake7.initilise(3)
    snake7.rx=1
    snake7.ry=2
    snake7.checkSpace(0,1)
    snake7.rx=2
    snake7.ry=2
    snake7.checkSpace(1,0)
    snake7.rx=2
    snake7.ry=1
    snake7.checkSpace(0,-1)
    snake7.checkSpace(-1,0)
    "finish the game" in {
      snake7.snake1.status must be_==("finished")
    }
  }
  "turn the snake in a direction that is not the oposite to the old direction" should{
    var snake8=new snakeController
    snake8.initilise(3)
    snake8.snake1.direction='d'
    snake8.ry=2
    snake8.turn('s')
    "turn the snake in the new direction" in {
      snake8.snake1.snake must be_==(ListBuffer(List(2,1)))
    }
  }
  "turn the snake in a direction that is the oposite to the old direction" should{
    var snake9=new snakeController
    snake9.initilise(3)
    snake9.snake1.direction='d'
    snake9.rx=2
    snake9.turn('a')
    "turn the snake in the old direction" in {
      snake9.snake1.snake must be_==(ListBuffer(List(1,2)))
    }
  } 
  "food should" should{
    var snake2=new snakeController
    snake2.initilise(4)
    "have coord " in{
      snake2.rx must be_!=(0)
      snake2.ry must be_!=(0)
    }
  }
}