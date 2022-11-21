package br.arc_camp_tcc.soperito.service.model.Firebase_model

class ContratadosModel(
    var codContrato: Int?,
    var codEmp: Int?,
    var codCurriculo: Int?,
    var codPerito: Int?,
    var statusContratados: Int?
) {
    // construtor vazio
    constructor(): this(
        null, null, null, null, null
    )
}