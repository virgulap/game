package test.scala.de.htwg.snake.controller
import org.specs2.mutable._
import main.scala.de.htwg.snake.controller._
import main.scala.de.htwg.snake.util.Observer
import main.scala.de.htwg.snake.data._
class controller extends Specification{
  
" a new array " should{
  var snake1=new snakeArray(5)
  for(i<-0 to 9; j<-0 to 9)
"have value 0" in{
    snake1.myMatrix(i)(j)==null
 }
"create first "should{
  snake1.createFirst
  "be #" in{
    snake1.myMatrix(0)(0)==2
 }
  "be *"in {
    snake1.myMatrix(1)(1)==4
  }
  "be ' '"in {
    snake1.myMatrix(2)(2)==0
  }
  }
" small" should{
  snake1.findsmall
  "be 1.1" in {
    snake1.smallx==1
    snake1.smally==1
    snake1.myMatrix(1)(1)==4
  }
} 
"increment" should{
  snake1.increment(4,1,1)
  "be 5" in {
    snake1.myMatrix(1)(1)==5
  }
}
"big "should {
  snake1.findbig
  "be 1.1"in{
    snake1.bigx==1
    snake1.bigy==1
    snake1.myMatrix(1)(1)==5
    }
}
"create food"should{
  snake1.myMatrix(1)(2)=1
snake1.checkSpace(0,1)
"be 5"in {
    snake1.myMatrix(1)(1)==5
    }
 "be 4"in{
   snake1.myMatrix(1)(2)==4
 }
snake1.checkSpace(0,1)
"be 0"in{
  snake1.myMatrix(1)(1)==0
}
"be 5"in{
  snake1.myMatrix(1)(2)==5
}
"be 4"in{
  snake1.myMatrix(1)(3)==4
}
 }
}
}
