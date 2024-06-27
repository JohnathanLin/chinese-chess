package com.windypath

import java.lang.Exception

/**
 * 启动类
 *
 * @Date 2021-01-05.
 * @author Johnathan Lin
 */
fun main() {
  println("开始游戏")
  startGame()
}

/** 开始游戏（命令行） */
fun startGame() {
  var cmd: String?
  showBoard()
  while (true) {
    println("请输入您下一步棋的坐标，先横后纵，先起始点后终止点，用空格分隔，如 8 2 8 5")
    try {
      cmd = readLine()
      if (cmd == null) {
        continue
      }
      if (cmd == "quit") {
        println("bye")
        break
      }
      val cmdSplitArr = cmd.trim().split(" ")
      val positionArr = isPieceChooseLegal(cmdSplitArr)

      val from = (positionArr[0] + 2).shl(4) + positionArr[1] + 2
      val to = (positionArr[2] + 2).shl(4) + positionArr[3] + 2
      isPiecePlayLegal(board[from], from, to)
      println("board[from]=${board[from]}, from=${from}, piece[board[from]]=${piece[board[from]]}, board[to]=${board[to]}, to=${to}")
      if (board[to] != 0) {
        piece[board[to]] = 0
      }
      piece[board[from]] = to
      board[to] = board[from]
      board[from] = 0
      side = 1 - side
      if (check(side)) {
          println("将军！")
      } else if (checkGameEnd(side)) {
        println("结束！")
        showBoard()
        break
      }
      showBoard()
    } catch (e: IllegalMoveException) {
      println("您的走法不合法，原因：" + e.message)
    } catch (e: IllegalInputException) {
      println("您的输入不合法，原因：" + e.message)
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }
  println("游戏结束")
}