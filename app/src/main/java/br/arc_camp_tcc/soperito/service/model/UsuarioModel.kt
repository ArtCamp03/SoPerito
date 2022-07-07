package br.arc_camp_tcc.soperito.service.model

// mapeamento de interface de banco
data class UsuarioModel(
    val codigUsuario: Int,
    val userPerito: Boolean,
    val userEmp: Boolean,
    var email: String,
    var nome: String,
    val cpf: String,
    var telefone: String,
    var senha: String,
    var codSeguranca: Int
)