package main.scala.de.htwg.snake.tui

import main.scala.de.htwg.snake.controller._
import main.scala.de.htwg.snake.util.Observer

class takeInput(controller: snakeController){
  def send(d:Char){
  if (d=='d' || d=='s' || d=='a' || d=='w') controller.snake1.turn(d) else
  if (d=='q') controller.quit else
  controller.snake1.turn(controller.snake1.direction)
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
  def rg {
    controller.add(this)
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
    val y=controller.size
    for (q <- 0 until y) {
      for (w <- 0 until y) {
        if (controller.snake1.myMatrix(q)(w)==2) print("#") else
        if (controller.snake1.myMatrix(q)(w)==1) print("+") else
        if(controller.snake1.myMatrix(q)(w)>3) print("*") else
        if (controller.snake1.myMatrix(q)(w)==0) print(" ")
        }
        println("")
	    }
	    println("Input key")
	    
  }
  
}