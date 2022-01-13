package fr.khalypso.wordblitz.search

import fr.khalypso.statesearchlib.model.Solution
import fr.khalypso.statesearchlib.model.algorithm.DfsAlgorithm
import fr.khalypso.wordblitz.Game
import java.util.*

class Solver(private val dictionary: List<String>, private val matrix: Array<CharArray>) {

    private val solvingAlgorithm = DfsAlgorithm()

    fun findSolutions(): List<Solution> {
        val solutions: MutableList<Solution> = ArrayList()
        for (wordToSearch in dictionary) {
            val game = Game(wordToSearch, matrix)

            val starts: MutableList<Pair<Int, Int>> = ArrayList()
            for (i in 0..3) {
                for (j in 0..3) {
                    if (game.matrix[i][j] == game.stringToSearch[0]) {
                        starts.add(Pair(i, j))
                    }
                }
            }
            for (startCursor in starts) {
                val possibleSolution = findSolution(Vertex(game, startCursor))
                if (possibleSolution.isPresent) {
                    solutions.add(possibleSolution.get())
                    break
                }
            }
        }
        return solutions
    }

    companion object {
        fun findSolution(vertex: Vertex): Optional<Vertex> {
            if (vertex.isLeaf) {
                return Optional.of(vertex)
            }
            for (next in vertex.findReachableVertices()) {
                val complete = findSolution(next)
                if (complete.isPresent) {
                    return complete
                }
            }
            return Optional.empty()
        }
    }
}