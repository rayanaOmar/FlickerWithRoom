package com.example.flickerbyroom

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerbyroom.details.Photo
import com.example.flickerbyroom.details.PhotoDetails
import com.example.flickerbyroom.retrofit.APIClient
import com.example.flickerbyroom.retrofit.APIInterface
import com.example.flickerbyroom.rvAdapter.RVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recycle: RecyclerView
    private lateinit var search: EditText
    private lateinit var btnSearch: Button


    private var images = ArrayList<Photo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycle = findViewById(R.id.rv)
        search = findViewById(R.id.edSearch)
        btnSearch = findViewById(R.id.button)

        recycle.adapter = RVAdapter(this, images)
        recycle.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            //Validate the user input
            if (search.text.isNotEmpty()) {
                requestAPI()
                search.text.clear()
                images.clear()
            } else {
                Toast.makeText(applicationContext, "Search is empty", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun requestAPI(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<PhotoDetails?>? = apiInterface!!.doGetListResources(search.text.toString())
        call?.enqueue(object : Callback<PhotoDetails?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<PhotoDetails?>,
                response: Response<PhotoDetails?>
            ) {
                for(photo in response.body()!!.photos.photo) {
                    //add to RV
                    images.add(photo)
                }
                recycle.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PhotoDetails?>, t: Throwable) {
                call.cancel()
            }
        })
    }

    //menu Section
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favView->{
                val intent = Intent(this, FavoritePage::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}