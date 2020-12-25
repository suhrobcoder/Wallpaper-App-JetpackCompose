package uz.suhrob.wallpaperapp.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import uz.suhrob.wallpaperapp.domain.model.Photo
import uz.suhrob.wallpaperapp.other.STARTING_PAGE
import uz.suhrob.wallpaperapp.other.TAG
import uz.suhrob.wallpaperapp.repository.PhotoRepository

class PhotoPagingSource(
    private val repository: PhotoRepository,
    private val isSearching: Boolean = false,
    private val query: String = ""
) : PagingSource<Int, List<Photo>>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<Photo>> {
        return try {
            val nextPage = params.key ?: STARTING_PAGE
            val response = if (isSearching)
                repository.search(query, nextPage, params.loadSize)
            else
                repository.curatedPhotos(perPage = params.loadSize, page = nextPage)
            Log.d(TAG, "$nextPage ${params.loadSize} $response")
            LoadResult.Page(
                data = response.chunked(2),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}