package com.ds

object VariableExample {
  def main(args: Array[String]): Unit = {
    // 声明不可变变量
    val immutableVariable: Int = 10
    println(s"Immutable Variable: $immutableVariable")

    // 声明可变变量
    var mutableVariable: Int = 20
    println(s"Mutable Variable: $mutableVariable")

    // 修改可变变量的值
    mutableVariable = 30
    println(s"Updated Mutable Variable: $mutableVariable")

    // 不可变变量不能被重新赋值，会导致编译错误
    // immutableVariable = 15 // Uncommenting this line will cause a compilation error
  }
}