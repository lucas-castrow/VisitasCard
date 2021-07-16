package com.castro.visitascard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.castro.visitascard.App
import com.castro.visitascard.databinding.ActivityMainBinding
import com.castro.visitascard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val mainViewModel: MainViewModel by viewModels{
            MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy{VisitasCardAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerCards.adapter = adapter
        getAllVisitasCard()
        setListeners()
    }

    private fun setListeners(){
        binding.btAddCard.setOnClickListener {
            val intent = Intent(this@MainActivity,AddVisitasCardActivity::class.java)
            startActivity(intent)

        }
        adapter.listenerShare = {
            Image.share(this@MainActivity,  it)
        }
    }

    private fun getAllVisitasCard(){
        mainViewModel.getAll().observe(this){
            adapter.submitList(it)
        }
    }
}