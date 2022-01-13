package fr.khalypso.wordblitz.search

import fr.khalypso.statesearchlib.model.Operator
import fr.khalypso.statesearchlib.model.State
import fr.khalypso.wordblitz.Game

class WordblitzState(private val game: Game, private val path: List<Pair<Int, Int>>) : State {

    override fun isTerminal(): Boolean {
        return path.size == game.stringToSearch.length
    }

    override fun operators(): List<Operator> {
        return null
    }

    class MoveOperator : Operator {
        override fun getCost(): Int = 0

        override fun apply(p0: State?): State {
        }

    }

}