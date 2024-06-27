package com.windypath

/**
 * 局面展示（命令行版）
 *
 * @Date 2021-01-31.
 * @author Johnathan Lin
 */

/** 棋子数字转为相应汉字 */
fun pieceToShow(piece: Int): String {
  if (piece < 32) {
    return when (piece) {
      16 -> "帅"
      17, 18 -> "仕"
      19, 20 -> "相"
      21, 22 -> "傌"
      23, 24 -> "俥"
      25, 26 -> "炮"
      27, 28, 29, 30, 31 -> "兵"
      else -> "口"
    }
  } else {
    return when (piece) {
      32 -> "将"
      33, 34 -> "士"
      35, 36 -> "象"
      37, 38 -> "马"
      39, 40 -> "车"
      41, 42 -> "包"
      43, 44, 45, 46, 47 -> "卒"
      else -> "口"
    }
  }
}

val map = hashMapOf(
  1 to "一",
  2 to "二",
  3 to "三",
  4 to "四",
  5 to "五",
  6 to "六",
  7 to "七",
  8 to "八",
  9 to "九"
)

/** 展示局面 */
fun showBoard() {
  print("  ")
  for (j in 3 until 12) {
    print(map[(j-2)].toString() + " ")
  }
  println()
  for (i in 3 until 13) {
    print((i-2).toString())
    if (i != 12) {
      print(" ")
    }
    for (j in 3 until 12) {
      print( pieceToShow(board[i.shl(4) + j]) + " ")
    }
    println()
  }
}

