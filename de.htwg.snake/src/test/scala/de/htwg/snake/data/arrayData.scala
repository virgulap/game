package de.htwg.snake.data
import org.specs2.mutable._
import de.htwg.snake.data._
class ArrayDataTest extends Specification{
  
" a new array " should{
  var snake1=new snakeArray(5)
  for(i<-0 to 6; j<-0 to 6){
"have value 0" in{
    snake1.myMatrix(i)(j) must beNull
 }}
"create first "should{
  snake1.createFirst
  "be #" in{
    snake1.myMatrix(0)(0) must be_==(2)
 }
  "be *"in {
    snake1.myMatrix(1)(1) must be_==(4)
  }
  "be ' '"in {
    snake1.myMatrix(2)(2) must be_==(0)
  }
  }
" small" should{
  snake1.findsmall
  "be 1.1" in {
    snake1.smallx must be_==(1)
    snake1.smally must be_==(1)
    snake1.myMatrix(1)(1) must be_==(4)
  }
} 
"increment" should{
  snake1.increment(4,1,1)
  "be 5" in {
    snake1.myMatrix(1)(1) must be_==(5)
  }
}
"big "should {
  snake1.findbig
  "be 1.1"in{
    snake1.bigx must be_==(1)
    snake1.bigy must be_==(1)
    snake1.myMatrix(1)(1) must be_==(5)
    }
}
}
}
