package de.htwg.snake.gui

import scala.swing._
import event._
import de.htwg.snake.controller._
import de.htwg.snake.controller.snakeController;
import de.htwg.snake.util.Observer
import java.awt.{ Color, Graphics2D }

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
    ingame
  }
  def decide {
    if(controller.snake1.status=="uninitialised") {init; out=true} else
	if(controller.snake1.status=="initialised") ingame else {finish; out=true}
  }
  def ingame{
   
   val panel=new Panel {
     listenTo(keys,mouse.moves,mouse.clicks)
     override def paint(g:Graphics2D){
     g.setPaint(Color.green)
     controller.snake1.snake.foreach(coord => g.fillOval((coord(1)-1)*15+1,(coord(0)-1)*15+1,15,15))
     //g.setPaint(Color.red)
     //g.fillOval((controller.snake1.ry-1)*15+1,(controller.snake1.rx-1)*15+1,15,15)
     }
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
   frame.size = new Dimension(100,100)
   frame.open
   panel.requestFocus()
   timer1.start()
   frame.visible=true
   frame.repaint
   frame.pack
   frame.size = new Dimension(controller.snake1.size*15+2,controller.snake1.size*15+2)
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