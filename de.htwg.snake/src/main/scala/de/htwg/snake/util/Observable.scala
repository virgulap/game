package de.htwg.snake.util


trait Observer {
  def update
  def end
}
class Observable {
  var subscribers:Vector[Observer] = Vector()
  def register(s:Observer) = subscribers=subscribers:+s
  def remove(s:Observer) = subscribers=subscribers.filterNot(o=>o==s)
  def notifyObservers = subscribers.foreach(o=>o.update)
  def endObservers = subscribers.foreach(o=>o.end)
}
