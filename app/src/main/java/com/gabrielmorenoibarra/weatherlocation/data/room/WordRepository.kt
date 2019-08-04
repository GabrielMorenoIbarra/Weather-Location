package com.gabrielmorenoibarra.weatherlocation.data.room

import androidx.lifecycle.LiveData
import com.gabrielmorenoibarra.weatherlocation.domain.model.Word

class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}
