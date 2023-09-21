package dev.mkeeda.webview_sandbox

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat

class MyWebViewClient(
    private val onUrlChanged: (Uri) -> Unit
) : WebViewClientCompat() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        onUrlChanged(request.url)
        return true
    }
}
