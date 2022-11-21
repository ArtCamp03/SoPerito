package br.arc_camp_tcc.soperito.service.model.Firebase_model

class PeritModel(
    var codigUsuario: Int?,
    var codPerito: Int?,
    var userPerito: Int?,
    var codCurriculo: Int?,
    var exp: String?,
    var espec: String?
) {

    // construtor vazio
    constructor(): this(null, null, null, null, null,
        null
    )
}