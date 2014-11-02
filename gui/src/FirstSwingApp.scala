import scala.swing._

object FirstSwingApp extends App {
  def top = new MainFrame {
    title = "First Swing App"
    contents = new Button {
      text = "Click me"
    }
  }
  top.maximize
  top.visible_=(true)
}