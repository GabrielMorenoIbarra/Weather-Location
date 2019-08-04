package com.gabrielmorenoibarra.weatherlocation.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gabrielmorenoibarra.weatherlocation.domain.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var instance: WordRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): WordRoomDatabase {
            return instance ?: synchronized(this) {
                val instance =
                        Room.databaseBuilder(
                                context.applicationContext,
                                WordRoomDatabase::class.java,
                                "word_database")
                                .fallbackToDestructiveMigration()
                                .addCallback(WordDatabaseCallback(scope))
                                .build()
                Companion.instance = instance
                instance
            }
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope)
            : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                instance?.let { database ->
                    scope.launch {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()

            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }
    }
}
