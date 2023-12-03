import 'dart:io';
import 'dart:core';

void main() {
  File file = new File('2023/03/input.txt');
  List<String> lines = file.readAsLinesSync();

  int sumValidParts = 0;

  for (int currentLineIndex = 0;
      currentLineIndex < lines.length;
      currentLineIndex++) {
    String? prevLine; // null on first line
    String curLine;
    String? nextLine; // null on last line

    if (currentLineIndex != 0) {
      prevLine = lines[currentLineIndex - 1];
    }
    curLine = lines[currentLineIndex];
    if (currentLineIndex + 1 != lines.length) {
      nextLine = lines[currentLineIndex + 1];
    }

    sumValidParts += getSumValidPartsCurrentLine(prevLine, curLine, nextLine);
  }

  print("Answer for Q1 is: " + sumValidParts.toString());
}

/**
 * Returns all valid numbers in current line
 */
int getSumValidPartsCurrentLine(
    String? prevLine, String curLine, String? nextLine) {
  List<BeginEndIndex> beginEndIndexList = getBeginAndEndIndex(curLine);
  int sumValidPartNumbers = 0;

  // get sourrinding chars of number in current line
  for (BeginEndIndex beginEndIndex in beginEndIndexList) {
    int beginIndex = beginEndIndex.getBeginIndex();
    int endIndex = beginEndIndex.getEndIndex() + 1;

    String surroundingPrevLine = "";
    String surroundingCurLine = "";
    String surroundingNextLine = "";

    if (prevLine != null) {
      surroundingPrevLine = prevLine.substring(beginIndex, endIndex);
    }
    surroundingCurLine = curLine.substring(beginIndex, endIndex);
    if (nextLine != null) {
      surroundingNextLine = nextLine.substring(beginIndex, endIndex);
    }

    // contains all chars surrounding number in current line
    String surroundingNumber =
        surroundingPrevLine + surroundingCurLine + surroundingNextLine;

    sumValidPartNumbers +=
        computeValidPartNumbers(surroundingNumber, surroundingCurLine);
  }

  return sumValidPartNumbers;
}

/**
 * Returns all begin and end indices of numbers of a String
 */
List<BeginEndIndex> getBeginAndEndIndex(String curLine) {
  List<BeginEndIndex> beginEndIndexList = new List.empty(growable: true);

  RegExp digitRegExp = new RegExp(r'\d');

  int? beginIndex; // stores also first '.'
  int? endIndex; // stores also last '.'

  bool lookingForBeginIndex = true;
  bool foundNumber = false;

  for (int currentCharIndex = 0;
      currentCharIndex < curLine.length;
      currentCharIndex++) {
    // search for index of begin and end of number
    if (curLine[currentCharIndex].contains(digitRegExp) &&
        lookingForBeginIndex) {
      beginIndex = (currentCharIndex == 0) ? 0 : (currentCharIndex - 1);
      lookingForBeginIndex = false; // start search for end of number
    }
    if ((!curLine[currentCharIndex].contains(digitRegExp) &&
            !lookingForBeginIndex) ||
        (currentCharIndex == (curLine.length - 1) && !lookingForBeginIndex)) {
      endIndex = currentCharIndex;
      lookingForBeginIndex = true; // start search for new number
      foundNumber = true;
    }

    // has found number
    if (foundNumber) {
      BeginEndIndex beginEndIndexEntry =
          new BeginEndIndex(beginIndex!, endIndex!);
      beginEndIndexList.add(beginEndIndexEntry);
      foundNumber = false;
    }
  }

  return beginEndIndexList;
}

/**
 * Returns valid part numbers or 0 if part number is invalid
 */
int computeValidPartNumbers(
    String surroundingNumber, String surroundingCurLine) {
  RegExp validRegExp = new RegExp(r'\d');

  bool isValidPart = false;
  for (int cur = 0; cur < surroundingNumber.length; cur++) {
    // check if there is any character that doesnt match digit or '.'
    if (!surroundingNumber[cur].contains(validRegExp) &&
        surroundingNumber[cur] != '.') {
      isValidPart = true;
      break;
    }
  }

  if (isValidPart) {
    String number = surroundingCurLine.replaceAll(new RegExp(r'[^0-9]'), '');
    return int.parse(number);
  }

  return 0;
}

class BeginEndIndex {
  int? beginIndex;
  int? endIndex;

  BeginEndIndex(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }

  int getBeginIndex() {
    if (beginIndex != null) {
      return beginIndex!;
    }
    throw "Invalid index found";
  }

  int getEndIndex() {
    if (endIndex != null) {
      return endIndex!;
    }
    throw "invalid index found";
  }
}
