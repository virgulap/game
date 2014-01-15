package de.htwg.snake.controller




import de.htwg.snake.util.Observable
import de.htwg.snake.data.Snake
import scala.swing._

/**
 * This class inherites from  the Observable class of the Observer patter. 
 * It models the controller which is the binding between the tui, gui and the model. 
 * @param
 */

class snakeController extends Observable {
  val timer1=new javax.swing.Timer(400,Swing.ActionListener(e => {turn(snake1.direction); notifyObservers;}))
  var snake1= new Snake(0)
  var c:String=" "
  var rxx = new scala.util.Random
  var rx=1
  var ry=1
  /**
   * This method depicts the field game with 
   *  the snake initialy  moving from the first  coordinate (1,1) of the field
   *  and creates the food as a random value 
   *  @param dimension of the field
   */
  def initilise(x:Int) {
    snake1= new Snake(x)
    snake1.snake += List(1,1)
    food
    snake1.status="initialised"
    timer1.start()
    notifyObservers
  }
/**
 * This method models the food 
 * @return 
 */
  def food{
    rx=rxx.nextInt(snake1.size)+1
    ry=rxx.nextInt(snake1.size)+1
    ((snake1.snake.exists(List(rx,ry)==_)),((snake1.size)*(snake1.size)-1!=snake1.points)) match {
      case (false,true) => {}
      case (true,false) => {snake1.status="finished"; endObservers;}
      case _ => food
    }
  }
/**
 * This method models how the the snake is growing.
 * It decides if the snake has in front of it a wall or has to eat a food or change direction
 * @param the coordinates of the momentan localisation
 */
  def checkSpace(x:Int, y:Int) {
    if ((snake1.snake(snake1.points)(0)+x==rx)&&(snake1.snake(snake1.points)(1)+y==ry)) {
      snake1.snake += List(snake1.snake(snake1.points)(0)+x,snake1.snake(snake1.points)(1)+y)
      snake1.points=snake1.points+1
      food
    } else
    if ((snake1.snake.exists(List(snake1.snake(snake1.points)(0)+x,snake1.snake(snake1.points)(1)+y)==_)) || (snake1.snake(snake1.points)(0)+x==snake1.size+1) || (snake1.snake(snake1.points)(0)+x==0) || (snake1.snake(snake1.points)(1)+y==0) || (snake1.snake(snake1.points)(1)+y==snake1.size+1)) {snake1.status="finished"; endObservers; timer1.stop();}
    else {
      snake1.snake += List(snake1.snake(snake1.points)(0)+x,snake1.snake(snake1.points)(1)+y)
      snake1.snake=snake1.snake.tail
    }
  }
  /**
   * This method models the moving of the snake. It moves right , left, up , down, 
   * but it cant turn in the same direction in which already is
   * @param the input of a tast
   */
  def turn(q:Char) {
    (q,snake1.direction) match {
      case ('w','w') => {timer1.stop(); checkSpace(-1, 0); timer1.start(); notifyObservers}
      case ('w','a') => {timer1.stop(); checkSpace(-1, 0); snake1.direction=q; timer1.start(); notifyObservers}
      case ('w','d') => {timer1.stop(); checkSpace(-1, 0); snake1.direction=q; timer1.start(); notifyObservers}
      case ('w','s') => {timer1.stop(); checkSpace(1, 0); timer1.start(); notifyObservers}
      case ('s','s') => {timer1.stop(); checkSpace(1, 0); timer1.start(); notifyObservers}
      case ('s','d') => {timer1.stop(); checkSpace(1, 0); snake1.direction=q; timer1.start(); notifyObservers}
      case ('s','a') => {timer1.stop(); checkSpace(1, 0); snake1.direction=q; timer1.start(); notifyObservers}
      case ('s','w') => {timer1.stop(); checkSpace(-1, 0); timer1.start(); notifyObservers}
      case ('a','a') => {timer1.stop(); checkSpace(0, -1); timer1.start(); notifyObservers}
      case ('a','s') => {timer1.stop(); checkSpace(0, -1); snake1.direction=q; timer1.start(); notifyObservers}
      case ('a','w') => {timer1.stop(); checkSpace(0, -1); snake1.direction=q; timer1.start(); notifyObservers}
      case ('a','d') => {timer1.stop(); checkSpace(0, 1); timer1.start(); notifyObservers}
      case ('d','d') => {timer1.stop(); checkSpace(0, 1); timer1.start(); notifyObservers}
      case ('d','w') => {timer1.stop(); checkSpace(0, 1); snake1.direction=q; timer1.start(); notifyObservers}
      case ('d','s') => {timer1.stop(); checkSpace(0, 1); snake1.direction=q; timer1.start(); notifyObservers}
      case ('d','a') => {timer1.stop(); checkSpace(0, -1); timer1.start(); notifyObservers}
      case (_,_) => {}
    }
  }
}