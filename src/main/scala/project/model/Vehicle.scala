package project.model

import javafx.animation.Interpolator
import scalafx.animation.TranslateTransition
import scalafx.scene.image.{Image, ImageView}
import scalafx.util.Duration

import scala.util.Random

abstract class Vehicle (img: Image, val direction: Boolean, currScore: Int) {
  private val rand = new Random()
  val vehicleView = new ImageView(img)
  vehicleView.fitHeight = 50
  vehicleView.preserveRatio = true
  private var duration = Math.pow(currScore - Math.sqrt((rand.nextInt(2000) + 2000) * 80), 2) / 80
  if(currScore > 312) {
    duration = 800
  }
//    rand.nextInt(2001) + 2000 - (Math.pow(currScore)
  private val translate: TranslateTransition = new TranslateTransition(Duration(duration))

  if (direction) {
    vehicleView.rotate = 180
  }

  if (direction) {
    translate.toX = -350
    translate.fromX = 350 + vehicleView.fitWidth.toDouble
    translate.setCycleCount(Int.MaxValue)
    translate.setNode(vehicleView)
  }
  else {
    translate.fromX = -350
    translate.toX = 350 + vehicleView.fitWidth.toDouble
    translate.setCycleCount(Int.MaxValue)
    translate.setNode(vehicleView)
  }
  translate.jumpTo(Duration(duration/(rand.nextInt(10) + 1)))
  translate.interpolator = Interpolator.LINEAR
  translate.play()

}
