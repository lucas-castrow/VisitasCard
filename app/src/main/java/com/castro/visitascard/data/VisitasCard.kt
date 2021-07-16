package com.castro.visitascard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VisitasCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val empresa: String,
    val telefone: String,
    val email: String,
    val background: String,
    val profile: String?
)
