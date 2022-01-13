package fr.khalypso.wordblitz.board

class Case(letter: Char) {

    val letter: Char;

    init {
        this.letter = letter.toUpperCase();
        this.validate()
    }

    private fun validate() {
        if(!letter.isLetter()){
            throw BoardException("Only letter are accepted within the board.")
        }
    }
}
