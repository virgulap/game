package de.htwg.snake.data

class Snake(val size:Int) {
	var snake = new scala.collection.mutable.ListBuffer[List[Int]]
	var direction: Char = 'd'
	var status:String = "uninitialised"
	var points:Int=0
  var registered:Int=0;
}


