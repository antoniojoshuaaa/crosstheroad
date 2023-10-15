package project.view

import project.MainApp
import scalafxml.core.macros.sfxml

@sfxml
class RootLayoutController {
  def handleClose(): Unit = {
    MainApp.stage.close()
  }

  def handleHowToPlay(): Unit = {
    MainApp.showHowToPlayPage()
  }

}
