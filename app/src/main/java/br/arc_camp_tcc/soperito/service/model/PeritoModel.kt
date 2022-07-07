package br.arc_camp_tcc.soperito.service.model

// mapeamento de interface de banco
class PeritoModel(
    val codigUsuario: Int,
    val codPerito: Int,
    val userPerito: Boolean,
    var email: String,
    var nome: String,
    val cpf: String,
    var telefone: String,
    var senha: String,
    var service: String,
    var temp: Int,
    var obs: String,
    var valor: Float,
    var exp: String,
    var esp: String,
)