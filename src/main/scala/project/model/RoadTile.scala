package project.model

import scalafx.scene.image.Image


class RoadTile (_tileType: String, _img: Image, carType: Int, currScore: Int) extends Tile(_tileType, _img) {
  var vehicle: Vehicle = null
  if (carType == 0) {
    vehicle = new Truck(true, currScore)
  }
  if (carType == 1) {
    vehicle = new Truck(false, currScore)
  }
  if (carType == 2) {
    vehicle = new Car(true, currScore)
  }
  if (carType == 3) {
    vehicle = new Car(false, currScore)
  }
  children.add(vehicle.vehicleView)
}
