package com.example.astute.data.local.converters

import androidx.room.TypeConverters
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import com.example.astute.data.local.entities.Frequency

class Converters {
    @TypeConverters
    fun fromLocalDate(date: LocalDate): String{
        return date.toString();
    }

    @TypeConverters
    fun toLocalDate(date: String): LocalDate{
        return LocalDate.parse(date);
    }

    @TypeConverters
    fun toLocalTime(time: String): LocalTime{
        return LocalTime.parse(time);
    }
    @TypeConverters
    fun fromLocalTime(time: LocalTime) : String{
        return time.toString();
    }


    @TypeConverters
    fun fromLocalDateTime(dateTime: LocalDateTime): String{
        return dateTime.toString();
    }
    @TypeConverters
    fun toLocalDateTime(dateTime: String): LocalDateTime{
        return LocalDateTime.parse(dateTime);
    }

    @TypeConverters
    fun fromFrequency(frequency: Frequency): String{
        return frequency.name;
    }
    @TypeConverters
    fun toFrequency(value: String): Frequency{
        return Frequency.valueOf(value);
    }

    @TypeConverters
    fun fromIntList(list: List<Int>): String{
        return list.joinToString(",");
    }
    @TypeConverters
    fun toIntList(list: String): List<Int>{
        if(list.isBlank()) return emptyList();
        return list.split(",").map{it.toInt()};
    }
}