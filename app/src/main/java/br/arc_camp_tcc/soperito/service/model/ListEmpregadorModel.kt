package br.arc_camp_tcc.soperito.service.model

import com.google.gson.annotations.SerializedName

class ListEmpregadorModel {

    @SerializedName("cod_vaga")
    var codVaga: Int = 0

    @SerializedName("cod_emp")
    var codEmp: Int = 0

    @SerializedName("contratado")
    var contratado: Int = 0

    @SerializedName("nome_emp")
    var nomeEmp: String = ""

    @SerializedName("servico")
    var servico: String = ""

    @SerializedName("temp_exp")
    var tempExp: String = ""

    @SerializedName("disp_pagar")
    var dispPagar: String = ""

    @SerializedName("descricao")
    var descricao: String = ""

    @SerializedName("v_data")
    var vData: String = ""

    @SerializedName("local")
    var local: String = ""
}