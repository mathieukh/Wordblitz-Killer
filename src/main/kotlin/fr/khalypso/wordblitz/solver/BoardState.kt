package fr.khalypso.wordblitz.solver

import fr.khalypso.wordblitz.board.BOARD_COLUMNS
import fr.khalypso.wordblitz.board.BOARD_ROWS
import fr.khalypso.wordblitz.board.Board

typealias CasePosition = Pair<Int, Int>

data class BoardState(val board: Board, val pressedPath: List<CasePosition>) {

    val wordDrawn: String
        get() {
            return pressedPath.map { board.caseAt(it) }.map { it.letter }.joinToString("")
        }

    val accessibleCases: List<CasePosition>
        get() {
            val accessibleCases = mutableListOf<CasePosition>()
            if (pressedPath.isEmpty()) {
                for (rowIndex in 0 until BOARD_ROWS) {
                    for (columnIndex in 0 until BOARD_COLUMNS) {
                        accessibleCases += CasePosition(rowIndex, columnIndex)
                    }
                }
                return accessibleCases
            }
            val currentPosition = pressedPath.last()
            val row = currentPosition.first
            val column = currentPosition.second
            for (i in -1..1) {
                val positionR = row + i
                if (positionR < 0 || positionR >= BOARD_ROWS) {
                    continue
                }
                for (j in -1..1) {
                    val positionC = column + j
                    if (positionC < 0 || positionC >= BOARD_COLUMNS) {
                        continue
                    }
                    val nextPosition = CasePosition(positionR, positionC)
                    if (pressedPath.contains(nextPosition)) {
                        continue
                    }
                    accessibleCases += nextPosition
                }
            }
            return accessibleCases.toList()
        }

    fun moveFinger(casePosition: CasePosition): BoardState {
        return BoardState(board, pressedPath + casePosition)
    }

}
