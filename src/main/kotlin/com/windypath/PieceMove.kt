package com.windypath

/**
 * 走法
 *
 * @Date 2021-01-24.
 * @author Johnathan Lin
 */

//马 Knight
//class Move {
//  companion object {
//
//  }
//}
// 走法
data class Move(
  var from: Int, // 棋子原位置
  var to: Int // 棋子目标位置
)

val moveArray: ArrayList<Move> = ArrayList(128)
//var moveNum: Int = 0

/** 马的走法生成 */
// 马下一步可能的位置
val knightDir: List<Int> = listOf(+0x0e, -0x12, -0x21, -0x1f, -0x0e, +0x12, +0x1f, +0x21)
// 马下一步对应的马腿
val knightCheck: List<Int> = listOf(-0x01, -0x01, -0x10, -0x10, +0x01, +0x01, +0x10, +0x10)

/** 清空走法 */
fun initGen() {
  moveArray.clear()
}

/** 保存走法 */
fun saveMove(from: Int, to: Int) {
  val move = Move(from, to)
  moveArray.add(move)
}

/** 生成马的走法 */
fun knightMove(p: Int) {
  // 下一步可能行走的位置
  var n: Int
  // 马腿位置
  var m: Int
  // 走棋方（红方16，黑方32）
  val sideTag: Int = 16 + side * 16

  for (k in 0 until 8) { // 8个方向
    // n为新的可能走到的位置
    n = p + knightDir[k]
    // 是否在棋盘上
    if (legalPosition[side][n].and(positionMask[charToSubscript('N')]) != 0) {
      m = p + knightCheck[k]
      if (board[m] == 0 && board[n].and(sideTag) == 0) {
        saveMove(p, n)
      }
    }
  }
}

/** 将（帅）的走法生成 */
val kingDir: List<Int> = listOf(-0x10, +0x01, +0x10, -0x01, 0, 0, 0, 0)

/** 生成将帅走法 */
fun kingMove(p: Int) {
  // 下一步可能行走的位置
  var n: Int
  // 走棋方（红方16，黑方32）
  val sideTag: Int = 16 + side * 16
  for (k in 0 until 4) { // 4个方向
    n = p + kingDir[k]
    if (legalPosition[side][n].and(positionMask[charToSubscript('K')]) != 0) {
      if (board[n].and(sideTag) == 0) {
        saveMove(p, n)
      }
    }
  }
}

/** 士的走法生成 */
val advisorDir = listOf(-0x11, -0x0f, +0x11, +0x0f, 0, 0, 0, 0)

/** 生成士(仕)的走法 */
fun advisorMove(p: Int) {
  // 下一步可能行走的位置
  var n: Int;
  //
  val sideTag = 16 + side * 16
  for (k in 0 until 4) {
    n = p + advisorDir[k]
    if (legalPosition[side][n].and(positionMask[charToSubscript('A')]) != 0) {
      if (board[n].and(sideTag) == 0) {
        saveMove(p, n)
      }
    }
  }
}

/** 相(象)的走法生成 */
// 四个可行方向
val bishopDir = listOf(-0x22, -0x1e, +0x22, +0x1e, 0, 0, 0, 0)
// 四个方向对应的象眼位置
val bishopCheck = listOf(-0x11, -0x0f, +0x11, +0x0f, 0, 0, 0, 0)

/** 生成相的走法 */
fun bishopMove(p: Int) {
  var n: Int
  var m: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p + bishopDir[k]
    if (legalPosition[side][n].and(positionMask[charToSubscript('B')]) != 0) {
      m = p + bishopDir[k]
      if (board[m] == 0 && board[n].and(sideTag) == 0) {
        saveMove(p, n)
      }
    }
  }
}

/** 车的走法生成 */
val rookDir = arrayListOf(-0x01, -0x10, +0x01, +0x10, 0, 0, 0, 0)

/** 车的走法生成 */
fun rookMove(p: Int) {
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 4) {
    n = p
    while (true) {
      n += rookDir[k]
      if (legalPosition[side][n].and(positionMask[charToSubscript('R')]) == 0) {
        break
      }
      if (board[n] == 0) {
        saveMove(p, n)
      } else {
        if (board[n].and(sideTag) == 0) {
          saveMove(p, n)
        }
        break
      }
    }
  }
}

/** 炮的走法生成 */

/** 生成炮的走法 */
fun cannonMove(p: Int) {
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
        if (!overFlag) {
          saveMove(p, n)
        }
      } else {
        if (!overFlag) {
          overFlag = true
        } else {
          if (board[n].and(sideTag) == 0) {
            saveMove(p, n)
          }
          break
        }
      }
    }
  }
}

/** 兵（卒）走法生成 */
val pawnDir = listOf(listOf(-0x01, +0x01, -0x10), listOf(-0x01, +0x01, +0x10))

/** 生成兵卒走法 */
fun pawnMove(p: Int) {
  var n: Int
  val sideTag = 16 + 16 * side
  for (k in 0 until 3) {
    n = p + pawnDir[side][k]
    if (legalPosition[side][n].and(positionMask[charToSubscript('P')]) != 0) {
      if (board[n].and(sideTag) == 0) {
        saveMove(p, n)
      }
    }
  }
}

/** 生成所有走法 */
fun genAllMove() {
  var p: Int
  val sideTag = 16 +  16 * side
  for (i in 3 until 13) {
    for (j in 3 until 12) {
      p = i.shl(4) + j
      if (board[p].and(sideTag) == 0) {
        continue
      }
      when (board[p] - sideTag) {
        0 -> kingMove(p)
        1, 2-> advisorMove(p)
        3, 4-> bishopMove(p)
        5,6 -> knightMove(p)
        7, 8 -> rookMove(p)
        9, 10 -> cannonMove(p)
        11, 12, 13, 14, 15 -> pawnMove(p)
      }
    }
  }
}

/** 输出所有走法 */
fun outputMove() {
  for (move in moveArray) {
    println("from ${move.from} to ${move.to}")
  }
  println("total move number: ${moveArray.size}")
}