package br.arc_camp_tcc.soperito.service.model.Person

import com.google.gson.annotations.SerializedName

// recebe as informaçoes da API
class PersonLoginModel {

    @SerializedName("email")
     lateinit var email: String

    @SerializedName("senha")
     lateinit var senha: String

    @SerializedName("cod_usuario")
    var codUsuario: Int = 0

}