package project.view

import project.MainApp
import scalafx.scene.layout.AnchorPane
import scalafxml.core.macros.sfxml

import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`

@sfxml
class HowToPlayController (private val anchorPane: AnchorPane) {
  anchorPane.children.insert(0, MainApp.gameMap)
  def handleHomeButton(): Unit = {
    MainApp.showMainMenu()
  }
}
