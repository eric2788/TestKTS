this pool {
    "a" set "abc"
    "b" set "bcd"
    "c" set "xyz"
}

Common println "a = ${"a" from this}"

Common println "b = ${"b" from this}"

Common println "c = ${"c" from this}"

val a = 1..10 random 2
a loop {
    val b = this > 5
    b then {
        Common println "$this is > 5"
    } elseThen {
        Common println "$this is <= 5"
    }
}