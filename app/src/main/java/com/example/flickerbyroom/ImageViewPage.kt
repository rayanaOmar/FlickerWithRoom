package com.example.flickerbyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.flickerbyroom.roomDatabase.Flicker
import com.example.flickerbyroom.roomDatabase.FlickerDatabase

class ImageViewPage : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var btnBack: Button

    private var title: String = ""
    private var link: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        imageView = findViewById(R.id.imageView)
        btnBack = findViewById(R.id.btnBack)

        //back to main Screen
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        title = intent.getStringExtra("title").toString()
        link = intent.getStringExtra("link").toString()

        Glide.with(this)
            .load(link)
            .into(imageView)
    }

    //function section
    private fun saveImage(title: String, link: String){
        //When user click on save icon the image will save to database
        val databaseObject = FlickerDatabase.getInstant(applicationContext)
        databaseObject.FlickerDao().insertImg(Flicker(0,title,link))
        //show Toast
        Toast.makeText(applicationContext, "Save Successfully!!", Toast.LENGTH_SHORT).show()
    }

    //menu section
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save-> {
                saveImage(title,link)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}