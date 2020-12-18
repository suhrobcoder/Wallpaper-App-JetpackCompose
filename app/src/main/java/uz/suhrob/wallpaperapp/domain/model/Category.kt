package uz.suhrob.wallpaperapp.domain.model

data class Category(
    val title: String,
    val photoUrl: String
) {
    companion object {
        val categories = listOf(
            Category(
                title = "Street Art",
                photoUrl = "https://images.pexels.com/photos/545008/pexels-photo-545008.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Nature",
                photoUrl = "https://images.pexels.com/photos/15286/pexels-photo.jpg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Christmas",
                photoUrl = "https://images.pexels.com/photos/5728316/pexels-photo-5728316.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Colors",
                photoUrl = "https://images.pexels.com/photos/40799/paper-colorful-color-loose-40799.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Wild Life",
                photoUrl = "https://images.pexels.com/photos/63325/grizzly-bears-playing-sparring-63325.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "City",
                photoUrl = "https://images.pexels.com/photos/466685/pexels-photo-466685.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Motivation",
                photoUrl = "https://images.pexels.com/photos/1434819/pexels-photo-1434819.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            ),
            Category(
                title = "Cars",
                photoUrl = "https://images.pexels.com/photos/1149137/pexels-photo-1149137.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
            )
        )
    }
}