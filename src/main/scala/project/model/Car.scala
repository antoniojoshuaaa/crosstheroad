package project.model

import scalafx.scene.image.Image

class Car (_direction: Boolean, _currScore: Int, _img: Image = new Image("project/image/car.png"))
  extends Vehicle (_img, _direction, _currScore) {

}