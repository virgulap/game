package de.htwg.snake.tui

import de.htwg.snake.controller._
import de.htwg.snake.controller.snakeController;
import de.htwg.snake.util.Observer
/**
 * This class takes the input of the wanted direction form the tastature
 * @param the controller
 */
class takeInput(controller: snakeController){
  /**
   * This method facilitates the moving  of the snake being given @param the direction
   */
  def send(d:Char){
    if (d=='d' || d=='s' || d=='a' || d=='w') controller.turn(d) else
    if (d=='q') controller.snake1.status="finished" else
      controller.turn(controller.snake1.direction)
  }
}
/**
 * This class models the textual interface of the game
 * implements the interface Observer as to maintain the connection between view and the other layers
 * @param controller
 */
class tuic(controller: snakeController) extends Observer{
  val TakeInput = new takeInput(controller)
  /**
   * Notice the player at the end of the game and print the result
   */
  def end {
    println("")
    println("GAME OVER")
    print("You have "+controller.snake1.points+" points")
  }
  /**
   * register the observer to the subject
   */
  def register {
    controller.register(this)
  }
  /**
   * Initialisation of the game, calls the init method from controller
   */
  def init {
    println("How big do you want the field to be?")
//    val x:Int=readInt()
//    controller.initilise(x)
  }
  /**Evaluates the state of the game.
   */
  def decide {
    if(controller.snake1.status=="uninitialised") init else
    if(controller.snake1.status=="initialised") printer
  }
 
  def update = {
    decide
  }
  /**
   * Takes the status from the controller
   */
  def status:String = controller.snake1.status
  /**
   * Prints the textual interface: 
   * for the boundary it is posted #
   * for the snake it is posted *
   * for the food it is posted +
   */
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