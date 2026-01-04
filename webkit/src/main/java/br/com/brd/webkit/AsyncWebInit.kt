package br.com.brd.webkit

import android.app.Application
import androidx.annotation.OptIn
import androidx.webkit.WebViewCompat
import androidx.webkit.WebViewStartUpConfig
import java.util.concurrent.Executors

/**
 *
 *
 * created on 04/01/2026
 * @author Lucas Goncalves
 */
class AsyncWebInit {

    private val myExecutor = Executors.newSingleThreadExecutor()


    @OptIn(WebViewCompat.ExperimentalAsyncStartUp::class)
    fun initWebview(application: Application) {
        try {
            val config = WebViewStartUpConfig.Builder(myExecutor)
                // Opcional: Se 'false', faz só o trabalho de background puro.
                // Se 'true' (padrão), faz tudo e chama o callback quando pronto.
                .setShouldRunUiThreadStartUpTasks(false)
                .build()

            // 3. Inicia o WebView de forma assíncrona (API Experimental)
            WebViewCompat.startUpWebView(
                application,
                config
            ) {
                // WebView está e pronta para uso sem lag.
                // Ideal para carregar na Splash Screen.
                println("WebView inicializado com sucesso!")
                myExecutor.shutdown()
            }
        } catch (ex: Exception) {
            println("Fallback")
            myExecutor.shutdown()
        }

    }
}