package fr.khalypso.wordblitz.board

import fr.khalypso.wordblitz.solver.CasePosition

const val BOARD_ROWS = 4
const val BOARD_COLUMNS = 4

class Board(private val matrix: List<List<Case>>) {

    init {
        this.validate()
    }

    private fun validate() {
        val rowsCount = this.matrix.size
        if (rowsCount != BOARD_ROWS) {
            throw BoardException("Wrong number of rows: Expected: $BOARD_ROWS, Actual: $rowsCount")
        }
        this.matrix.forEach { row ->
            val columnsCount = row.size;
            if (columnsCount != BOARD_ROWS) {
                throw BoardException("Wrong number of columns: Expected: $BOARD_COLUMNS, Actual: $columnsCount")
            }
        }
    }

    fun caseAt(casePosition: CasePosition): Case {
        return matrix[casePosition.first][casePosition.second]
    }

    override fun toString(): String {
        return this.matrix.joinToString(separator = "\n", transform = { rowCases ->
            rowCases.joinToString(separator = "", transform = { case -> case.letter.toString() })
        })
    }

}
