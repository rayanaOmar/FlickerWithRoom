package com.example.flickerbyroom

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerbyroom.roomDatabase.Flicker
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

        //return all the images saved into the database and show it in the recycler view
        databaseObject = FlickerDatabase.getInstant(applicationContext)

        updateRV()
    }
    //function section
    private fun updateRV(){
        val rv = databaseObject.FlickerDao().getAllUserInfo()

        recyclerFav.adapter = RVAdapterFav(this,rv)
        recyclerFav.layoutManager = LinearLayoutManager(this)
    }
    fun confirm(photo: Flicker) {
        var at= AlertDialog.Builder(this)
        at.setTitle("delete Image")
        at.setPositiveButton("Delete", DialogInterface.OnClickListener { dialogInterface, i ->
            deleteImg(photo)
        })
        at.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        at.show()

    }
     fun deleteImg(photo: Flicker) {
        //When user click on save icon the image will save to database
        val databaseObject = FlickerDatabase.getInstant(applicationContext)
        databaseObject.FlickerDao().deleteImg(photo)
        updateRV()
        //show Toast
        Toast.makeText(applicationContext, "Delete Successfully!!", Toast.LENGTH_SHORT).show()
    }
}