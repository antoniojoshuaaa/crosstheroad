package project.model

import scalafx.scene.image.Image

class Truck (_direction: Boolean, _currScore: Int, _img: Image = new Image("project/image/truck.png"))
  extends Vehicle (_img, _direction, _currScore) {

}