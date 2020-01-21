private val a = 1..10 random 2 //create iterable and get random of 2 numbers
a loop {
    val b = this > 5
    b then {
        Common println "$this is > 5"
    } elseThen {
        Common println "$this is <= 5"
    }
}

1..10 loop {
    Common println "No. $this"
}