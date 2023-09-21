package dev.mkeeda.webview_sandbox

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class WebComposeFragment : Fragment() {
    private val viewModel: WebViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val url by viewModel.url.collectAsStateWithLifecycle()

            WebScreen(
                url = url,
                onUrlChanged = { newUrl ->
                    viewModel.onPageLoad(newUrl)
                }
            )
        }
    }
}

@Composable
fun WebScreen(
    url: Uri,
    onUrlChanged: (Uri) -> Unit
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = MyWebViewClient(onUrlChanged)
            }
        },
        update = { webView ->
            webView.loadUrl(url.toString())
        }
    )
}
