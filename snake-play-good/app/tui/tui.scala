package de.htwg.snake.tui

import de.htwg.snake.controller.snakeController
import de.htwg.snake.util.Observer

class takeInput(controller: snakeController){
  def send(d:Char){
    if (d=='d' || d=='s' || d=='a' || d=='w') controller.turn(d) else
    if (d=='q') controller.snake1.status="finished" else
      controller.turn(controller.snake1.direction)
    controller.notifyObservers
  }
}

class tuic(controller: snakeController) extends Observer{
  val TakeInput = new takeInput(controller)
  def finish {
    println("")
    println("GAME OVER")
    print("You have "+controller.snake1.points+" points")
  }
  def register {
    controller.register(this)
  }
  def init {
    println("How big do you want the field to be?")
    val x:Int=readInt()
    controller.initilise(x)
  }
  def decide {
    if(controller.snake1.status=="uninitialised") init else
    if(controller.snake1.status=="initialised") printer else
      finish
  }
  def update = {
    decide
  }
  def status:String = controller.snake1.status
  def printer {
    val y=controller.snake1.size+1
    for (q <- 0 to y) {
      for (w <- 0 to y) {
        if ((q==0)||(q==controller.snake1.size+1)||(w==0)||(w==controller.snake1.size+1)) print("#") else
        if ((q==controller.rx)&&(w==controller.ry)) print("+") else
        if(controller.snake1.snake.exists(List(q,w)==_)) print("*") else
          print(" ")
      }
      println("")
    }
    println("Input key")

  }

}