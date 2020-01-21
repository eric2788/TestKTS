package com.ericlam.kts

import java.util.*

fun main() {
    val lam = Player("Eric Lam", UUID.nameUUIDFromBytes("eric".toByteArray()))
    val tim = Player("Tim Lam", UUID.nameUUIDFromBytes("tim".toByteArray()))
    println("name" of lam)
    println("name" of tim eq "Tim Lam")

}

data class Player(val name: String, val uniqueId: UUID)

infix fun String.of(any: Any): Any {
    return any::class.java.getDeclaredField(this).also { it.isAccessible = true }.get(any)
            ?: throw IllegalStateException("no field $this in $any")
}

infix fun Any.eq(any: Any): Boolean {
    return this == any
}