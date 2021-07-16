package com.castro.visitascard

import android.app.Application
import com.castro.visitascard.data.AppDatabase
import com.castro.visitascard.data.VisitasCardRepository

class App: Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { VisitasCardRepository(database.visitasDAO()) }
}