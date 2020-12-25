package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

@Composable
fun <T> LazyGridFor(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<List<T>>,
    rows: Int = 2,
    spaceBetweenItems: Dp,
    hPadding: Dp,
    vPadding: Dp,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.padding(
            horizontal = hPadding - spaceBetweenItems / 2,
            vertical = vPadding - spaceBetweenItems / 2
        )
    ) {
        items(pagingItems) { item ->
            Row {
                item?.forEach { photo ->
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .align(Alignment.Top)
                            .padding(spaceBetweenItems / 2),
                        contentAlignment = Alignment.Center
                    ) {
                        itemContent(photo, 0)
                    }
                }
                repeat(rows - (item?.size ?: 0)) {
                    Box(modifier = Modifier.weight(1F).padding(spaceBetweenItems / 2)) {}
                }
            }
        }
        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingState() }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingState() }
                }
                loadState.refresh is LoadState.Error -> {
                    item { ErrorState { refresh() } }
                }
                loadState.append is LoadState.Error -> {
                    item { ErrorState { refresh() } }
                }
            }
        }
    }
}