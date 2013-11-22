package de.htwg.snake.data

class snakeArray(val size:Int) {
	var myMatrix = Array.ofDim[Int](size+2,size+2)
	var direction: Char = 'd'
	var status:String = "uninitialised"
	var points:Int=0
	var rx=1
    var ry=1
    var rxx = new scala.util.Random
  def set(h:Int,k:Int) {
    if(h==1 && k==1) myMatrix(h)(k)=4 else 
    if (h==0) myMatrix(h)(k)=2 else 
    if (h==size+1) myMatrix(h)(k)=2 else 
    if (k==0) myMatrix(h)(k)=2 else 
    if(k==size+1) myMatrix(h)(k)=2 else 
    myMatrix(h)(k)=0
  }
  def createFirst {
    for (h <- 0 to size+1; k <- 0 to size+1 ) set(h,k)
    food
  }
  def food{
         rx=rxx.nextInt(size)+1
         ry=rxx.nextInt(size)+1
         ((myMatrix(rx)(ry)==0),((size)*(size)-1!=points)) match {
           case (true,true) => myMatrix(rx)(ry)=1
           case (false,false) =>status="finished"
           case _ => food
         }
      }
  var smallx:Int=1
  var smally:Int=1
  var bigx:Int=1
  var bigy:Int=1
  def findsmall{
    if (myMatrix(smallx-1)(smally)==4) smallx=smallx-1 else
    if (myMatrix(smallx+1)(smally)==4) smallx=smallx+1 else
    if (myMatrix(smallx)(smally+1)==4) smally=smally+1 else
    if (myMatrix(smallx)(smally-1)==4) smally=smally-1
  }
  def findbig {
    if (myMatrix(bigx-1)(bigy)==points+5) bigx=bigx-1 else
    if (myMatrix(bigx+1)(bigy)==points+5) bigx=bigx+1 else
    if (myMatrix(bigx)(bigy+1)==points+5) bigy=bigy+1 else
    if (myMatrix(bigx)(bigy-1)==points+5) bigy=bigy-1
  }
  def increment(inc:Int,a:Int,b:Int) {
    myMatrix(a)(b)=myMatrix(a)(b)+1
    if (myMatrix(a-1)(b)==inc+1) increment(inc+1,a-1,b) else
    if (myMatrix(a+1)(b)==inc+1) increment(inc+1,a+1,b) else
    if (myMatrix(a)(b+1)==inc+1) increment(inc+1,a,b+1) else
    if (myMatrix(a)(b-1)==inc+1) increment(inc+1,a,b-1)
  }
  def checkSpace(x:Int, y:Int) {
    findsmall
    increment(4,smallx,smally)
    if (myMatrix(smallx+x)(smally+y)==1) {
    myMatrix(smallx+x)(smally+y)=4
    points=points+1
    food
    } else
    if ((myMatrix(smallx+x)(smally+y)>3) || (smallx+x==size+1) || (smallx+x==0) || (smally+y==0) || (smally+y==size+1)) status="finished"
  	else {
  	myMatrix(smallx+x)(smally+y)=4
  	findbig
    myMatrix(bigx)(bigy)=0
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


