package com.ds

import scala.util.Try

object DataTypeExamples {
  def main(args: Array[String]): Unit = {
    // 基本类型
    val byteValue: Byte = 127
    val shortValue: Short = 32767
    val intValue: Int = 2147483647
    val longValue: Long = 9223372036854775807L
    val floatValue: Float = 3.14f
    val doubleValue: Double = 3.141592653589793
    val charValue: Char = 'A'
    val stringValue: String = "Hello, Scala!"
    val booleanValue: Boolean = true

    // 集合类型
    val listValue: List[Int] = List(1, 2, 3)
    val setValue: Set[String] = Set("Scala", "Java", "Python")
    val mapValue: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)
    val arrayValue: Array[Int] = Array(4, 5, 6)
    val tupleValue: (Int, String, Boolean) = (42, "Answer", true)
    val optionValue: Option[String] = Some("I am here")
    val eitherValue: Either[String, Int] = Right(42)
    val tryValue: Try[Int] = Try(10 / 2)

    // 特殊类型
    val unitValue: Unit = ()
    val nullValue: String = null
//    val nothingValue: Nothing = throw new RuntimeException("Nothing value")

    // 输出所有值
    println(s"Byte Value: $byteValue")
    println(s"Short Value: $shortValue")
    println(s"Int Value: $intValue")
    println(s"Long Value: $longValue")
    println(s"Float Value: $floatValue")
    println(s"Double Value: $doubleValue")
    println(s"Char Value: $charValue")
    println(s"String Value: $stringValue")
    println(s"Boolean Value: $booleanValue")

    println(s"List Value: $listValue")
    println(s"Set Value: $setValue")
    println(s"Map Value: $mapValue")
    println(s"Array Value: ${arrayValue.mkString(", ")}")
    println(s"Tuple Value: $tupleValue")
    println(s"Option Value: $optionValue")
    println(s"Either Value: $eitherValue")
    println(s"Try Value: $tryValue")

    println(s"Unit Value: $unitValue")
    println(s"Null Value: $nullValue")

    // nothingValue is not printed because it throws an exception
  }
}