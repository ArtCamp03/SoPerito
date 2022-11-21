package br.arc_camp_tcc.soperito.service.model.Firebase_model

class VagasModel(
    var codVaga: Int?,
    var codEmp: Int?,
    var contratado: Int?,
    var servico: String?,
    var descricao: String?,
    var tempExp: Int?,
    var dispPagar: String?,
    var vData: String?,
    var local: String?
) {
    // construtor vazio
    constructor(): this(
        null, null, null, null,
        null, null, null, null, null
    )
}