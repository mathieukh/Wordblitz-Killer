package fr.khalypso.wordblitz

import fr.khalypso.wordblitz.board.BoardFactory
import fr.khalypso.wordblitz.dictionary.DictionaryFactory
import fr.khalypso.wordblitz.solver.Solver
import fr.khalypso.wordblitz.solver.WordBlitzSearchState

fun main() {
    val dictionary = DictionaryFactory.fromFile("src/main/resources/touslesmots.txt");
    println("Number of words parsed: ${dictionary.count()}")
    println("Board construction: ")
    val board = BoardFactory.fromConsoleInput()
    println("Board: ")
    println(board)
    val solutions = Solver(dictionary, board).findSolutions()
    val sorted = solutions.sortedBy { it.size }
    println("Number of words found: ${sorted.size}");
    sorted.forEach { solution ->
        val finalState = solution.last.state
        if (finalState !is WordBlitzSearchState) {
            throw IllegalStateException("Unexpected state within the solution node")
        }
        println(finalState.boardState.pressedPath)
    }
}
