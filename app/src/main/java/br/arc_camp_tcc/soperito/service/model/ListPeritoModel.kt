package br.arc_camp_tcc.soperito.service.model

import com.google.gson.annotations.SerializedName

class ListPeritoModel {

    @SerializedName("cod_curriculo")
    var codCurriculo: Int = 0

    @SerializedName("cod_perito")
    var codPerito: Int = 0

    @SerializedName("empregador")
    var empregador: Int = 0

    @SerializedName("nome_perito")
    var nome: String = ""

    @SerializedName("servico")
    var servico: String = ""

    @SerializedName("temp")
    var temp: String = ""

    @SerializedName("obs")
    var obs: String = ""

    @SerializedName("valor")
    var valor: String = ""

    @SerializedName("localizacao")
    var localizacao: String = ""

    @SerializedName("data_curriculo")
    var dataCurriculo: String = ""

    @SerializedName("exp")
    var exp: String = ""

    @SerializedName("cidade")
    var cidade: String = ""

    @SerializedName("estado")
    var estado: String = ""

    @SerializedName("espec")
    var espec: String = ""

    var statusCand: Int = 0

}