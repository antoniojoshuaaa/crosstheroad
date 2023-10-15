package project.model

import scalafx.scene.layout.{AnchorPane, TilePane}
import scalafx.scene.shape.Rectangle

class GameMap() extends AnchorPane {
  private val rect = new Rectangle() {
    width = 600
    height = 2000
  }
  this.clip = rect
  val mapPane: TilePane = new TilePane() {
    prefColumns = 1
  }

  children.addAll(mapPane)
}
