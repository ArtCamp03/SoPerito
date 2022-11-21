package br.arc_camp_tcc.soperito.service.model.Firebase_model

class CandidatosModel(
    var codCandidato: Int?,
    var codEmp: Int?,
    var codVaga: Int?,
    var codPerito: Int?,
    var statusCand: Int?
) {
    // construtor vazio
    constructor(): this(
        null, null, null, null, null
    )
}