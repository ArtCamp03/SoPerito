package br.arc_camp_tcc.soperito.service.listeners

interface PeritoListener {

    // Adiçao
    fun onlistClick(id: Int)

    // Remoçao
    fun onDelete(id: Int)

}