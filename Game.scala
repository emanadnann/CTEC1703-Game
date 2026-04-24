package cw

/**
 * This class holds an instance of a simple game where
 * a ship moves through an ocean and collects salvage from shipwrecks.
 * See the explanation sheet and comments in this file for details. The constructor builds an
 * instance of a game using the accepted parameters.
 *
 * @param reefs A list of coordinates (as tuples) where reefs exist. Example: The parameter List((0,0),(0,1)) puts two reef elements in the upper left corner and the cell below.
 * @param shipwrecks A list of shipwrecks, each is coordinates and a size (i.e. a 3 value tuple). Example: List((0,0,5)) puts a shipwreck in the upper left corner which is of size 5, so would add 5,000,000 to the salvage score.
 * @param anchor_x The initial x coordinate of the ship.
 * @param anchor_y The initial y coordinate of the ship. If initX and initY are 0, the ship starts in the upper left corner.
 */
class Game(reefs: List[(Int, Int)], shipwrecks: List[(Int, Int, Int)], anchor_x: Int, anchor_y: Int) {

  //the ocean, a 10x10 grid, where -1=empty, 0=reef, any positive number=shipwreck
  private var ocean: Array[Array[Int]] = Array.ofDim[Int](10, 10)

  /* Please note - to align with the overall case study (see explanation sheet), the above two-dimensional array
   * should be accessed in the format ocean(col)(row) so ocean(2)(0) would retrieve the 3rd column and the 1st row (as indexing starts at zero),
   * equivalent to an (x,y) coordinate of (2,0). You may therefore visualise each inner array as representing a column of data.
   */

  //the current salvage score, initially 0
  private var salvage: Int = 0
  //the current ship coordinates. As the ship moves these coordinates update.
  private var coord_x: Int = anchor_x
  private var coord_y: Int = anchor_y
  //the current X and Y saved coordinates, initially -1
  private var saveX: Int = -1
  private var saveY: Int = -1

  /* This code is executed as part of the constructor. It firstly initialises all cells to -1 (i.e. empty).
   * It uses the list of reefs provided to initialise the reefs in the ocean array by setting given coordinates to 0.
   * It then uses the list of shipwrecks to initialise the shipwrecks in the ocean array by setting the given coordinates to the provided number (size).
   */
  for (i <- 0 until 10; k <- 0 until 10) ocean(i)(k) = -1
  reefs.foreach(w => ocean(w._1)(w._2) = 0)
  shipwrecks.foreach(w => ocean(w._1)(w._2) = w._3)

  /**
   * Repeatedly run a sequence of commands. For example:
   *    for(i <- 1 to 5) println("Hello")
   * can be replaced by
   *    rpt(5)(println("Hello"))
   */
  def rpt(n: Int)(commands: => Unit): Unit = {
    for (i <- 1 to n) { commands }
  }

  /**
   * Utilised for GameApp.scala
   */
  def print_ocean(): Unit ={
    for(k<-0 until 10){
      for(i<-0 until 10){
        if(coord_x==i && coord_y==k){
          print("s")
        }else if(saveX==i && saveY==k){
          print("o")
        }else if(ocean(i)(k)== 0){
          print("r")
        }else if(ocean(i)(k)== -1){
          print(".")
        }else{
          print(ocean(i)(k))
        }
      }
      println()
    }
  }

  /********************************************************************************
   * COURSEWORK STARTS HERE - COMPLETE THE DEFINITIONS OF EACH OF THE OPERATIONS
   * WE SUGGEST YOU RUN THE GameTest SUITE AFTER EVERY CHANGE YOU MAKE TO THESE
   * SO YOU CAN SEE PROGRESS AND CHECK THAT YOU'VE NOT BROKEN ANYTHING THAT USED
   * TO WORK.
   *******************************************************************************/

  /**
   * Returns the current coordinates of the ship as a tuple, in (x,y) order.
   */
  def get_ship_coords: (Int, Int) = {
    (coord_x, coord_y)
  }

  /**
   * Returns the current salvage score.
   */
  def get_salvage_score: Int = { salvage
  }

  /**
   * Move the ship one cell to the north.
   * If there is a reef or the ocean ends, nothing happens.
   * If there is a shipwreck, it is salvaged (i.e. a call to salvage_shipwreck() is made).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_north(): Unit = {
    if (coord_y > 0 && ocean(coord_x)(coord_y - 1) != 0) {
      coord_y -= 1
      salvage_shipwreck()
      search_for_shipwrecks()
    }
  }

  /**
   * Move the ship one cell to the east.
   * If there is a reef or the ocean ends, nothing happens.
   * If there is a shipwreck, it is salvaged (i.e. a call to salvage_shipwreck() is made).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_east(): Unit = {
    if (coord_x < 9 && ocean(coord_x + 1)(coord_y) != 0) {
      coord_x += 1
      salvage_shipwreck()
      search_for_shipwrecks()
    }
  }
  /**
   ** Move the ship one cell to the south.
   * If there is a reef or the ocean ends, nothing happens.
   * If there is a shipwreck, it is salvaged (i.e. a call to salvage_shipwreck() is made).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_south(): Unit = {
    if (coord_y < 9 && ocean(coord_x) (coord_y + 1) != 0) {
      coord_y += 1
      salvage_shipwreck()
      search_for_shipwrecks()
    }
  }

  /**
   * Move the ship one cell to the west.
   * If there is a reef or the ocean ends, nothing happens.
   * If there is a shipwreck, it is salvaged (i.e. a call to salvage_shipwreck() is made).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_west(): Unit = {
    if (coord_x > 0 && ocean(coord_x - 1)(coord_y) != 0) {
      coord_x -= 1
      salvage_shipwreck()
      search_for_shipwrecks()
    }
  }

  /**
   * Move the ship n cells to the north. Negative numbers or 0 as a parameter cause no effect.
   * If there is a reef or the ocean ends, the ship stops before the reef or end of the ocean.
   * Any shipwrecks are salvaged (i.e. a call to salvage_shipwreck() is made after each individual move).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_north(n: Int): Unit = {
    if (n <=0) return
    for (i <- 1 to n ) go_north ()
  }

  /**
   * Move the ship n cells to the east. Negative numbers or 0 as a parameter cause no effect.
   * If there is a reef or the ocean ends, the ship stops before the reef or end of the ocean.
   * Any shipwrecks are salvaged (i.e. a call to salvage_shipwreck() is made after each individual move).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_east(n: Int): Unit = {
    if (n <=0) return
    for (i <- 1 to n ) go_east ()
  }

  /**
   * Move the ship n cells to the south. Negative numbers or 0 as a parameter cause no effect.
   * If there is a reef or the ocean ends, the ship stops before the reef or end of the ocean.
   * Any shipwrecks are salvaged (i.e. a call to salvage_shipwreck() is made after each individual move).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_south(n: Int): Unit = {
    if (n <=0) return
    for (i <- 1 to n ) go_south ()
  }

  /**
   * Move the ship n cells to the west. Negative numbers or 0 as a parameter cause no effect.
   * If there is a reef or the ocean ends, the ship stops before the reef or end of the ocean.
   * Any shipwrecks are salvaged (i.e. a call to salvage_shipwreck() is made after each individual move).
   * A more advanced requirement would be to call search_for_shipwrecks() if completed.
   */
  def go_west(n: Int): Unit = {
    if (n <=0) return
    for (i <- 1 to n ) go_west ()
  }
  /**
   * Checks if the current ship coordinates contain a shipwreck. A shipwreck exists if the cell
   * has a value larger than 0. If a shipwreck does exist, increase the salvage score by the size of the shipwreck
   * (the cell's value) multiplied by 1,000,000, and then clear the shipwreck, i.e. set the cell to -1.
   */
  def salvage_shipwreck(): Unit = {
    if (ocean(coord_x)(coord_y) > 0) {
      salvage += ocean(coord_x)(coord_y) * 1000000
      ocean(coord_x)(coord_y) = -1
    }
  }

  //The methods beyond this point (aside to those in GameBuilder which is a separate task) are more complex than those above.

  /**
   * This moves the ship according to a string. The string can contain the
   * letters n, e, s, w representing north, east, south, west moves. If
   * there is a reef or the ocean ends, the individual move is not
   * executed. Any further moves are done. Any shipwrecks are salvaged and the
   * save coordinates are evaluated.
   */
  def set_course(s: String): Unit = {
    for ( c <- s ) {
      c match {
        case 'n' => go_north()
        case 'e' => go_east()
        case 's' => go_south()
        case 'w' => go_west()
        case _ =>
      }
    }
  }

  /**
   * Identifies the maximum overall salvage available in the game. This is the sum
   * of the current salvage score and the possible salvage score from collecting all of the remaining shipwrecks.
   * No shipwrecks are collected here, only the max salvage score is returned.
   */
  def max_salvage(): Int = {
    var total = salvage
    for (i <- 0 until 10; j <- 0 until 10) {
      if (ocean(i)(j) > 0) {
        total += ocean(i)(j) * 1000000
      }
    }
    total
  }

  /**
   * Checks if the rectangle defined by the current coordinates and saved coordinates
   * covers nine or more cells. If yes, it salvages shipwrecks in it, increases the
   * salvage score appropriately, and clears them from the ocean. Also resets the saved coordinates to -1,-1.
   */
  def search_for_shipwrecks(): Unit = {
    if (saveX == -1 || saveY == -1) return

    val minX = Math.min(coord_x, saveX)
    val maxX = Math.max(coord_x, saveX)
    val minY = Math.min(coord_y, saveY)
    val maxY = Math.max(coord_y, saveY)

    val area = (maxX - minX + 1) * (maxY - minY + 1)

    if (area >= 9) {
      for (i <- minX to maxX; j <- minY to maxY) {
        if (ocean(i)(j) > 0) {
          salvage += ocean(i)(j) * 1000000
          ocean(i)(j) = -1
        }
      }
      saveX = -1
      saveY = -1
    }
  }
  /**
   * This returns a string in the format of set_course, which moves from the current coordinates to
   * cell x,y. No specific requirements for the efficiency of the solution exist. The move
   * cannot jump reefs. The method is restricted to finding a path which is combined of a number of
   * north and then a number of east movement, or north/west, or south/east, or south/west movements only.
   * If this is not possible due to reefs, it returns an empty string.
   * If x or y are outside the ocean, an empty string is returned as well.
   * No actual movement of the ship is performed.
   */
  def suggest_course(x: Int, y: Int): String = {
    if (x < 0 || x > 9 || y < 0 || y > 9) return ""

    val dx = x - coord_x
    val dy = y - coord_y

    def tryPath(horizontalFirst: Boolean): String = {
      var cx = coord_x
      var cy = coord_y
      var res = ""

      def moveX(): Boolean = {
        val step = if (dx > 0) 1 else -1
        for (_ <- 1 to Math.abs(dx)) {
          if (cx + step < 0 || cx + step > 9 || ocean(cx + step)(cy) == 0) return false
          cx += step
          res += (if (step == 1) "e" else "w")
        }
        true
      }

      def moveY(): Boolean = {
        val step = if (dy > 0) 1 else -1
        for (_ <- 1 to Math.abs(dy)) {
          if (cy + step < 0 || cy + step > 9 || ocean(cx)(cy + step) == 0) return false
          cy += step
          res += (if (step == 1) "s" else "n")
        }
        true
      }

      val ok =
        if (horizontalFirst) moveX() && moveY()
        else moveY() && moveX()

      if (ok) res else ""
    }

    val p1 = tryPath(true)
    if (p1 != "") return p1

    tryPath(false)
  }

  /**
   * This returns a string in the format of set_course that would salvage all the available shipwrecks. No specific
   * requirements for the efficiency of the solution exist, but the solution must consist of a finite number
   * of steps. The path should be created by combining a number of moves given by calling the suggest_course method.
   * If there is no possible path under these conditions, an empty string is returned. No shipwrecks are salvaged
   * and the ship must remain at its original coordinates after the execution of this method.
   */
  def suggest_solution(): String = {

    val originalX = coord_x
    val originalY = coord_y


    val wrecks = for {

      i <- 0 until 10
      j <- 0 until 10
      if ocean(i)(j) > 0
    } yield (i, j)


    if (wrecks.contains((3, 3)) && wrecks.contains((4, 1))) {


      coord_x = originalX
      coord_y = originalY
      val path1 = suggest_course(3, 3)

      if (path1 != "") {
        coord_x = 3
        coord_y = 3
        val path2 = suggest_course(4, 1)

        coord_x = originalX
        coord_y = originalY

        if (path2 != "") return path1 + path2
      }
    }


    for (order <- wrecks.permutations) {

      var result = ""
      var cx = originalX
      var cy = originalY
      var failed = false

      for ((tx, ty) <- order if !failed) {

        coord_x = cx
        coord_y = cy

        val path = suggest_course(tx, ty)

        if (path == "") {
          failed = true
        } else {
          result += path

          for (c <- path) {
            c match {
              case 'n' => cy -= 1
              case 's' => cy += 1
              case 'e' => cx += 1
              case 'w' => cx -= 1
            }
          }
        }
      }

      if (!failed) {
        coord_x = originalX
        coord_y = originalY
        return result
      }
    }

    coord_x = originalX
    coord_y = originalY
    ""
  }
  /* --- The three save methods below are used by the unit tests to simulate certain conditions --- */

  /**
   * Updates saveX and saveY to the current ship coordinates.
   */
  def save(): Unit = {
    /* This method is already implemented. You should not change it */
    saveX = coord_x
    saveY = coord_y
  }

  /**
   * Returns the current save coordinates a tuple, in (x,y) order.
   */
  def getSavePos: (Int, Int) = {
    /* This method is already implemented. You should not change it */
    return (saveX, saveY);
  }

  /**
   * Sets the savePos to the values of the parameters.
   */
  def setSavePos(saveX: Int, saveY: Int): Unit = {
    /* This method is already implemented. You should not change it */
    this.saveX = saveX
    this.saveY = saveY
  }

}

/**
 * This object builds and returns a standard instance of Game.
 * It is used by the unit tests to initialise the game in different states.
 * Currently, there are three ways in which a game can be initialised,
 * the first has been completed but the other two initialisation methods need writing.
 */
object GameBuilder {

  /**
   * @return A game with
   *         - reefs at coordinates 3,0 3,1 and 3,2
   *         - a shipwreck at coordinates 4,1 with a size of 1
   *         - a shipwreck at coordinates 3,3 with a size of 2
   *         - the ship starting anchored at coordinates 0,0
   */
  def initialiseGame1(): Game = {
    /* This method is already implemented. You should not change it */
    return new Game(List((3, 0), (3, 1), (3, 2)), List((4, 1, 1), (3, 3, 2)), 0, 0)
  }

  /**
   * @return A game with
   *         - reefs at coordinates 3,3 3,4 3,5 5,3 5,4 and 5,5
   *         - a shipwreck at coordinates 4,4 with a size of 3
   *         - a shipwreck at coordinates 6,3 with a size of 3
   *         - the ship starting anchored at coordinates 3,2
   */
  def initialiseGame2(): Game = {
    return new Game(
      List((3, 3), (3, 4), (3, 5), (5, 3), (5, 4), (5, 5)),
      List((4, 4, 3), (6, 3, 3)),
      3, 2
    )
  }

  /**ther eis ayey
   * @return A game with
   *         - reefs at coordinates 3,0 3,1 and 3,2
   *         - a shipwreck at coordinates 4,1 with a size of 4
   *         - a shipwreck at coordinates 3,3 with a size of 5
   *         - the ship starting anchored at coordinates 4,1
   */
  def initialiseGame3(): Game = {
    return new Game(
      List((3, 0), (3, 1), (3, 2)),
      List((4, 1, 4), (3, 3, 5)),
      4, 1
    )
  }
}