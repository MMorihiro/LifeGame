package com.github.mmorihiro.core


internal class MakeNewGeneration {
    fun get(array: Array<BooleanArray>): Array<BooleanArray> {
        val copiedArray = copy(array)
        for (i in array.indices) {
            for (j in array[i].indices) {
                val pair = Pair(array[i][j], countSurroundings(array, i, j))

                when (pair) {
                    Pair(false, 3) -> copiedArray[i][j] = true
                    Pair(true, 2), Pair(true, 3) -> copiedArray[i][j] = true
                    Pair(true, 0), Pair(true, 1) -> copiedArray[i][j] = false
                    else ->
                        if (pair.first == true && pair.second >= 4)
                            copiedArray[i][j] = false
                }
            }
        }

        return copiedArray
    }

    private fun copy(array: Array<BooleanArray>): Array<BooleanArray> {
        val copy = Array(array.size) { BooleanArray(array[0].size) { false } }
        for (i in array.indices) {
            for (j in array[i].indices) {
                copy[i][j] = array[i][j]
            }
        }
        return copy
    }

    private fun countSurroundings(array: Array<BooleanArray>,
                                  i: Int, j: Int): Int {
        var surroundings = 0
        for (y in i - 1..i + 1) {
            for (x in j - 1..j + 1) {
                when {
                    !(y == i && x == j) &&
                        array.indices.contains(y) &&
                        array[y].indices.contains(x) &&
                        array[y][x] -> surroundings += 1
                }
            }
        }
        return surroundings
    }
}