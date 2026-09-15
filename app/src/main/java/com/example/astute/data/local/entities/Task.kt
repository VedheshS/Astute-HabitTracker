package com.example.astute.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val dueDate: LocalDate,
    val dueTime: LocalTime? = null,
    val isCompleted: Boolean = false,
    val completedAt: LocalDateTime? = null,
    val isDeleted: Boolean = false,
    val deletedAt: LocalDate,
    val remainderEnabled: Boolean = false,
    val remainderTime: LocalTime? = null,
    val createdAt: LocalDateTime

)
