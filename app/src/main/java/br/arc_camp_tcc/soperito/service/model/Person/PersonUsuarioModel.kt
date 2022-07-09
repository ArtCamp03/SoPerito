package br.arc_camp_tcc.soperito.service.model.Person

import com.google.gson.annotations.SerializedName

// recebe as informaçoes da API
class PersonUsuarioModel {

    @SerializedName("codigUsuario ")
    var codigUsuario: Int = 0

    @SerializedName("codEmp ")
    var codEmp: Int = 0

    @SerializedName("codPerito ")
    var codPerito: Int = 0

    @SerializedName("userPerito ")
    var userPerito: Boolean = false

    @SerializedName("userEmp ")
    var userEmp: Boolean = false

    @SerializedName("email ")
    var email: String = ""

    @SerializedName("nome ")
    var nome: String = ""

    @SerializedName("cpf ")
    var cpf: String = ""

    @SerializedName("telefone ")
    var telefone: String = ""

    @SerializedName("senha ")
    var senha: String = ""

    @SerializedName("codSeguranca ")
    var codSeguranca: Int = 0
}