# CTEC1703 Game (Scala Coursework)

## Overview

This project is a Scala-based coursework assignment for the CTEC1703 Computer Programming module.
It implements a grid-based ocean navigation game where a ship collects salvage from shipwrecks while avoiding reefs.

## Features

* 10x10 ocean grid system
* Ship movement (North, East, South, West)
* Reef collision detection (blocked movement)
* Shipwreck salvaging system
* Save position & area-based salvage mechanic
* Course execution using string commands (e.g. "n", "e", "s", "w")
* Path suggestion algorithm (`suggest_course`)
* Maximum salvage calculation
* Full unit test coverage (100% pass rate)

## Technologies Used

* Scala
* JUnit (for testing)
* IntelliJ IDEA

## Project Structure

```
cw/
 ├── Game.scala        # Main game logic
 ├── GameTest.scala    # Unit tests
 ├── GameApp.scala     # Optional interactive demo
```

## How to Run

1. Open the project in IntelliJ IDEA
2. Ensure Scala SDK is configured
3. Run `GameApp.scala` to play the demo
   OR
   Run `GameTest.scala` to execute all unit tests

## Example Gameplay

* The ship starts at a fixed position
* Move using directions (n, e, s, w)
* Avoid reefs (cannot pass through)
* Collect shipwrecks to gain salvage points

## Results

* All 50 unit tests passed
* Final Mark: **100/100**

## Author

Eman Adnan

