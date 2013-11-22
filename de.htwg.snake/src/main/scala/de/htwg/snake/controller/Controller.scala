package de.htwg.snake.controller


import de.htwg.snake.util.Observable
import de.htwg.snake.data.snakeArray

 class snakeController extends Observable {
  var snake1= new snakeArray(0)
  def initilise(x:Int) {
    snake1= new snakeArray(x)
    snake1.createFirst
    snake1.status="initialised"
    notifyObservers
  }
  def quit {snake1.status="finished"; notifyObservers}
  def size = snake1.size+2
}