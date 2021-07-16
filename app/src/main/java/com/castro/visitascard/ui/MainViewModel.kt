package com.castro.visitascard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.castro.visitascard.data.VisitasCard
import com.castro.visitascard.data.VisitasCardRepository

class MainViewModel(private val visitasCardRepository: VisitasCardRepository): ViewModel() {

    fun insert(visitasCard: VisitasCard){
        visitasCardRepository.insert(visitasCard)
    }

    fun getAll(): LiveData<List<VisitasCard>>  {
        return visitasCardRepository.getAll()
    }

}

class MainViewModelFactory(private val repository: VisitasCardRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

      if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
          @Suppress("UNCHECKED_CAST")
          return MainViewModel(repository) as T
      }
      throw IllegalArgumentException("Unknown ViewModel Class")
    }


}
