Common println "hello world"

val arr = Create array {
    Add num 1
    Add num 3
    Add double 2.0
    Add string "abc"
    Add string "xyz"
    Add bool false
}

Common println arr

val pickme = 123

val map = Create map {
    1 value "abc"
    2 value 333
    1.2 value "abc"
    "a" value "xyz"
    "ww" value 222
    "d" value false
    false value "xxx"
}

Common println map



/*
Register command "hello" execute  {
    val name = this get 1 or "noone"
    Common println "Hello $name !!"
}

Register command "array" execute {
    Common println arr
}

 */

