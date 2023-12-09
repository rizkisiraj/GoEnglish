package com.example.learningapp.data

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Activity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Perform the necessary migration steps here
                // For example, you can execute SQL statements
                database.execSQL("ALTER TABLE Activities ADD COLUMN chapter TEXT NULL")
                database.execSQL("ALTER TABLE Activities ADD COLUMN name TEXT NULL")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "local_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}