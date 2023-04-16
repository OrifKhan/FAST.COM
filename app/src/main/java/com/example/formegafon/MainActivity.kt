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
    private val list = mutableListOf<String>()
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
        webView.evaluateJavascript(SHOW_DETAILS) { data ->
            Log.d("log_text","$data class")
            if (data.toString()!="oc-icon speed-progress-indicator-icon             oc-icon-refresh"){
            }else{
                binding.text.visibility= View.VISIBLE

            }
        }
        binding.text.setOnClickListener() {
            findIdForById(webView)
            Repository().setClientList(info)
            /*.forEach(){
                ooo=it.clientCountry.toString()
                Toast.makeText(this,"${it.clientCountry}",Toast.LENGTH_SHORT).show()
                 Log.d("log_test", "${it.clientCountry} location")
                 Log.d("log_test", "${it.apiAddress} location")
                 Log.d("log_test", "${it.unloaded} location")
                 Log.d("log_test", "${it.loaded} location")
                 Log.d("log_test", "${it.serverCountry} location")
                 Log.d("log_test", "${it.yourSpeedInternet} location")
                 Log.d("log_test", "${it.speed} location")
                 Log.d("log_test", "${it.testData} location")
            }*/
            Log.d("log_test", "${Repository().setClientList(info)} location")


        }
        webView.webViewClient = MyWebViewClient(this, binding.progressBar, binding.errorAnim)
        webView.webChromeClient = object : WebChromeClient() {


        }
    }

    private fun findIdForById(webView: WebView) {


            webView.evaluateJavascript(ID_USER) { data ->
                info.add(mapOf(ID_USER to data))
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_API) { data ->
                info.add(mapOf(ID_USER to data))
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")

            }
            webView.evaluateJavascript(ID_LATENCY) { data ->
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                info.add(mapOf(ID_LATENCY to data))
                Log.d("log_test", "${data} location")

            }

            webView.evaluateJavascript(ID_LOADED) { data ->
                info.add(mapOf(ID_USER to data))
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_SERVER) { data ->
                info.add(mapOf(ID_USER to data))
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_INTERNET_SPEED) { data ->
                info.add(mapOf(ID_INTERNET_SPEED to data))
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")


            }
            webView.evaluateJavascript(ID_SPEED) { data ->
                info.add(mapOf(ID_USER to data))
                CoroutineScope(Dispatchers.IO).launch {

                    delay(500)
                    mainApi.sendThis(data)
                }
                Log.d("log_test", "${data} location")



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


