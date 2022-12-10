import 'dart:io';

void main() {
  File file = new File('2022/08/input.txt');
  List<String> lines = file.readAsLinesSync();

  var numRows = lines.length;
  var numCols = lines[0].length;

  var trees = List.generate(numRows, (i) => new List.generate(numCols, (index) => 0));
  //var booelanMatrix = List.generate(numRows, (i) => new List.generate(numCols, (index) => 0));

  for(int i = 0; i < trees.length; i++) {
    for(int j = 0; j < trees[i].length; j++) {
      trees[i][j] = int.parse(lines[i][j]);
    }
  }

  int sum = numCols*2 + (numRows - 2)*2; // edges are always visible

  for(int i = 1; i < trees.length - 1; i++) { // starting at idx 1, because edges are always visible
    for(int j = 1; j < trees[i].length - 1; j++) {
      bool isVisibleLeft = true;
      bool isVisibleTop = true;
      bool isVisibleRight = true;
      bool isVisibleBottom = true;
      int curTree = trees[i][j];
      // starting left
      for(int l = 0; l < j; l++) {
        if(trees[i][l] >= curTree) {
          isVisibleLeft = false;
        }
      }
      // starting top
      for(int t = 0; t < i; t++) {
        if(trees[t][j] >= curTree) {
          isVisibleTop = false;
        }
      }
      // starting right
      for(int r = trees[i].length-1; r > j; r--) {
        if(trees[i][r] >= curTree) {
          isVisibleRight = false;
        }
      }
      // starting bottom
      for(int b = trees.length-1; b > i; b--) {
        if(trees[b][j] >= curTree) {
          isVisibleBottom = false;
        }
      }
      if(isVisibleLeft || isVisibleTop || isVisibleRight || isVisibleBottom) {
        sum++;
      }
    }

  }

  print("num of visible trees: " + sum.toString());

}