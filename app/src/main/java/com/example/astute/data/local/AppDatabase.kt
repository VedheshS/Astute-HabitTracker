package com.example.astute.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.astute.data.local.converters.Converters
import com.example.astute.data.local.dao.HabitCompletionDao

import com.example.astute.data.local.dao.HabitDao
import com.example.astute.data.local.dao.TaskDao
import com.example.astute.data.local.entities.Habit
import com.example.astute.data.local.entities.HabitCompletion
import com.example.astute.data.local.entities.Task

@Database(
    entities = [
        Habit :: class,
        HabitCompletion::class,
        Task::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun habitDao(): HabitDao
    abstract fun habitCompletionDao(): HabitCompletionDao
    abstract fun taskDao(): TaskDao
}
