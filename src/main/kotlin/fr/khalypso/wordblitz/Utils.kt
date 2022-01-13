package fr.khalypso.wordblitz

import java.util.stream.Collectors

class Utils {

    companion object {

        fun filterDictionary(dictionary: List<String>, letters: CharArray): List<String> {
            val lettersAvailable = countLetters(letters);
            return dictionary.stream()
                .filter { word ->
                    val wordLettersCount = countLetters(word.toCharArray());
                    // Ensure that all the letters in the word are present considering the letters given
                    wordLettersCount.entries.all { wordLetter ->
                        lettersAvailable.getOrDefault(
                            wordLetter.key,
                            0
                        ) >= wordLetter.value
                    }
                }
                .collect(Collectors.toList());
        }

        private fun countLetters(letters: CharArray): Map<Char, Int> {
            return letters.map {  it to letters.count { c -> c == it } }.toMap();
        }

    }

}