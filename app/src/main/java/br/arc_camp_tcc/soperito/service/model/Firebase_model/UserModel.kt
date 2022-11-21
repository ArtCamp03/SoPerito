package br.arc_camp_tcc.soperito.service.model.Firebase_model

class UserModel(
    var cod_usuario: Int?,
    var user_perito: Int?,
    var user_emp: Int?,
    var cod_perito: Int?,
    var cod_emp: Int?,
    var email: String?,
    var nome: String?,
    var cpf: String?,
    var telefone: String?,
    var cidade: String?,
    var estado: String?,
    var senha: String?
) {
    // construtor vazio
    constructor(): this(null, null, null, null, null,
    null, null, null, null, null, null, null)

}