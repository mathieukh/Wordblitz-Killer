package fr.khalypso.wordblitz.dictionary

class Words(words: List<String>) {

    val searchedWords: List<String>

    init {
        this.searchedWords =
            words.filter { it.isNotBlank() }.filter { it.toCharArray().all { character -> character.isLetter() } }
                .map { it.toUpperCase() }
        if (this.searchedWords.isEmpty()) {
            throw DictionaryException("Dictionary is empty. No solution won't be found")
        }
    }

    fun count(): Int {
        return this.searchedWords.count();
    }

}
