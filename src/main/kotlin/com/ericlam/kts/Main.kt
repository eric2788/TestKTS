package com.ericlam.kts


import com.ericlam.kts.dsl.Add
import com.ericlam.kts.dsl.Create
import com.ericlam.kts.dsl.array
import com.ericlam.kts.dsl.invoke
import java.io.File
import javax.script.ScriptEngineManager

fun main() {

    val time = System.currentTimeMillis()
    val packages = """
        import kotlin.*
        import kotlin.annotation.*
        import kotlin.collections.*
        import kotlin.comparisons.*
        import kotlin.io.*
        import kotlin.ranges.*
        import kotlin.sequences.*
        import kotlin.text.*
        import java.lang.*
        import kotlin.jvm.*
        import kotlin.js.*
        import com.ericlam.kts.dsl.*
    """.trimIndent()
    System.setProperty("idea.use.native.fs.for.win", "false")
    val folder = File("kotscript")
    folder.mkdirs()
    val engine = ScriptEngineManager().getEngineByExtension("kts")
    folder.listFiles()?.forEach {
        println("=== ${it.name} ===")
        val kts = it.readLines().joinToString("\n")
        val script = "$packages\n$kts"
        engine.eval(script)
        println("=== ${it.name} ===")
    }
    println("kts Spent: ${((System.currentTimeMillis() - time) / 1000.0)}s")
    //dslTest()
}

object RegexPattern {
    val assignPattern = Regex("\\w\\s*=\\s*\\w")
    val globalAssignPattern = Regex("global private val\\s\\w\\s*=\\s*\\w")
}

fun dslTest() {
    Create array {
        Add num 1
        Add double 2.2
        Add string "abc"
        Add bool false
    }
    println("=== kotlin runtime ===")
    while (true) {
        val line = readLine() ?: continue
        if (line == "quit") return
        invoke(line)
    }

}