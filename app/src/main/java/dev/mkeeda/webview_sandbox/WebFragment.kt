package dev.mkeeda.webview_sandbox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dev.mkeeda.webview_sandbox.databinding.FragmentWebBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null

    private val binding get() = _binding!!

    private val viewModel: WebViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_WebFragment_to_WebComposeFragment)
        }

        binding.webview.webViewClient = MyWebViewClient(onUrlChanged = { newUrl ->
            viewModel.onPageLoad(newUrl)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.url.collectLatest { url ->
                binding.webview.loadUrl(url.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
