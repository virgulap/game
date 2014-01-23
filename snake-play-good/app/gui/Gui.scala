package de.htwg.snake.gui

import scala.swing._
import event._
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
      preferredSize=new Dimension(200,200)
    }
    frame.repaint()
    frame.pack()
    timer1.stop()
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
        g.setPaint(Color.red)
        g.fillOval((controller.ry-1)*15+1,(controller.rx-1)*15+1,15,15)
      }
    }
    panel.reactions += {
      case e:KeyPressed => {
        e.key match {
          case Key.W => {timer1.stop; controller.turn('w'); controller.notifyObservers}
          case Key.S => {timer1.stop; controller.turn('s'); controller.notifyObservers}
          case Key.A => {timer1.stop; controller.turn('a'); controller.notifyObservers}
          case Key.D => {timer1.stop; controller.turn('d'); controller.notifyObservers}
          case Key.Up => {timer1.stop; controller.turn('w'); controller.notifyObservers}
          case Key.Down => {timer1.stop; controller.turn('s'); controller.notifyObservers}
          case Key.Left => {timer1.stop; controller.turn('a'); controller.notifyObservers}
          case Key.Right => {timer1.stop; controller.turn('d'); controller.notifyObservers}
          case Key.Q => controller.snake1.status="finished"
        }
      }
    }
    panel.preferredSize = new Dimension(controller.snake1.size*15,controller.snake1.size*15)
    frame.contents=panel
    frame.open
    panel.requestFocus()
    timer1.start()
    frame.visible=true
    frame.repaint
    frame.pack
  }
  val timer1=new javax.swing.Timer(400,Swing.ActionListener(e => {controller.turn(controller.snake1.direction); controller.notifyObservers;}))
  def rg {
    controller.register(this)
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
        preferredSize=new Dimension(270,100)
        val btn = new Button("Start");
        listenTo(btn)
        contents+=btn
        reactions += {
          case ButtonClicked(btn) => {
            controller.initilise(nr.text.toInt)
          }
        }

      }

      centerOnScreen
    }
    frame.visible=true
  }
}