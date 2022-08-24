package br.arc_camp_tcc.soperito.service.listeners

// Houve a resposta da API
interface APIListener<T> {
    fun onSuccess(result: T)
    fun onFailure(message: String)
}