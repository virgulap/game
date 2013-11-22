package de.htwg.snake.controller


import de.htwg.snake.util.Observable
import de.htwg.snake.data.snakeArray

 class snakeController extends Observable {
  var snake1= new snakeArray(0)
  def initilise(x:Int) {
    snake1= new snakeArray(x)
    snake1.status="initialised"
    snake1.food
    notifyObservers
  }
  def quit {snake1.status="finished"; notifyObservers}
}