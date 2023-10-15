package project.view

import project.MainApp
import scalafx.scene.layout.StackPane
import scalafxml.core.macros.sfxml

import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`


@sfxml
class MainMenuController(private var stackPane: StackPane) {
  stackPane.children.insert(0, MainApp.gameMap)
  def handleStartGame(): Unit = {
    MainApp.showGameWindow()
  }
  def handleHowToPlayButton(): Unit = {
    MainApp.showHowToPlayPage()
  }
  def handleExitGame(): Unit = {
    MainApp.stage.close()
  }
}
