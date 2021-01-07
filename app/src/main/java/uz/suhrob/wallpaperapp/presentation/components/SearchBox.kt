package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onTextInputStarted: (SoftwareKeyboardController) -> Unit = {},
    actionSearch: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.padding(bottom = 4.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search)
        },
        placeholder = {
            Text(text = "Search")
        },
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Search) {
                actionSearch()
                softKeyboardController?.hideSoftwareKeyboard()
            }
        },
        onTextInputStarted = onTextInputStarted,
        singleLine = true,
        textStyle = textStyle,
        activeColor = MaterialTheme.colors.onPrimary
    )
}