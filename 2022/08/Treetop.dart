import 'dart:io';

void main() {
  File file = new File('2022/08/example.txt');
  List<String> lines = file.readAsLinesSync();

  var numRows = lines.length;
  var numCols = lines[0].length;

  var trees = List.generate(numRows, (i) => new List.generate(numCols, (index) => 0));
  var booelanMatrix = List.generate(numRows, (i) => new List.generate(numCols, (index) => 0));

  for(int i = 0; i < trees.length; i++) {
    for(int j = 0; j < trees[i].length; j++) {
      trees[i][j] = int.parse(lines[i][j]);
    }
  }

  int sum = 0;
  for(int i = 0; i < trees.length; i++) {
    int maxRow = 0;
    int maxRowIdx = 0;
    int isMax = 1;
    for(int j = 0; j < trees[i].length; j++) {
      if(trees[i][j] > maxRow) {
        maxRow = trees[i][j];
        maxRowIdx = j;
      }
    }
    for(int c = 0; c < trees.length; c++) {
        if(trees[c][maxRowIdx] > maxRow) {
          // there is a higher value in this coloumn
          isMax = 0;
        }
    }
    sum += isMax;
    booelanMatrix[i][maxRowIdx] = isMax;
  }

  print("num of visible trees: " + sum.toString());

}