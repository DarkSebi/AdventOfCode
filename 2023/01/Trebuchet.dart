import 'dart:io';
import 'dart:core';

void main() {
  File file = new File('2023/01/input.txt');
  List<String> lines = file.readAsLinesSync();

  int sumQ1 = 0;
  int sumQ2 = 0;
  for (String line in lines) {
    sumQ1 = sumQ1 + getFirstAndLastDigit(line);

    String replacedLine = replaceStringsWithDigits(line);
    sumQ2 = sumQ2 + getFirstAndLastDigit(replacedLine);
  }

  print("The answer for Q1 is: " + sumQ1.toString());
  print("The answer for Q2 is: " + sumQ2.toString());
}

int getFirstAndLastDigit(String line) {
  var lineArray = line.split('');

  bool hasFindFirst = false;
  int? currentDigit;
  int? lastDigit = null;
  String? calibrationValue;
  lineArray.forEach((element) {
    currentDigit = int.tryParse(element);
    if (currentDigit != null && !hasFindFirst) {
      hasFindFirst = true;
      calibrationValue = currentDigit!.toString();
    } else if (currentDigit != null) {
      lastDigit = currentDigit!;
    }
  });

  if (lastDigit == null) {
    calibrationValue = calibrationValue! + calibrationValue!;
  } else {
    calibrationValue = calibrationValue! + lastDigit.toString();
  }

  return int.parse(calibrationValue!);

}


  Map<Pattern, String> keyValueStore = {
    RegExp(r'one'): "o1e",
    RegExp(r'two'): "t2o",
    RegExp(r'three'): "t3e",
    RegExp(r'four'): "f4r",
    RegExp(r'five'): "f5e",
    RegExp(r'six'): "s6x",
    RegExp(r'seven'): "s7n",
    RegExp(r'eight'): "e8t",
    RegExp(r'nine'): "n9e"
  };

String replaceStringsWithDigits(String line) {
 
     bool didChange = false;

  do {
    didChange = false;
    keyValueStore.forEach((key, value) {
      // get first and last occurence of any word, no matter which
      int indexOfFirst = line.length;
      int indexOfLast = 0;
      keyValueStore.forEach((keyInner, value) {
        if (line.contains(keyInner)) {
          int idx = line.indexOf(keyInner);
          int idx2 = line.lastIndexOf(keyInner);
          if (indexOfFirst >= idx) {
            indexOfFirst = idx;
          }
          if (indexOfLast <= idx2) {
            indexOfLast = idx2;
          }
        }
      });

      // check if current key is first
      if (indexOfFirst == line.indexOf(key)) {
        line = line.toLowerCase().replaceAll(key, value);
        didChange = true;
      }
      // check if current key is last
      if (indexOfLast == line.indexOf(key)) {
        line = line.toLowerCase().replaceAll(key, value);
        didChange = true;
      }
    });
  } while (didChange);


  return line;

}