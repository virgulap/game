package de.htwg.snake.data
import org.specs2.mutable._

class DataTest extends Specification {
  " a new snake" should{
    var snake=new Snake(2)
    "be uninitialised " in {
      snake.status must be_==("uninitialised")
    }
    "have no points " in {
      snake.points must be_==(0)
    }
    "have no food" in {
      snake.rx must be_==(0)
      snake.ry must be_==(0)
    }
  }
  "food should" should{
    var snake2=new Snake(2)
    snake2.food
    "have coord " in{
      snake2.rx must be_!=(0)
      snake2.ry must be_!=(0)
    }
  }
  "initialisation" should {
      var snake3=new Snake(2)
      snake3.init 
    "initialise the game" in{
      snake3.status must be_==("initialised")
      snake3.snake(0) must be_==(List(1,1))
      snake3.rx must be_!=(0)
      snake3.ry must be_!=(0)
      List(snake3.rx,snake3.ry) must be_!=(List(1,1))
    }
  }  
  "move the snake into a free space" should {
    var snake4=new Snake(2)
    snake4.init 
    snake4.rx=2
    snake4.ry=2
    snake4.checkSpace(0,1)
    "remove the old state" in {
      snake4.snake(0) must be_!=(List(1,1))
    }
    "add the new state" in {
      snake4.snake(0) must be_==(List(1,2))
    }
  }
  "move the snake into food" should {
    var snake5=new Snake(2)
    snake5.init 
    snake5.rx=1
    snake5.ry=2
    snake5.checkSpace(0,1)
    "keep the old state" in {
      snake5.snake(0) must be_==(List(1,1))
    }
    "add the new state" in {
      snake5.snake(1) must be_==(List(1,2))
    }
  }
    "move the snake into a wall" should {
    var snake6=new Snake(2)
    snake6.init 
    snake6.checkSpace(-1,0)
    "finish the game" in {
      snake6.status must be_==("finished")
    }
  }
  "move the snake into itself" should {
    var snake7=new Snake(3)
    snake7.init
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
      snake7.status must be_==("finished")
    }
  }
  "turn the snake in a direction that is not the oposite to the old direction" should{
    var snake8=new Snake(3)
    snake8.init
    snake8.direction='d'
    snake8.ry=2
    snake8.turn('s')
    "turn the snake in the new direction" in {
      snake8.snake(0) must be_==(List(2,1))
    }
  }
  "turn the snake in a direction that is the oposite to the old direction" should{
    var snake9=new Snake(3)
    snake9.init
    snake9.direction='d'
    snake9.rx=2
    snake9.turn('a')
    "turn the snake in the old direction" in {
      snake9.snake(0) must be_==(List(1,2))
    }
  }
}