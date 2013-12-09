package de.htwg.snake.controller


import de.htwg.snake.util.Observable
import de.htwg.snake.data.Snake
import scala.swing._



class snakeController extends Observable {
  val timer1=new javax.swing.Timer(400,Swing.ActionListener(e => {snake1.turn(snake1.direction); notifyObservers;}))
  var snake1= new Snake(0)
  var c:String=" "
  def initilise(x:Int) {
    snake1= new Snake(x)
    snake1.init
    notifyObservers
  }
  def check(q:Int, w:Int):String = {
        if ((q==snake1.rx)&&(w==snake1.ry)) c="+" else
        if(snake1.snake.exists(List(q,w)==_)) c="*" else
        c=" "
        return c
  }

}