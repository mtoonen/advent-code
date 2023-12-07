package nl.meine.aoc._2023

class Day6 {

    fun one(timeString: List<Int>, distance:List<Int>): Int {
        val times = timeString
        val distances = distance
        var total = 1;
        times.forEachIndexed{index,time->
            run {
                val timeToBeat = distances[index]
                var countSucceeded = 0
                for (i in 1..<time) {
                    val distanceTravelled = (time - i) * i
                    if (distanceTravelled > timeToBeat) {
                        countSucceeded++
                    }
                }
                total *= countSucceeded
            }
        }
        return total
    }


    fun two(time: Long, distance:Long): Long {
        val lines = input.split("\n")
        var countSucceeded:Long = 0
        for (i in 1..<time) {
            val distanceTravelled:Long = (time - i) * i
            if (distanceTravelled > distance) {
                countSucceeded++
            }
        }
        return countSucceeded
    }

}

fun main() {
    val time =  listOf(62,     64,     91,     90)
    val dist =  listOf(553,   1010,   1473,   1074)
    val ins = Day6();
    //println(ins.one(time,dist))
    println(ins.two(62649190,553101014731074))

}
