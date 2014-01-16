package de.htwg.snake
import de.htwg.snake.controller._
import de.htwg.snake.controller.snakeController;
import de.htwg.snake.gui._
import de.htwg.snake.tui._
/**
 * Running This object opens the snake game with its Textual and graphical interfaces
 * 
 */
object snake {
  val controller = new snakeController
  val gui = new guic(controller)
  val tui = new tuic(controller)
  var input:Char='c'
    /**
     * This method demonstrates the game itself. 
     * @param args Unused
     * @return Nothing
     */
  def main (args: Array[String]) {
	gui.register
    tui.register
    controller.notifyObservers
    while (controller.snake1.status!="finished") {
      try {
      input=readChar
	  }
	  catch {
	    case e:Throwable => input='p'
	  }
	  println("")
	  tui.TakeInput.send(input)
    }
  }
}