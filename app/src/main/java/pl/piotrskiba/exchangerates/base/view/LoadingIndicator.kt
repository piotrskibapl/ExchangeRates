package pl.piotrskiba.exchangerates.base.view

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.piotrskiba.exchangerates.base.viewmodel.ViewModelState

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier, state: ViewModelState, content: @Composable () -> Unit) {
    if (state == ViewModelState.LOADING) {
        CircularProgressIndicator(modifier = modifier)
    } else {
        content()
    }
}

@Preview
@Composable
fun LoadingIndicatorPreview() {
    LoadingIndicator(state = ViewModelState.LOADING) {}
}
