package project.model

import scalafx.animation.TranslateTransition
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.StackPane

abstract class Tile (val tileType: String, img: Image) extends StackPane {
  val translate = new TranslateTransition()
  private val tileImage = new ImageView(img)
  children.add(tileImage)
}
