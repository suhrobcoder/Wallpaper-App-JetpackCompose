package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun <T> LazyGridFor(
    modifier: Modifier = Modifier,
    items: List<T> = listOf(),
    rows: Int = 2,
    spaceBetweenItems: Dp,
    hPadding: Dp,
    vPadding: Dp,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(rows)
    LazyColumnForIndexed(
        items = chunkedList,
        modifier = modifier.padding(
            horizontal = hPadding - spaceBetweenItems / 2,
            vertical = vPadding - spaceBetweenItems / 2
        )
    ) { index, it ->
        Row {
            it.forEachIndexed { rowIndex, item ->
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .align(Alignment.Top)
                        .padding(spaceBetweenItems / 2),
                    contentAlignment = Alignment.Center
                ) {
                    itemContent(item, index * rows + rowIndex)
                }
            }
            repeat(rows - it.size) {
                Box(modifier = Modifier.weight(1F).padding(spaceBetweenItems / 2)) {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewLazyGridFor() {
    LazyGridFor(
        items = listOf("B1", "B2", "B3", "B4", "B5", "B6", "B7"),
        spaceBetweenItems = 16.dp,
        hPadding = 32.dp,
        vPadding = 16.dp
    ) { item, index ->
        Button(onClick = {}, modifier = Modifier.fillMaxSize()) {
            Text(text = item)
        }
    }
}