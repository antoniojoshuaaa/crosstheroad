package project.view

import project.MainApp
import scalafx.scene.layout.{Pane, StackPane}
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.{Black, White}
import scalafx.scene.text.{Font, FontWeight, Text}
import scalafxml.core.macros.sfxml

import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`

@sfxml
class GameOverController (private val gameOverPane: StackPane) {
  val finalScore = new Text(MainApp.gameController.score.toString)
  gameOverPane.children.insert(0, finalScore)
  gameOverPane.children.insert(0, MainApp.gameMap)
  finalScore.font = Font.font("Impact", FontWeight.Bold, 30)
  finalScore.fill = White
  finalScore.stroke = Black
  def handleRestartGame(): Unit = {
    MainApp.gameController.resetMap()
    MainApp.gameController.initializeMap()
    MainApp.showGameWindow()
  }

  def handleHomeButton(): Unit = {
    MainApp.gameController.resetMap()
    MainApp.gameController.initializeMap()
    MainApp.showMainMenu()
  }
}
