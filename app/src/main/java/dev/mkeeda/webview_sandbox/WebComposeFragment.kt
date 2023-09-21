package dev.mkeeda.webview_sandbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle

class WebComposeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            WebScreen()
        }
    }
}

@Composable
fun WebScreen(viewModel: WebViewModel = WebViewModel()) {
    val url by viewModel.url.collectAsStateWithLifecycle()

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = MyWebViewClient(viewModel)
            }
        },
        update = { view ->
            view.loadUrl(url.toString())
        }
    )
}
