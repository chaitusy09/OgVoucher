package com.oginnovation.voucherssas.login

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.oginnovation.voucherssas.R

class ActivityLogin : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar = findViewById(R.id.progress_home)
        webView = findViewById(R.id.webview_home)
        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                // Track URL here (e.g., with Google Analytics)
                trackUrl(url)
                //view?.loadUrl("")
                return false // Handle all links within the WebView
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.url.observe(this) { url ->
            webView.loadUrl(url)
        }
        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
    private fun trackUrl(url: String?) {
        // Implement your URL tracking logic here
        // You can use libraries like Google Analytics or Firebase Analytics
        // This example is just a placeholder
        if (url != null) {
            if(url.contains("client_id")){
                val queryParams = getQueryParamsFromUrl(url)
                println("response_type: ${queryParams["response_type"]}")
                println("client_id: ${queryParams["client_id"]}")
            }
                Log.d("WebViewActivity", "URL: $url")
        }
    }
    fun getQueryParamsFromUrl(url: String): Map<String, String> {
        val uri = Uri.parse(url)
        val builder = mutableMapOf<String, String>()
        for (paramName in uri.queryParameterNames) {
            val paramValue = uri.getQueryParameter(paramName)
            if (paramValue != null) {
                builder[paramName] = paramValue
            }
        }
        return builder.toMap()
    }
}