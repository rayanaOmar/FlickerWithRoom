package com.example.flickerbyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerbyroom.roomDatabase.FlickerDatabase
import com.example.flickerbyroom.rvAdapter.RVAdapterFav

class FavoritePage : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var recyclerFav: RecyclerView
    private lateinit var databaseObject: FlickerDatabase

    private var title: String = ""
    private var link: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_page)

        recyclerFav = findViewById(R.id.rvFavorite)
        btnBack = findViewById(R.id.btnBack)

        //back to main Screen
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //these two value will dave into database
        title = intent.getStringExtra("title").toString()
        link = intent.getStringExtra("link").toString()

        databaseObject = FlickerDatabase.getInstant(applicationContext)

        updateRV()
    }
    //function section
    private fun updateRV(){
        val rv = databaseObject.FlickerDao().getAllUserInfo()

        recyclerFav.adapter = RVAdapterFav(this, rv)
        recyclerFav.layoutManager = LinearLayoutManager(this)
    }
}