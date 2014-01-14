package de.htwg.snake.gui
import scala.swing._
import event._
import de.htwg.snake.controller.snakeController;
import de.htwg.snake.util.Observer
import java.awt.{ Color, Graphics2D }
/**
 * This class models the frame of the game as a window with decoration
 * @param instance of the controller
 * implements the interface Observer as to maintain the connection between view and the other layers
 */
class guic(controller: snakeController) extends Observer  {
  var frame = new MainFrame
  var out= false
  def update {
    decide
  }
  /**
   * Opens a panel which depicts as contents the message of finishing the game and the result.
   * It uses the instance of the swing class Gridpanel,panel that lays out its contents in a uniform grid.
   */
  def finish{
    frame.open
    frame.contents=new GridPanel(2,1){
      contents += new Label("Game Over")
      contents += new Label("You scored "+controller.snake1.points+" points")
    }
    /**
     * repaint is a function in the class GridPanel
     */
    frame.repaint()
    frame.pack()
    /**
     * Sets the wanted dimension of the finish panel 
     */
    frame.size=new Dimension(200,200)
    timer1.stop()
  }
  /**
   * Evaluates the state of the game and decides if it initialises , starts  or finishes the game
   * Calls the specific method for each stage
   */
  def decide {
    if(controller.snake1.status=="uninitialised") {init; out=true} else
    if(controller.snake1.status=="initialised") ingame else {finish; out=true}
  }
  /**
   * Models the visualisation of the things that have to be seen and facilitates the interecation of the user with what he sees
   */
  def ingame{

    val panel=new Panel {
      /**
       * method of the publisher class in scala swing,listen to the given publisher
       * specify which Components produce events of interest
       */
      listenTo(keys,mouse.moves,mouse.clicks)
      /**
       * method of  Graphics2D,which specifies the colors to render in device space.
       */
      override def paint(g:Graphics2D){
        g.setPaint(Color.green)
        controller.snake1.snake.foreach(coord => g.fillOval((coord(1)-1)*15+1,(coord(0)-1)*15+1,15,15))
        g.setPaint(Color.red)
        g.fillOval((controller.ry-1)*15+1,(controller.rx-1)*15+1,15,15)
      }
    }
    /**
     * reacts to the cursor control keys and to the w s a d for the directions of the snake
     */
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
    frame.contents=panel
    frame.open
    /**
     * allows component(panel) to set the focus to itself
     */
    panel.requestFocus()
    timer1.start()
    frame.visible=true
    frame.repaint
    frame.pack
    /**
     * sets the size of the field game depending on the desired size of the user 
     */
    frame.size = new Dimension(controller.snake1.size*15+2,controller.snake1.size*15+50)
  }
  /**
   * predefined timer that facilitates the snake to move
   */
  val timer1=new javax.swing.Timer(400,Swing.ActionListener(e => {controller.turn(controller.snake1.direction); controller.notifyObservers;}))
  def register {
    controller.register(this)
  }
  /**
   * Iniatialisation of the game, depincting various options as exit, menus
   */
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
      /**
       * opens a panel which depicts the initial information needed for starting the game
       */
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