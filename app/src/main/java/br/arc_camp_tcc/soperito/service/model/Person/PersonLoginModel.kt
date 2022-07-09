package br.arc_camp_tcc.soperito.service.model.Person

import com.google.gson.annotations.SerializedName

// recebe as informaçoes da API
class PersonLoginModel {

    @SerializedName("email ")
    var email: String = ""

    @SerializedName("senha ")
    var senha: String = ""

}