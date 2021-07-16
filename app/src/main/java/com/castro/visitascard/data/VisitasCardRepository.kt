package com.castro.visitascard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class VisitasCardRepository(private val dao: VisitasCardDAO){

    fun insert(visitasCard: VisitasCard) = runBlocking{
        launch(Dispatchers.IO){
            dao.insert(visitasCard)
        }
    }

    fun getAll() = dao.getAll()


}