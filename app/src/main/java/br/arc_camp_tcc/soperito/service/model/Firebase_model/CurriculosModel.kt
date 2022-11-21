package br.arc_camp_tcc.soperito.service.model.Firebase_model

class CurriculosModel(
    var codCurriculo: Int?,
    var codPerito: Int?,
    var empregador: Int?,
    var servico: String?,
    var temp: String?,
    var obs: String?,
    var valor: String?,
    var localizacao: String?,
    var dataCurriculo: String?
) {
    // construtor vazio
    constructor(): this(
        null, null, null, null,
        null, null, null, null, null
    )
}