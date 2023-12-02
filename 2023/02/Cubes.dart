import 'dart:io';
import 'dart:core';

int possibleRedCubes = 12;
int possibleGreenCubes = 13;
int possibleBlueCubes = 14;

final String RED = "red";
final String GREEN = "green";
final String BLUE = "blue";


void main() {
  
  File file = new File('2023/02/input.txt');
  List<String> lines = file.readAsLinesSync();
  List<Game> games = convertLinesToGames(lines);

  int sumQ1 = 0;
  int sumQ2 = 0;

  for(Game game in games) {
    sumQ1 += game.id;
    sumQ2 += game.power;
  }

  print("Answer for Q1 is: " + sumQ1.toString());
  print("Answer for Q2 is: " + sumQ2.toString());

}

List<Game> convertLinesToGames(List<String> lines) {

  List<Game> games = new List.empty(growable: true);

  for(String line in lines) {

    int gameId = int.parse(line.split(":")[0].split("Game")[1].trim());
    var drawingsLine = line.substring(line.indexOf(":") + 1).split(";"); // returns "[1 blue, 2 green, 5 red] [10 red, 1 blue, 3 green] [14 red]"

    List<Drawing> drawings = new List.empty(growable: true);

    for(String drawingLine in drawingsLine) {
      // stores all counts of cubes within one drawing
      var cubes = drawingLine.split(","); // returns "[1 blue] [2 green] [5 red]

      int countRed = 0;
      int countGreen = 0;
      int countBlue = 0;
      
      for(String countCube in cubes) {

        countCube = countCube.trim();

        String count = countCube.substring(0, countCube.indexOf(" ")).trim();
        String color = countCube.substring(countCube.indexOf(" ") + 1).trim();

        if(color == RED) {
          countRed = int.parse(count);
          continue;
        } else if (color == GREEN) {
          countGreen= int.parse(count);
          continue;
        } else if (color == BLUE) {
          countBlue = int.parse(count);
        }

      }

      Drawing drawing = new Drawing(countRed, countGreen, countBlue);
      drawings.add(drawing);
    }

    Game game = new Game(gameId, drawings);
    games.add(game);
  }

  return games;

}

class Game {

  int id = 0;
  int power = 0;

  Game(int id, List<Drawing> drawings) {

    this.id = id;

    int maxRed = 1;
    int maxGreen = 1;
    int maxBlue = 1;

    for(Drawing drawing in drawings) {
      if(!drawing.isPossible)  {
        this.id = 0; // set id to 0 if there is at least one impossible drawing
      }

      if(drawing.redCubes! >= maxRed) {
        maxRed = drawing.redCubes!;
      }
      if(drawing.greenCubes! >= maxGreen) {
        maxGreen = drawing.greenCubes!;
      }
      if(drawing.blueCubes! >= maxBlue) {
        maxBlue = drawing.blueCubes!;
      }
    }

    this.power = maxRed * maxGreen * maxBlue;

  }

}


class Drawing {

  bool isPossible = false;

  int? redCubes;
  int? greenCubes;
  int? blueCubes;

  Drawing(int redCubes, int greenCubes, int blueCubes) {
    if(setRedCubes(redCubes) & setGreenCubes(greenCubes) & setBlueCubes(blueCubes)) {
      this.isPossible = true;
    }
  }

  bool setRedCubes(int redCubes) {
    this.redCubes = redCubes;
    if(redCubes <= possibleRedCubes) {
      return true;
    }
    return false;
  }

  bool setGreenCubes(int greenCubes) {
    this.greenCubes = greenCubes;
    if(greenCubes <= possibleGreenCubes) {
      return true;
    }
    return false;
  }

  bool setBlueCubes(int blueCubes) {
    this.blueCubes = blueCubes;
    if(blueCubes <= possibleBlueCubes) {
      return true;
    }
    return false;
  }
}