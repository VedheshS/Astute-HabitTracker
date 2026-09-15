package com.example.astute.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "habits")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    val description: String? = null,

    val icon: String,

    val color: String,

    val frequency: Frequency,

    val scheduleDays: List<Int> = emptyList(),

    val startDate: LocalDate,

    val endDate: LocalDate? = null,

    val isDeleted: Boolean = false,

    val deletedAt: LocalDate? = null,

    val reminderEnabled: Boolean = false,

    val reminderTime: LocalTime? = null,

    val createdAt: LocalDateTime
)