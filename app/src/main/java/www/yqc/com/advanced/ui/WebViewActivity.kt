package www.yqc.com.advanced.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*
import www.yqc.com.advanced.R

class WebViewActivity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        btn.setOnClickListener {
            webview.loadUrl("http://www.baidu.com")
            webview.addJavascriptInterface(this, "android")
            webview.webViewClient = mWebviewClient
            val settings = webview.settings
            settings.javaScriptEnabled = true//允许使用js
            settings.cacheMode=WebSettings.LOAD_NO_CACHE//不用缓存
            settings.setSupportZoom(true)//支持缩放
            settings.builtInZoomControls=true
        }
    }

    val mWebviewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }
    }
}
