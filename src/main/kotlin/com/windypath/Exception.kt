package com.windypath

/**
 * 定义异常
 *
 * @Date 2021-02-10.
 * @author Johnathan Lin
 */
/** 基础异常类 */
open class BaseException : RuntimeException {
  constructor() : super() {}
  constructor(message: String?, cause: Throwable?) : super(message, cause) {}
  constructor(message: String?) : super(message) {}
  constructor(cause: Throwable?) : super(cause) {}
}

/** 走法不合法 */
class IllegalMoveException(message: String?) : BaseException(message) {}

/** 输入的坐标不合法 */
class IllegalInputException(message: String?) : BaseException(message) {}

/** 选择的棋子不合法 */
class IllegalPieceException(message: String?) : BaseException(message) {}