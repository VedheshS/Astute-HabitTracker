package com.example.astute.data.repository
import android.content.Context
import com.example.astute.data.local.DatabaseProvider
object RepositoryProvider {
    fun getHabitRepository(context: Context): HabitRepository {
        val database = DatabaseProvider.getDatabase(context)
        return HabitRepository( habitDao = database.habitDao() )
    }
    fun getHabitCompletionRepository( context: Context ): HabitCompletionRepository {
        val database = DatabaseProvider.getDatabase(context)
        return HabitCompletionRepository( habitCompletionDao = database.habitCompletionDao() )
    }
    fun getTaskRepository(context: Context): TaskRepository {
        val database = DatabaseProvider.getDatabase(context)
        return TaskRepository( taskDao = database.taskDao() )
    }
}