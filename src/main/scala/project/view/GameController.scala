package project.view

import project.MainApp
import project.model.{Chicken, GameMap, GrassTile, RoadTile, Tile}
import scalafx.animation.AnimationTimer
import scalafx.scene.image.Image
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.text.Text
import scala.collection.convert.ImplicitConversions.`list asScalaBuffer`
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class GameController(gameMap: GameMap, chicken: Chicken, numRows: Int){
  private var roadInARow = 0
  private var grassInARow = 0
  private var newMap = true
  private val rand = new Random()
  private var lanes: ArrayBuffer[Tile] = ArrayBuffer.empty
  private var numLanes = 0
  private val roadImage = new Image("project/image/road.png")
  private val grassImage = new Image("project/image/grass.png")
  var score = 0
  var scoreText = new Text("0")
  val gameLoop: AnimationTimer = AnimationTimer { timer =>
    if (!newMap) {
      if (checkCollision()) {
        MainApp.showGameOverWindow()
      }
    }
  }

  def handleKeyPressed(event: KeyEvent): Unit = {
    if(MainApp.isStarted){
      event.code match {
        case KeyCode.Up =>
          moveChicken(event.code)
        case _ =>
      }
    }
  }

  private def moveChicken(direction: KeyCode): Unit = {
    direction.getName match {
      case "Up" =>
        addRow()
        score += 1
        scoreText.text = score.toString
        gameMap.mapPane.children.remove(gameMap.mapPane.children.size() - 1)
    }
  }

  def resetMap(): Unit = {
    gameMap.mapPane.getChildren.clear()
    score = 0
    scoreText = new Text(score.toString)
    numLanes = 0
    lanes = ArrayBuffer.empty
    newMap = true
  }

  def initializeMap(): Unit = {
    val newTile = new GrassTile("grass", grassImage)
    gameMap.mapPane.children.add(newTile)
    lanes += newTile
    for (_ <- 0 until numRows - 1) {
      addRow()
    }
    numLanes = 0
  }

  private def addRow(): Unit = {
    var newTile: Tile = null
    val num = rand.nextInt(6)
    val carType = rand.nextInt(4)
    if (roadInARow >= 4) {
      roadInARow = 0
      grassInARow += 1
      newTile = new GrassTile("grass", grassImage)
      gameMap.mapPane.children.insert(0, newTile)
    } else if (grassInARow > 3) {
      roadInARow += 1
      grassInARow = 0
      newTile = new RoadTile("road", roadImage, carType, score)
      gameMap.mapPane.children.insert(0, newTile)
    }
    else {
      num match {
        case x if x >= 1 && x <= 6 =>
          newTile = new RoadTile("road", roadImage, carType, score)
          gameMap.mapPane.children.insert(0, newTile)
        case _ =>
          newTile = new GrassTile("grass", grassImage)
          gameMap.mapPane.children.insert(0, newTile)
          grassInARow += 1
      }
    }

    if (num == 1 | num == 2 | num == 3 | num == 4 | num == 5 | num == 6) {
      roadInARow += 1
    } else roadInARow = 0
    lanes += newTile
    newMap = false
    if (lanes.length > 13) {
      lanes.remove(0)
    }
  }

  private def checkCollision(): Boolean = {
    val laneProp = lanes(0)
    val chickenImage = chicken.view

    if (lanes(0).tileType.equals("road")) {
      val car = laneProp.asInstanceOf[RoadTile].vehicle
      val carBounds = car.vehicleView.getBoundsInParent
      val chickenBounds = chickenImage.getBoundsInParent

      if (chickenBounds.getMaxX >= carBounds.getMinX && chickenBounds.getMaxX <= carBounds.getMaxX) {
        MainApp.stopAnimationTimer()
        return true
      }
      if (chickenBounds.getMinX <= carBounds.getMaxX && chickenBounds.getMinX >= carBounds.getMinX) {
        MainApp.stopAnimationTimer()
        return true
      }
    }
    false
  }
}
