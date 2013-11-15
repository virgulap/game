package main.scala.de.htwg.snake.gui

import scala.swing._
import event._
import main.scala.de.htwg.snake.controller._
import main.scala.de.htwg.snake.util.Observer

class guic(controller: snakeController) extends Observer  {
  var frame = new MainFrame 
  var out= false
  def update {
    decide
  }
  def finish{
    frame.open
    frame.contents=new GridPanel(2,1){
      contents += new Label("Game Over")
      contents += new Label("You scored "+controller.snake1.points+" points")
    }
    frame.repaint()
    frame.pack()
    frame.size=new Dimension(200,200)
    timer1.stop()
  }
  def decide {
    if(controller.snake1.status=="uninitialised") {init; out=true} else
	if(controller.snake1.status=="initialised") ingame else {finish; out=true}
  }
  def ingame{
   
   val panel=new GridPanel(controller.size,controller.size) {
     listenTo(keys,mouse.moves,mouse.clicks)
     
     for (g <- 0 until controller.size; h <- 0 until controller.size) 
       if (controller.snake1.myMatrix(g)(h)==2) contents+= new Label("#") else
       if (controller.snake1.myMatrix(g)(h)==1) contents+= new Label("+") else
       if (controller.snake1.myMatrix(g)(h)>3) contents+= new Label("*") else
       contents+= new Label(" ")
   }
   panel.reactions += {
     case e:KeyPressed => {
       e.key match {
         case Key.W => {timer1.stop; controller.snake1.turn('w'); controller.notifyObservers; timer1.start}
         case Key.S => {timer1.stop; controller.snake1.turn('s'); controller.notifyObservers; timer1.start} 
         case Key.A => {timer1.stop; controller.snake1.turn('a'); controller.notifyObservers; timer1.start} 
         case Key.D => {timer1.stop; controller.snake1.turn('d'); controller.notifyObservers; timer1.start} 
         case Key.Up => {timer1.stop; controller.snake1.turn('w'); controller.notifyObservers; timer1.start} 
         case Key.Down => {timer1.stop; controller.snake1.turn('s'); controller.notifyObservers; timer1.start}  
         case Key.Left => {timer1.stop; controller.snake1.turn('a'); controller.notifyObservers; timer1.start} 
         case Key.Right => {timer1.stop; controller.snake1.turn('d'); controller.notifyObservers; timer1.start} 
         case Key.Q => controller.quit 
       }
     }
   }
   frame.contents=panel
   frame.open
   panel.requestFocus()
   timer1.start()
   frame.visible=true
   frame.repaint
   frame.pack
   frame.open
  }
  val timer1=new javax.swing.Timer(400,Swing.ActionListener(e => {controller.snake1.turn(controller.snake1.direction); controller.notifyObservers;}))
  def rg {
    controller.add(this)
  }
  def init {
    frame = new MainFrame {
	title = "SNAKE"
	menuBar = new MenuBar {
	  contents += new Menu("File") {
	    contents += new MenuItem(Action("Exit") {
	      sys.exit(0)
	    })
	  }
	}
	contents= new FlowPanel {
	  contents += new Label("How big do you want the field to be?")
	  contents += new Label("")
	  val nr = new TextField("10",2) {
	  }
	  contents +=nr
	  val btn = new Button("Start");
	  listenTo(btn)
	  contents+=btn
	  reactions += {
       case ButtonClicked(btn) => {
       controller.initilise(nr.text.toInt)
    }
	  }
	
    }
	   
	size=new Dimension(270,100)
	centerOnScreen
	}
	frame.visible=true
  }
}