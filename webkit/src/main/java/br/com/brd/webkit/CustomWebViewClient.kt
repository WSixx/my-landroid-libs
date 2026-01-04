package br.com.brd.webkit

import android.util.Log
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 *
 * Por padrão a partir do Android 8.1 a webview roda em um processo separado do app
 * WebView Client seguro contra crash de webview e
 * encerramento do processo por falta de memoria
 * Isso impede que o processo do app principal possa quebrar
 *
 * created on 04/01/2026
 * @author Lucas Goncalves
 */
class CustomWebViewClient : WebViewClient() {

    // Este metodo e chamado quando o processo isolado morre (crash ou OOM - Out of Memory)
    override fun onRenderProcessGone(
        view: WebView?,
        detail: RenderProcessGoneDetail?,
    ): Boolean {

        // 1. Verificar o motivo da morte
        if (detail?.didCrash() == true) {
            Log.e("WebViewProcess", "O Renderizador CRASHOU.")
        } else {
            Log.e("WebViewProcess", "O Renderizador foi morto pelo sistema (Memória Baixa).")
        }

        // 2. Ação de Recuperação
        // NÃO tente reutilizar a instância 'view'. Ela é inútil agora.
        // Você deve remover essa WebView do layout e, opcionalmente, criar uma nova
        // ou mostrar uma mensagem de erro para o usuário.
        (view?.parent as? android.view.ViewGroup)?.removeView(view)

        // 3. Retorno Vital
        // true = "Eu tratei o problema, NÃO mate meu app."
        // false = "Não sei o que fazer, pode matar meu app." (Comportamento padrão)
        return true
    }
}