package com.ericlam.kts

fun main() {
    var kts = """
        a = 1
        println a
        a = 2
        c = 3
        
        global b = 20
    """.trimIndent()

    RegexPattern.assignPattern.findAll(kts).forEach {mr ->
        kts = kts.replace(mr.value, "private val ${mr.value}")
    }
    RegexPattern.globalAssignPattern.findAll(kts).forEach {mr ->
        kts = kts.replace(mr.value, mr.value.removePrefix("global private "))
    }
    println(kts)
}