package uz.suhrob.wallpaperapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.suhrob.wallpaperapp.database.dao.PhotoDao
import uz.suhrob.wallpaperapp.database.entity.PhotoEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [PhotoEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
}