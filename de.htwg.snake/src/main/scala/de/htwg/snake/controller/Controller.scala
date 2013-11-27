package de.htwg.snake.controller


import de.htwg.snake.util.Observable
import de.htwg.snake.data.Snake

 class snakeController extends Observable {
  var snake1= new Snake(0)
  def initilise(x:Int) {
    snake1= new Snake(x)
    snake1.init
    notifyObservers
  }
}