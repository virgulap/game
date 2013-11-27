package controllers


import util.Observable
import data.Snake

 class snakeController extends Observable {
  var snake1= new Snake(0)
  def initilise(x:Int) {
    snake1= new Snake(x)
    snake1.init
    notifyObservers
  }
}