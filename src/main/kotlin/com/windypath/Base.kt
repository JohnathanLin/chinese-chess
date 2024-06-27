package com.windypath

/**
 * 基础棋盘信息
 *
 * @Date 2021-01-05.
 * @author Johnathan Lin
 */
// 当前局面
var board = mutableListOf(
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0,39,37,35,33,32,34,36,38,40, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0,41, 0, 0, 0, 0, 0,42, 0, 0, 0, 0, 0,
  0, 0, 0,43, 0,44, 0,45, 0,46, 0,47, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0,27, 0,28, 0,29, 0,30, 0,31, 0, 0, 0, 0,
  0, 0, 0, 0,25, 0, 0, 0, 0, 0,26, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0,23,21,19,17,16,18,20,22,24, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
)
// Fen局面字符串
var fen = "rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w"

// 整合后的合法位置
val legalPosition: List<List<Int>> = listOf(
  listOf(
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 1,25, 1, 9, 1,25, 1, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 1, 9, 1, 9, 1, 9, 1, 9, 0, 0, 0, 0,
    0, 0, 0,17, 1, 1, 7,19, 7, 1, 1,17, 0, 0, 0, 0,
    0, 0, 0, 1, 1, 1, 3, 7, 3, 1, 1, 1, 0, 0, 0, 0,
    0, 0, 0, 1, 1,17, 7, 3, 7,17, 1, 1, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  ),
  listOf(
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 1, 1,17, 7, 3, 7,17, 1, 1, 0, 0, 0, 0,
    0, 0, 0, 1, 1, 1, 3, 7, 3, 1, 1, 1, 0, 0, 0, 0,
    0, 0, 0,17, 1, 1, 7,19, 7, 1, 1,17, 0, 0, 0, 0,
    0, 0, 0, 9, 1, 9, 1, 9, 1, 9, 1, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 1,25, 1, 9, 1,25, 1, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
  )
)

// 棋子位置数组
val piece = arrayListOf(
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  199, 198, 200, 197, 201, 196, 202, 195, 203, 164,
  170, 147, 149, 151, 153, 155, 55, 54, 56, 53, 57,
  52, 58, 51, 59, 84, 90, 99, 101, 103, 105, 107
)

// 位置对应的值（将 士 相 马 车 炮 兵）
val positionMask = listOf(2, 4, 16, 1, 1, 1, 8)

// 当前走棋方(0:红方, 1:黑方)
var side = 0

/** 棋子数字转为相应字符 */
fun pieceToChar(piece: Int): String {
  if (piece < 32) {
    return when (piece) {
      16 -> "K"
      17, 18 -> "A"
      19, 20 -> "B"
      21, 22 -> "N"
      23, 24 -> "R"
      25, 26 -> "C"
      27, 28, 29, 30, 31 -> "P"
      else -> ""
    }
  } else {
    return when (piece) {
      32 -> "k"
      33, 34 -> "a"
      35, 36 -> "b"
      37, 38 -> "n"
      39, 40 -> "r"
      41, 42 -> "c"
      43, 44, 45, 46, 47 -> "p"
      else -> ""
    }
  }
}

/** 字符串转对应下标 */
fun charToSubscript(piece: Char): Int {
  return when (piece) {
    'k', 'K' -> 0
    'a', 'A' -> 1
    'b', 'B' -> 2
    'n', 'N' -> 3
    'r', 'R' -> 4
    'c', 'C' -> 5
    'p', 'P' -> 6
    else -> 7
  }
}

/** 由局面转变为Fen字符串 */
fun boardToFen() {
  val sb = StringBuilder()
  for (i in 3..12) {
    var k = 0
    for (j in 3..11) {
      val pc = board[i.shl(4) + j]
      if (pc != 0) {
        if (k > 0) {
          sb.append(k)
          k = 0
        }
        sb.append(pieceToChar(pc))
      } else {
        k++
      }
    }
    if (k > 0) {
      sb.append(k)
    }
    if (i != 12) {
      sb.append("/")
    }
  }
  sb.append(" ")
  sb.append(if (side == 0) "w" else "b")
  fen = sb.toString()
}

/** 由Fen字符串还原局面 */
fun fenToBoard() {
  val pcWhite = mutableListOf(16, 17, 19, 21, 23, 25, 27)
  val pcBlack = mutableListOf(32, 33, 35, 37, 39, 41, 43)
  var i = 3
  var j = 3
  var index = 0
  while (fen[index] != ' ') {
    val str = fen[index]
    if (str == '/') {
      j = 3
      i++
      if (i > 12) {
        break
      }
    } else if (str >= '1' && str <= '9') {
      for (k in 0 until str - '0') {
        if (j >= 11) {
          break
        }
        j++
      }
    } else if (str >= 'A' && str <= 'Z') {
      if (j <= 11) {
        val k = charToSubscript(str)
        if (k < 7) {
          if (pcWhite[k] < 32) {
            board[i.shl(4) + j] = pcWhite[k]
            pcWhite[k]++
          }
        }
        j++
      }
    } else if (str >= 'a' && str <= 'z') {
      if (j <= 11) {
        val k = charToSubscript(str)
        if (k < 7) {
          if (pcBlack[k] < 48) {
            board[i.shl(4) + j] = pcBlack[k]
            pcBlack[k]++
          }
        }
        j++
      }
    }

    index++
  }
  index++
  side = if (fen[index] == 'b') 1 else 0
}

/** 将军检测 */
fun check(lSide: Int): Boolean {
  val wKing: Int = piece[16]
  val bKing: Int = piece[32]
  var p: Int
  val q: Int
  var r: Boolean
  val sideTag = 32 - lSide * 16
  val fSide = 1 - lSide
  var posAdd: Int

  if (wKing == 0 || bKing == 0) {
    return false
  }
  // 检测将帅是否照面
  r = true
  if (wKing % 16 == bKing % 16) {
    for (p in (wKing - 16) downTo bKing step 16) {
      if (board[p] != 0) {
        r = false
        break
      }
    }
    if (r) {
      return r
    }
  }
  q = piece[48 - sideTag] // lSide方将的位置
  // 检测将是否被马攻击
  var n: Int
  var m: Int
  for (i in 5..6) {
    p = piece[sideTag + i]
    if (p == 0) {
      continue
    }
    for (k in 0 until 8) {
      n = p + knightDir[k]
      if (n != q) {
        continue
      }
      if (legalPosition[fSide][n].and(positionMask[charToSubscript('N')]) != 0) {
        m = p + knightCheck[k]
        if (board[m] == 0) {
          return true
        }
      }
    }
  }
  // 检测将是否被车攻击
  r = true
  for (i in 7..8) {
    p = piece[sideTag + i]
    if (p == 0) {
      continue
    }
    if (p % 16 == q % 16) {
      posAdd = if (p > q) -16 else 16
      // kotlin没有常规的(for(i = 0 i < a i++))
      var k: Int = (p + posAdd)
      while (k != q) {
        if (board[k] != 0) {
          r = false
          break
        }
        k += posAdd
      }
      if (r) {
        return r
      }
    } else if (p / 16 == q / 16) {
      posAdd = if (p > q) -1 else 1
      var k: Int = (p + posAdd)
      while (k != q) {
        if (board[k] != 0) {
          r = false
          break
        }
        k += posAdd
      }
      if (r) {
        return r
      }
    }
  }

  // 检测将是否被炮攻击
  var overFlag = 0
  for (i in 9..10) {
    p = piece[sideTag + i]
    if (p == 0) {
      continue
    }
    if (p % 16 == q % 16) {
      posAdd = if (p > q) -16 else 16
      var k: Int = (p + posAdd)
      while (k != q) {
        if (board[k] != 0) {
          if (overFlag == 0) {
            overFlag = 1
          } else {
            overFlag = 2
            break
          }
        }
        k += posAdd
      }
      if (overFlag == 1) {
        return true
      }
    } else if (p / 16 == q / 16) {
      posAdd = if (p > q) -1 else 1
      var k: Int = (p + posAdd)
      while (k != q) {
        if (board[k] != 0) {
          if (overFlag == 0) {
            overFlag = 1
          } else {
            overFlag = 2
            break
          }
        }
        k += posAdd
      }
      if (overFlag == 1) {
        return true
      }
    }
  }

  // 检测将是否被兵攻击
  for (i in 11..15) {
    p = piece[sideTag + i]
    if (p == 0) {
      continue
    }
    for (k in 0 until 3) {
      n = p + pawnDir[fSide][k]
      if ((n == q) && legalPosition[fSide][n].and(positionMask[charToSubscript('P')]) != 0) {
        return true
      }
    }
  }
  return false
}

/** 绝杀检测 */
fun checkGameEnd(lSide: Int) : Boolean {
  val sideTag = 32 - lSide * 16
  val p = piece[48 - sideTag]

  return p == 0
}