package dev.mkeeda.webview_sandbox

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class WebViewModel: ViewModel() {
    private val _currentUrl = MutableStateFlow(Uri.parse("https://www.google.com"))
    val url: Flow<Uri> = _currentUrl.map { uri ->
        //URLでWebViewの挙動をコントロールする例
        if (uri.authority?.contains("cybozu.com") == true) {
            Uri.parse("https://www.google.com")
        } else {
            uri
        }
    }

    fun onPageLoad(url: Uri) {
        _currentUrl.value = url
    }
}
