package fr.khalypso.wordblitz.solver

import fr.khalypso.statesearchlib.model.Solution
import fr.khalypso.statesearchlib.model.algorithm.DfsAlgorithm
import fr.khalypso.statesearchlib.model.exception.NoSolutionException
import fr.khalypso.wordblitz.board.Board
import fr.khalypso.wordblitz.dictionary.Words
import java.util.*

class Solver(private val words: Words, private val board: Board) {

    private val solvingAlgorithm = DfsAlgorithm()

    fun findSolutions(): List<Solution> {
        val solutions: MutableList<Solution> = ArrayList()
        words.searchedWords.parallelStream().forEach { wordToSearch ->
            val boardState = BoardState(board, listOf())
            val initialSearchState = WordBlitzSearchState(boardState, wordToSearch)
            try {
                val possibleSolution = solvingAlgorithm.findSolution(initialSearchState)
                solutions += possibleSolution
            } catch (_: NoSolutionException) {
                // Unable to find the word in the board
            }
        }
        return solutions
    }

}
