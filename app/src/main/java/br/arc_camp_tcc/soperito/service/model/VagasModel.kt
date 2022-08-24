package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "vagas")
class VagasModel {

    @SerializedName("id_vaga")  // mapeamento de dados qe chegam da API
    @ColumnInfo(name = "id_vaga")   // salvos no banco de dados
    @PrimaryKey
    var vagaId : Int = 0

    @SerializedName("servico")
    @ColumnInfo(name = "servico")
    var servico : String = ""

    @SerializedName("descricao")
    @ColumnInfo(name = "descricao")
    var descricao : String = ""

    @SerializedName("temp_exp")
    @ColumnInfo(name = "temp_exp")
    var tempExp : Int = 0

    @SerializedName("disp_pagar")
    @ColumnInfo(name = "disp_pagar")
    var dispPagar : Float = 0F

}