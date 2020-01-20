package com.ericlam.kts.dsl

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

private val commands: ConcurrentMap<String, Array<String>.() -> Unit> = ConcurrentHashMap()

object Register

object Create

object Common

infix fun Common.println(any: Any?) {
    kotlin.io.println(any)
}

infix fun Common.print(any: Any?) {
    kotlin.io.print(any)
}

infix fun Register.command(cmd: String): CommandBuilder {
    return CommandBuilder(cmd)
}

data class CommandBuilder(val name: String)

infix fun CommandBuilder.execute(runner: Array<String>.() -> Unit) {
    commands[this.name] = runner
}

infix fun Create.array(run: ListBuilder.() -> Unit): MutableList<Any> {
    val list = ListBuilder()
    run(list)
    return list
}

infix fun Create.map(run: MapBuilder.() -> Unit): MutableMap<Any, Any> {
    val map = MapBuilder()
    run(map)
    return map
}

object Add

infix fun String.set(any: Any){

}


class MapBuilder : LinkedHashMap<Any, Any>() {
    infix fun Any.value(any: Any) {
        this@MapBuilder += (this to any)
    }
}

class ListBuilder : ArrayList<Any>() {
    infix fun Add.num(int: Int) {
        add(int)
    }

    infix fun Add.double(dou: Double) {
        add(dou)
    }

    infix fun Add.string(str: String) {
        add(str)
    }

    infix fun Add.bool(bool: Boolean) {
        add(bool)
    }
}

infix fun <T> Array<out T>.get(index: Int): T? {
    return this.getOrNull(index - 1)
}

infix fun <T> Iterable<T>.loop(run: T.() -> Unit) {
    this.forEach(run)
}

infix fun <T> Array<out T>.loop(run: T.() -> Unit) {
    this.forEach(run)
}

infix fun <T> Collection<T>.get(index: Int): T? {
    return this.elementAtOrNull(index - 1)
}

infix fun <T> T?.or(def: T): T {
    return this ?: def
}


fun invoke(line: String) {
    val arr = line.split(" ")
    val cmd = arr[0]
    val params = arr.subList(1, arr.size)
    commands[cmd]?.invoke(params.toTypedArray())
}
