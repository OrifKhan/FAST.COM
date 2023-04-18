package com.example.formegafon

import MyWebViewClient
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import com.example.dbfordodo.dodoViewMadel.retrafitRepository.Retrafit
import com.example.formegafon.Data.Constant.Companion.ID_API
import com.example.formegafon.Data.Constant.Companion.ID_INTERNET_SPEED
import com.example.formegafon.Data.Constant.Companion.ID_LATENCY
import com.example.formegafon.Data.Constant.Companion.ID_LOADED
import com.example.formegafon.Data.Constant.Companion.ID_SERVER
import com.example.formegafon.Data.Constant.Companion.ID_SPEED
import com.example.formegafon.Data.Constant.Companion.ID_USER
import com.example.formegafon.Data.Constant.Companion.SHOW_DETAILS
import com.example.formegafon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var webView: WebView
    var info = mutableListOf<Map<String, String>>()
    private val mainApi: MainApi = Retrafit().retrofit.create(MainApi::class.java)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        webView = binding.webView
        binding.apply {
            webView.loadUrl("https://fast.com")
            webView.settings.domStorageEnabled = true
            webView.settings.javaScriptEnabled = true
        }

        binding.text.setOnClickListener() {
            webView.evaluateJavascript(ID_USER) { data ->
                CoroutineScope(Dispatchers.Default).launch {
                info.add(mapOf(ID_USER to data))
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_API) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                info.add(mapOf(ID_USER to data))
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")

            }
            webView.evaluateJavascript(ID_LATENCY) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                info.add(mapOf(ID_LATENCY to data))
                }
                Log.d("log_test", "${data} location")

            }

            webView.evaluateJavascript(ID_LOADED) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                info.add(mapOf(ID_USER to data))
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_SERVER) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                info.add(mapOf(ID_USER to data))
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_INTERNET_SPEED) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                info.add(mapOf(ID_INTERNET_SPEED to data))
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_SPEED) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                info.add(mapOf(ID_USER to data))

                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")



            }



        }
        webView.webViewClient = MyWebViewClient(this, binding.progressBar, binding.errorAnim)
        webView.webChromeClient = object : WebChromeClient() {


        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}


