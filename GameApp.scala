package cw

import scala.io.StdIn

/**
 * This GameApp class is not needed for the coursework.
 * It is instead for explanatory purposes only.
 * Once you have some of your methods implemented (Various moves, salvage_shipwreck, etc.),
 * it can be ran to provide a short interactive demo for the initial game scenario.
 * This can be helpful for better understanding the game structure when tackling the harder methods
 */

object GameApp {
  def main(args: Array[String]): Unit = {
    var input:String=""
    var round:Int=1
    var game:Game=GameBuilder.initialiseGame1()
    while(!input.equals("q")){
      println("You are in round "+ round+" and your salvage is "+game.get_salvage_score)
      println("You could get up to "+game.max_salvage()+" salvage")
      game.print_ocean()
      input=StdIn.readLine("Enter next move (n,e,s,w,save):")
      if(input.equals("n"))
        game.go_north()
      else if(input.equals("e"))
        game.go_east()
      else if(input.equals("s"))
        game.go_south()
      else if(input.equals("w"))
        game.go_west()
      else if(input.equals("save"))
        game.save()
      round += 1
    }
  }
}