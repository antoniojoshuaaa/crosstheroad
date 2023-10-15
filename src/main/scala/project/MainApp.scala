package project

import javafx.{scene => jfxs}
import project.model.{Chicken, GameMap, GameWindow}
import project.view.GameController
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.{Image}
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import javafx.scene.input.KeyEvent

object MainApp extends JFXApp {
  var isStarted = false
  val numRows = 13
  private val mapPixelWidth = 600
  private val chickenImg = new Image("project/image/chicken.png")
  private val chicken: Chicken = new Chicken(mapPixelWidth, numRows, chickenImg)

  val gameMap: GameMap = new GameMap()

  val gameController = new GameController(gameMap, chicken, numRows)
  gameController.initializeMap()

  private val rootResource = getClass.getResource("view/RootLayout.fxml")
  private val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  private val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Cross the Road Game"
    scene = new Scene {
      root = roots
//      resizable = false
      onKeyPressed = (event: KeyEvent) => {
        gameController.handleKeyPressed(event)
      }
    }
  }

  def showGameWindow(): Unit = {
    gameController.gameLoop.start()
    isStarted = true
    val gameWindow = new GameWindow(gameMap, chicken)
    this.roots.setCenter(gameWindow)
  }

  def showMainMenu(): Unit = {
    isStarted = false
    val roots = loadRootFXML[jfxs.layout.AnchorPane]("view/MainMenu.fxml")
    this.roots.setCenter(roots)
  }

  def stopAnimationTimer(): Unit = {
    gameController.gameLoop.stop()
  }

  def showGameOverWindow(): Unit = {
    isStarted = false
    val roots = loadRootFXML[jfxs.layout.AnchorPane]("view/GameOverPage.fxml")
    this.roots.setCenter(roots)

  }

  def showHowToPlayPage(): Unit = {
    val roots = loadRootFXML[jfxs.layout.AnchorPane]("view/HowToPlayPage.fxml")
    this.roots.setCenter(roots)
  }

  private def loadRootFXML[T](resource: String): T = {
    val loader = new FXMLLoader(getClass.getResource(resource), NoDependencyResolver)
    loader.load()
    loader.getRoot[T]
  }

  showMainMenu()
}
