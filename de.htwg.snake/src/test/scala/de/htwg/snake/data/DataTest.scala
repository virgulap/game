package de.htwg.snake.data

import org.specs2.mutable._
class DataTest extends Specification {
	var snake=new Snake(3)
	"A new snake " should {
	  "have direction right" in {
	    snake.direction must be_==('d')
	  }
	  "have 0 points" in {
	    snake.points must be_==(0)
	  }
	  "be uninitialised" in {
	    snake.status must be_==("uninitialised")
	  }
	}
}