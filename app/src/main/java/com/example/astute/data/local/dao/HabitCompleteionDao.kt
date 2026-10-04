package com.example.astute.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.astute.data.local.entities.HabitCompletion
import java.time.LocalDate

@Dao
interface HabitCompletionDao {

    @Insert
    suspend fun insertCompletion(completion: HabitCompletion): Long

    @Query("""
        SELECT * FROM habit_completions
        WHERE habitId = :habitId
        AND date = :date
    """)
    suspend fun getCompletion(
        habitId: Long,
        date: LocalDate
    ): HabitCompletion?

    @Query("""
        SELECT * FROM habit_completions
        WHERE habitId = :habitId
        ORDER BY date ASC
    """)
    suspend fun getCompletionsForHabit(
        habitId: Long
    ): List<HabitCompletion>

    @Query("""
        DELETE FROM habit_completions
        WHERE habitId = :habitId
        AND date = :date
    """)
    suspend fun deleteCompletion(
        habitId: Long,
        date: LocalDate
    )
}