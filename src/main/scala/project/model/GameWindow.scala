package project.model

import project.MainApp
import scalafx.geometry.Pos
import scalafx.scene.layout.{AnchorPane, HBox, StackPane}
import scalafx.scene.paint.Color.{Black, White}
import scalafx.scene.text.{Font, FontWeight, Text}

class GameWindow(gameMap: GameMap, chicken: Chicken) extends AnchorPane{
  private val hBox = new HBox()
  val scoreText: Text = MainApp.gameController.scoreText
  hBox.children.add(scoreText)
  hBox.alignment = Pos.TopCenter
  hBox.minWidth = 600
  hBox.maxWidth = 600
  hBox.minHeight = 780
  hBox.maxHeight = 780
  this.maxWidth = 600
  this.maxHeight = 780
  this.minWidth = 600
  this.minHeight = 780
  private val stackPane = new StackPane()
  stackPane.children.addAll(gameMap, chicken)
  scoreText.font = Font.font("Impact", FontWeight.Bold, 30)
  scoreText.fill = White
  scoreText.stroke = Black

  children.addAll(stackPane, hBox)

}
