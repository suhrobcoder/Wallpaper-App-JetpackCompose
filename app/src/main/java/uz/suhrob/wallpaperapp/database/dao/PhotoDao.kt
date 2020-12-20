package uz.suhrob.wallpaperapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.suhrob.wallpaperapp.database.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): List<PhotoEntity>

    @Query("DELETE FROM photos")
    fun clearTable()
}