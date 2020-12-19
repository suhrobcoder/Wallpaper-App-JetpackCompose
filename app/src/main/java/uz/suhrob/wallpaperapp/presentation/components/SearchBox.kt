package uz.suhrob.wallpaperapp.presentation.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.ui.tooling.preview.Preview

@Composable
fun SearchBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    actionDone: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search)
        },
        onImeActionPerformed = { action, softKeyboardController ->
            if (action == ImeAction.Done) {
                actionDone()
                softKeyboardController?.hideSoftwareKeyboard()
            }
        },
        singleLine = true,
        textStyle = textStyle
    )
}

@Preview
@Composable
fun PreviewSearchBox() {
    SearchBox("", {})
}