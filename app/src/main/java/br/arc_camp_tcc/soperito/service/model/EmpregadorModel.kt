package br.arc_camp_tcc.soperito.service.model

// mapeamento de interface de banco
class EmpregadorModel(
    val codigUsuario: Int,
    val userEmp: Boolean,
    val codEmp: Int,
    var email: String,
    var nome: String,
    val cpf: String,
    var telefone: String,
    var senha: String,
    var statusEmp: String,
    var dispPagar: Float,
    var servicoDesejado: String,
    var tempExp: Int,
    var descVaga: String
)