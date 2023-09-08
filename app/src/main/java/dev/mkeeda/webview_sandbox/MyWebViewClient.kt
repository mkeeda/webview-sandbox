package dev.mkeeda.webview_sandbox

import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat

class MyWebViewClient(
    private val viewModel: WebViewModel
) : WebViewClientCompat() {
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        viewModel.onPageLoad(request.url)
        return true
    }
}
