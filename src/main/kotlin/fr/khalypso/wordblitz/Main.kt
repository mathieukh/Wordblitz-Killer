package fr.khalypso.wordblitz

import fr.khalypso.wordblitz.search.Solver
import java.io.File

fun main() {
    val dictionary = File("src/main/resources/touslesmots.txt").readLines().filter { it.isNotEmpty() }
    println("Number of words parsed: ${dictionary.count()}")
    println("Game characters:")
    val characters = readLine()!!.trim().toUpperCase().toCharArray();
    val gameMatrix = arrayOf(
        characters.copyOfRange(0, 4),
        characters.copyOfRange(4, 8),
        characters.copyOfRange(8, 12),
        characters.copyOfRange(12, 16)
    );
    println("Game matrix: ");
    println(gameMatrix.contentDeepToString());
    val solutions = Solver(dictionary, gameMatrix).findSolutions()
    val sorted = solutions.sortedBy { it.buildSolution().length }
    println("Number of words found: ${sorted.size}");
    sorted.forEach {
        println(it.buildSolution())
        it.displayPath()
    }
}