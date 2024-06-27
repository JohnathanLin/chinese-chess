package com.windypath

import java.lang.Exception

/**
 * 验证走法合法性
 *
 * @Date 2021-02-01.
 * @author Johnathan Lin
 */

/** 棋子是否在棋盘中 */
fun isLegalPosition(side: Int, p: Int, piece: Char): Boolean {
  return legalPosition[side][p].and(positionMask[charToSubscript(piece)]) != 0
}

/** 检测马走法是否合理 */
fun knightMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'N') || !isLegalPosition(side, d, 'N')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  var m: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 8) {
    n = p + knightDir[k]
    if (n == d) {
      m = p + knightCheck[k]
      if (board[m] == 0 && board[n].and(sideTag) == 0) {
        return true
      }
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测将走法是否合理 */
fun kingMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'K') || !isLegalPosition(side, d, 'K')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p + kingDir[k]
    if (n == d && board[n].and(sideTag) == 0) {
      return true
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测士走法是否合理 */
fun advisorMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'A') || !isLegalPosition(side, d, 'A')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p + advisorDir[k]
    if (n == d && board[n].and(sideTag) == 0) {
      return true
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测相走法是否合理 */
fun bishopMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'B') || !isLegalPosition(side, d, 'B')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  var m: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p + bishopDir[k]
    if (n == d) {
      m = p + bishopDir[k]
      if (board[m] == 0 && board[n].and(sideTag) == 0) {
        return true
      }
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测车走法是否合理 */
fun rookMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'R') || !isLegalPosition(side, d, 'R')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p
    while (true) {
      n += rookDir[k]
      if (legalPosition[side][n].and(positionMask[charToSubscript('R')]) == 0) {
        break
      }
      if ((board[n] == 0 || board[n].and(sideTag) == 0) && n == d) {
        return true
      }
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测炮走法是否合理 */
fun cannonMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'C') || !isLegalPosition(side, d, 'C')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  var overFlag: Boolean
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p
    overFlag = false
    while (true) {
      n += rookDir[k]
      if (legalPosition[side][n].and(positionMask[charToSubscript('C')]) == 0) {
        break
      }
      if (board[n] == 0) {
        if (!overFlag && n == d) {
          return true
        }
      } else {
        if (!overFlag) {
          overFlag = true
        } else {
          if (board[n].and(sideTag) == 0 && n == d) {
            return true
          }
          break
        }
      }
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 检测兵走法是否合理 */
fun pawnMoveLegal(p: Int, d: Int): Boolean {
  if (!isLegalPosition(side, p, 'P') || !isLegalPosition(side, d, 'P')) {
    throw IllegalMoveException("棋子的起始或终点所在位置不合法")
  }
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 3) {
    n = p + pawnDir[side][k]
    if (legalPosition[side][n].and(positionMask[charToSubscript('P')]) != 0) {
      if (board[n].and(sideTag) == 0 && n == d) {
        return true
      }
    }
  }
  throw IllegalMoveException("棋子移动不符合规则")
}

/** 棋子下棋是否合法 */
fun isPiecePlayLegal(piece: Int, from: Int, to: Int): Boolean {
  val sideTag = 16 + 16 * side
  if (piece.and(sideTag) == 0) {
    throw IllegalInputException("未选中棋子")
  }
  return when (piece) {
    16, 32 -> kingMoveLegal(from, to)
    17, 18, 33, 34 -> advisorMoveLegal(from, to)
    19, 20, 35, 36 -> bishopMoveLegal(from, to)
    21, 22, 37, 38 -> knightMoveLegal(from, to)
    23, 24, 39, 40 -> rookMoveLegal(from, to)
    25, 26, 41, 42 -> cannonMoveLegal(from, to)
    27, 28, 29, 30, 31, 43, 44, 45, 46, 47 -> pawnMoveLegal(from, to)
    else -> throw IllegalInputException("未选中棋子")
  }
}

/** 棋子输入是否合法 */
fun isPieceChooseLegal(cmdSplitArr: List<String>): List<Int> {
  // 行是否合法
  fun isLegalRow(row: Int) = row in 1..10
  // 列是否合法
  fun isLegalCol(col: Int) = col in 1..9

  if (cmdSplitArr.size != 4) {
    throw IllegalInputException("输入的坐标须为四个数字")
  }
  val res: List<Int>
  try {
    res = cmdSplitArr.map { Integer.parseInt(it) }
  } catch (e: Exception) {
    throw IllegalInputException("输入的坐标需为数字")
  }
  if (!isLegalRow(res[0]) || !isLegalCol(res[1]) || !isLegalRow(res[2]) || !isLegalCol(res[3])) {
    throw IllegalInputException("输入的坐标应在棋盘上，行为1到10之间，列为1到9之间")
  }
  return res
}
