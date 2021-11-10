package com.example.flickerbyroom.roomDatabase

import androidx.room.*


//this interface will add all operation i need to to on my database
@Dao
interface FlickerDao {
    //get me all the rows and arrangement by the title desc
    @Query("SELECT * FROM Flicker ORDER BY title DESC")
    fun getAllUserInfo(): List<Flicker>

    @Insert
    fun insertImg(img:  Flicker)

    @Delete
    fun deleteImg(img: Flicker)

}