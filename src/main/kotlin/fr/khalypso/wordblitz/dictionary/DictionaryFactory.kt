package fr.khalypso.wordblitz.dictionary

import java.io.File

class DictionaryFactory {

    companion object {
        fun fromFile(fileName: String): Words {
            val fileLines = File(fileName).readLines()
            return Words(fileLines)
        }
    }
}
