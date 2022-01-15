package fr.khalypso.wordblitz.solver

import fr.khalypso.statesearchlib.model.Operator
import fr.khalypso.statesearchlib.model.State

class WordBlitzSearchState(val boardState: BoardState, private val word: String) : State {

    override fun isTerminal(): Boolean {
        return boardState.wordDrawn == word
    }

    override fun operators(): List<Operator> {
        val accessibleCases = boardState.accessibleCases
        val nextLetterIndex = boardState.pressedPath.size
        if (word.length == nextLetterIndex) {
            return listOf()
        }
        val nextLetter = word[nextLetterIndex]
        val casesWithCorrectLetter = accessibleCases
            .filter { casePosition -> boardState.board.caseAt(casePosition).letter == nextLetter }
        return casesWithCorrectLetter.map { case -> NewLetterOperator(case) }
    }

    class NewLetterOperator(private val nextCasePosition: CasePosition) : Operator {

        override fun getCost(): Int {
            return 0
        }

        override fun apply(state: State?): State {
            if (state !is WordBlitzSearchState) {
                throw IllegalStateException("This operator can only be applied on a WordBlitzSearchState")
            }
            return WordBlitzSearchState(state.boardState.moveFinger(nextCasePosition), state.word)
        }

    }

}
