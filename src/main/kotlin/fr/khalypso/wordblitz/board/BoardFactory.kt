package fr.khalypso.wordblitz.board

class BoardFactory {

    companion object {
        fun fromConsoleInput(): Board {
            val cases = readLine()!!.trim().toCharArray().map { Case(it) }
            val gameMatrix = listOf(
                cases.subList(0, 4),
                cases.subList(4, 8),
                cases.subList(8, 12),
                cases.subList(12, 16)
            )
            return Board(gameMatrix)
        }
        fun fromString(letters: String): Board {
            val cases = letters.toCharArray().map { Case(it) }
            val gameMatrix = listOf(
                cases.subList(0, 4),
                cases.subList(4, 8),
                cases.subList(8, 12),
                cases.subList(12, 16)
            )
            return Board(gameMatrix)
        }
    }
}
