package com.ericlam.kts


import com.ericlam.kts.dsl.invoke
import java.io.File
import java.util.regex.Pattern
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
        var kts = it.readLines().joinToString("\n")
        RegexPattern.assignPattern.findAll(kts).forEach {mr ->
            kts = kts.replace(mr.value, "private val ${mr.value}")
        }
        RegexPattern.globalAssignPattern.findAll(kts).forEach {mr ->
            kts = kts.replace(mr.value, mr.value.removePrefix("global private "))
        }
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
    println("=== kotlin runtime ===")
    while (true) {
        val line = readLine() ?: continue
        if (line == "quit") return
        invoke(line)
    }

}