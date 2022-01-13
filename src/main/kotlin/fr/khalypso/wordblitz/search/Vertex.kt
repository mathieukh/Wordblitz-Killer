package fr.khalypso.wordblitz.search

import fr.khalypso.wordblitz.Game

class Vertex {
    private val game: Game
    private val path: List<Pair<Int, Int>>

    constructor(game: Game, cursorPosition: Pair<Int, Int>) {
        this.game = game
        this.path = mutableListOf(cursorPosition)
    }

    private constructor(vertex: Vertex, cursorPosition: Pair<Int, Int>) {
        this.game = vertex.game
        this.path = mutableListOf(cursorPosition) + vertex.path.toMutableList();
    }

    fun findReachableVertices(): List<Vertex> {
        val reachableVertices: MutableList<Vertex> = mutableListOf();
        val (row, column) = path[0]
        for (i in -1..1) {
            val positionR = row + i
            if (positionR < 0 || positionR > 3) {
                continue
            }
            for (j in -1..1) {
                val positionC = column + j
                if (positionC < 0 || positionC > 3) {
                    continue
                }
                val nextCursor = Pair(positionR, positionC)
                if (path.contains(nextCursor)) {
                    continue
                }
                val indexToCheck = path.size
                if (game.stringToSearch[indexToCheck] == game.matrix[positionR][positionC]) {
                    reachableVertices += Vertex(this, nextCursor)
                }
            }
        }
        return reachableVertices
    }

    val isLeaf: Boolean
        get() = path.size == game.stringToSearch.length

    fun buildSolution(): String {
        val stringBuilder = StringBuilder()
        var i = path.size
        while (i-- > 0) {
            val (first, second) = path[i]
            stringBuilder.append(game.matrix[first][second])
        }
        return stringBuilder.toString()
    }

    fun displayPath() {
        val size = path.size
        (0..3).forEach { i ->
            (0..3).forEach { j ->
                val cursor = Pair(i, j)
                val index = path.indexOf(cursor)
                print(" ")
                print(if (index == -1) "-" else size - 1 - index + 1)
                print(" ")
            }
            println()
        }
    }
}