package main.scala.de.htwg.snake
import main.scala.de.htwg.snake.controller._
import main.scala.de.htwg.snake.gui._
import main.scala.de.htwg.snake.tui._

object snake {
  val controller = new snakeController
  val gui = new guic(controller)
  val tui = new tuic(controller)
  var input:Char='c'
  def main (args: Array[String]) {
	gui.rg
    tui.rg
    controller.notifyObservers
    while (true) {
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