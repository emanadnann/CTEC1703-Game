package cw

import org.junit.Test
import org.junit.Assert._

class GameTest {

  @Test def test_00_east(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.go_east()
    assertEquals((1,0), game.get_ship_coords)
  }

  @Test def test_01_east(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.go_east()
    assertEquals((4,2), game.get_ship_coords)
  }

  @Test def test_02_east(): Unit = {
    var game: Game = GameBuilder.initialiseGame3()
    game.go_east()
    assertEquals((5,1), game.get_ship_coords)
  }

  @Test def test_03_west(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_west()
    assertEquals((0,0), game.get_ship_coords)
  }

  @Test def test_04_west(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_west()
    assertEquals((2,2), game.get_ship_coords)
  }

  @Test def test_05_west(): Unit = {
    var game: Game=GameBuilder.initialiseGame3()
    game.go_west()
    assertEquals((4,1), game.get_ship_coords)
  }

  @Test def test_06_east(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_east()
    game.go_east()
    assertEquals((5,2), game.get_ship_coords)
  }

  @Test def test_07_east(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_east(3)
    assertEquals((2,0), game.get_ship_coords)
  }

  @Test def test_08_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south()
    assertEquals((0,1), game.get_ship_coords)
  }

  @Test def test_09_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_south()
    assertEquals((3,2), game.get_ship_coords)
  }

  @Test def test_10_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame3()
    game.go_south()
    assertEquals((4,2), game.get_ship_coords)
  }

  @Test def test_11_north(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_north()
    assertEquals((0,0), game.get_ship_coords)
  }

  @Test def test_12_north(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_north()
    assertEquals((3,1), game.get_ship_coords)
  }

  @Test def test_13_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south()
    game.go_south()
    assertEquals((0,2), game.get_ship_coords)
  }

  @Test def test_14_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south(5)
    assertEquals((0,5), game.get_ship_coords)
  }

  @Test def test_15_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south(13)
    assertEquals((0,9), game.get_ship_coords)
  }

  @Test def test_16_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_south(2)
    assertEquals((3,2), game.get_ship_coords)
  }

  @Test def test_17_south(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south(-2)
    assertEquals((0,0), game.get_ship_coords)
  }

  @Test def test_18_south_east(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_south(3)
    game.go_east(3)
    assertEquals((3,3), game.get_ship_coords)
    assertEquals(2000000, game.get_salvage_score)
  }

  @Test def test_19_ship_at_shipwreck(): Unit = {
    var game: Game = GameBuilder.initialiseGame3()
    assertEquals(0, game.get_salvage_score)
    game.salvage_shipwreck()
    assertEquals(4000000, game.get_salvage_score)
  }

  //The tests beyond this point are generally more difficult to pass based on the methods they are assessing,
  // therefore you may wish to focus on passing the tests above first.

  @Test def test_20_south_east_save(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.go_east(5)
    game.go_south()
    assertEquals((8,3), game.get_ship_coords)
    assertEquals(3000000, game.get_salvage_score)
  }

  @Test def test_21_south_east_save(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.go_east(3)
    game.go_south()
    assertEquals(3000000, game.get_salvage_score)
    assertEquals((3,2), game.getSavePos)
    game.go_east()
    assertEquals((7,3), game.get_ship_coords)
    assertEquals(3000000, game.get_salvage_score)
    assertEquals((-1,-1), game.getSavePos)
  }

  @Test def test_22_save(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.save()
    game.go_east(3)
    game.go_south(2)
    assertEquals((-1,-1), game.getSavePos)
    assertEquals((6,4), game.get_ship_coords)
    assertEquals(6000000, game.get_salvage_score)
  }

  @Test def test_23_save(): Unit = {
    var game: Game=GameBuilder.initialiseGame2()
    game.go_east(1)
    game.go_south(4)
    game.save()
    game.go_east(2)
    game.go_north(4)
    assertEquals((-1,-1), game.getSavePos)
    assertEquals((6,2), game.get_ship_coords)
    assertEquals(6000000, game.get_salvage_score)
  }

  @Test def test_24_save(): Unit = {
    var game: Game=GameBuilder.initialiseGame1()
    game.go_east(2)
    game.go_south(3)
    game.save()
    game.go_south()
    game.go_east()
    game.save()
    assertEquals((3,4), game.getSavePos)
    assertEquals((3,4), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_25_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("eenn")
    assertEquals((2,0), game.get_ship_coords)
  }


  @Test def test_26_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("eesnne")
    assertEquals((2,0), game.get_ship_coords)
  }

  @Test def test_27_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("eesnneww")
    assertEquals((0,0), game.get_ship_coords)
  }

  @Test def test_28_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("esnne")
    assertEquals((2,0), game.get_ship_coords)
  }

  @Test def test_29_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("eeeenns")
    assertEquals((2,1), game.get_ship_coords)
  }

  @Test def test_30_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("ess")
    assertEquals((4,4), game.get_ship_coords)
    assertEquals(3000000, game.get_salvage_score)
  }

  @Test def test_31_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.go_south(2)
    game.go_east(2)
    game.save()
    game.set_course("ssseee")
    assertEquals((5,5), game.get_ship_coords)
    assertEquals(2000000, game.get_salvage_score)
  }

  @Test def test_32_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("ssseeeewww")
    assertEquals((1,3), game.get_ship_coords)
    assertEquals(2000000, game.get_salvage_score)
  }

  @Test def test_33_set_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("ssssssssssss")
    assertEquals((0,9), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_34_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggest_course(3, 3)
    assertEquals("ssseee", sol)
    assertEquals((0,0), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_35_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggest_course(4, 2)
    assertEquals("", sol)
    assertEquals((0,0), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_36_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("ess")
    assertEquals((4,4), game.get_ship_coords)
    var sol=game.suggest_course(3, 2)
    assertEquals("nnw", sol)
    assertEquals(3000000, game.get_salvage_score)
  }

  @Test def test_37_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    var sol=game.suggest_course(5, 6)
    assertEquals("", sol)
    assertEquals((3,2), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_38_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    var sol=game.suggest_course(10, 10)
    assertEquals("", sol)
    assertEquals((0,0), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_39_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("ssseee")
    var sol=game.suggest_course(4, 1)
    assertEquals("enn", sol)
    assertEquals((3,3), game.get_ship_coords)
    assertEquals(2000000, game.get_salvage_score)
  }

  @Test def test_40_suggest_course(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.go_south(5)
    assertTrue(game.suggest_course(4,1).equals("eeeennnn") || game.suggest_course(4,1).equals("nnnneeee"))
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_41_max_salvage(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    assertEquals(3000000, game.max_salvage())
    assertEquals((0,0), game.get_ship_coords)
    assertEquals(0, game.get_salvage_score)
  }

  @Test def test_42_max_salvage(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("eees")
    assertEquals(6000000, game.max_salvage())
    assertEquals(3000000, game.get_salvage_score)
  }

  @Test def test_43_max_salvage(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("eees")
    assertEquals(6000000, game.max_salvage())
    assertEquals(3000000, game.get_salvage_score)
    game.set_course("nwwss")
    assertEquals(6000000, game.max_salvage())
    assertEquals(6000000, game.get_salvage_score)
  }

  @Test def test_44_max_salvage(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("ess")
    assertEquals(6000000, game.max_salvage())
    assertEquals(3000000, game.get_salvage_score)
    game.set_course("nnees")
    assertEquals(6000000, game.max_salvage())
    assertEquals(6000000, game.get_salvage_score)
  }

  @Test def test_45_suggest_solution(): Unit = {
    var game: Game = GameBuilder.initialiseGame2()
    game.set_course("eees")
    assertEquals("", game.suggest_solution())
  }

  @Test def test_46_suggest_solution(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    assertEquals("ssseeeenn", game.suggest_solution())
  }

  @Test def test_47_suggest_solution(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.go_south(5)
    assertTrue(game.suggest_solution().equals("nneeeenn") || game.suggest_solution().equals("eeennenn"))
  }

  @Test def test_48_search_for_shipwrecks(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.set_course("sssseeee")
    assertEquals(0, game.get_salvage_score)
    assertEquals((4,4), game.get_ship_coords)
    game.setSavePos(0,0)
    game.search_for_shipwrecks()
    assertEquals(3000000, game.get_salvage_score)
    assertEquals((4,4), game.get_ship_coords)
  }

  @Test def test_49_search_for_shipwrecks(): Unit = {
    var game: Game = GameBuilder.initialiseGame1()
    game.save()
    game.set_course("ssseee")
    assertEquals((-1,-1), game.getSavePos)
    game.go_east()
    assertEquals(2000000, game.get_salvage_score)
    assertEquals((4,3), game.get_ship_coords)
  }
}