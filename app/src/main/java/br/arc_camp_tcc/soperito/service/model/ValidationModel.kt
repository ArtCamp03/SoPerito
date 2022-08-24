package br.arc_camp_tcc.soperito.service.model

class ValidationModel(message: String = "") { // um valor sfasdfsdf

    private var status: Boolean = true
    private var validationMessage: String = ""

    init {
        if(message != "") {
            validationMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validationMessage
}