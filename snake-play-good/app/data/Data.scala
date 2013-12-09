package de.htwg.snake.data

class Snake(val size:Int) {
	var snake = new scala.collection.mutable.ListBuffer[List[Int]]
	var direction: Char = 'd'
	var status:String = "uninitialised"
	var points:Int=0
	var rxx = new scala.util.Random
	var rx=1
    var ry=1
  def food{
         rx=rxx.nextInt(size)+1
         ry=rxx.nextInt(size)+1
         ((snake.exists(List(rx,ry)==_)),((size)*(size)-1!=points)) match {
           case (false,true) => {}
           case (true,false) =>status="finished"
           case _ => food
         }
      }
  def init{
    snake += List(1,1)
    food
    status="initialised"
  }
  def checkSpace(x:Int, y:Int) {
    if ((snake(points)(0)+x==rx)&&(snake(points)(1)+y==ry)) {
    snake += List(snake(points)(0)+x,snake(points)(1)+y)
    points=points+1
    food
    } else
    if ((snake.exists(List(snake(points)(0)+x,snake(points)(1)+y)==_)) || (snake(points)(0)+x==size+1) || (snake(points)(0)+x==0) || (snake(points)(1)+y==0) || (snake(points)(1)+y==size+1)) status="finished"
  	else {
  	snake += List(snake(points)(0)+x,snake(points)(1)+y)
    snake=snake.tail
    }
  }
  def turn(q:Char) {
    (q,direction) match {
      case ('w','w') => checkSpace(-1, 0)
      case ('w','a') => {checkSpace(-1, 0); direction=q}
      case ('w','d') => {checkSpace(-1, 0); direction=q}
      case ('w','s') => checkSpace(1, 0)
      case ('s','s') => checkSpace(1, 0)
      case ('s','d') => {checkSpace(1, 0); direction=q}
      case ('s','a') => {checkSpace(1, 0); direction=q}
      case ('s','w') => checkSpace(-1, 0)
      case ('a','a') => checkSpace(0, -1)
      case ('a','s') => {checkSpace(0, -1); direction=q}
      case ('a','w') => {checkSpace(0, -1); direction=q}
      case ('a','d') => checkSpace(0, 1)
      case ('d','d') => checkSpace(0, 1)
      case ('d','w') => {checkSpace(0, 1); direction=q}
      case ('d','s') => {checkSpace(0, 1); direction=q}
      case ('d','a') => checkSpace(0, -1)
    }
  }
}


