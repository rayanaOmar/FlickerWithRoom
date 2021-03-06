package com.example.flickerbyroom.roomDatabase

import androidx.room.*

@Entity(tableName = "Flicker") // name of the table of database
class Flicker (
    @PrimaryKey(autoGenerate = true) // the primary key of the row
    //the 3 column in my database
    @ColumnInfo(name = "id")val id: Int = 0 ,
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "link")val link: String

    )