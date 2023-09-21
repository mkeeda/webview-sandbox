package dev.mkeeda.webview_sandbox

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WebViewModel: ViewModel() {
    private val _currentUrl = MutableStateFlow(Uri.parse("https://www.google.com"))
    val url: StateFlow<Uri> = _currentUrl.asStateFlow()

    fun onPageLoad(newUrl: Uri) {
        _currentUrl.update {  _ ->
            //URLでWebViewの挙動をコントロールする例
            if (newUrl.authority?.contains("cybozu.com") == true) {
                Uri.parse("https://www.google.com")
            } else {
               newUrl
            }
        }
    }
}
