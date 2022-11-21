package br.arc_camp_tcc.soperito.service.model.Firebase_model

class EmpModel(
    var codigUsuario: Int?,
    var codEmp: Int?,
    var userEmp: Int?,
    var codVaga: Int?,
    var emailContact: String?,
    var nomeContact: String?,
    var telefoneContact: String?
) {
    // construtor vazio
    constructor(): this(
        null, null, null, null,
        null, null, null
    )

}