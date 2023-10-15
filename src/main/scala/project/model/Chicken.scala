package project.model

import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.Pane

class Chicken (mapWidth: Int, numRows: Int, img: Image) extends Pane{

  private val initPositionx = mapWidth / 2 - 25
  private val initPositiony = (numRows - 1)*60
  val view: ImageView = new ImageView(img) {
    fitHeight = 50
    preserveRatio = true
  }
  view.layoutX = initPositionx
  view.layoutY = initPositiony

  children = List(view)
}
