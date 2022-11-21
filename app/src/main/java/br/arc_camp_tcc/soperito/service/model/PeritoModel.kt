package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// mapeamento de interface de banco

@Entity(tableName = "peritos")
class PeritoModel{

    @SerializedName("cod_usuario")
    @ColumnInfo(name = "cod_usuario")
    var codigUsuario: Int = 0

    @SerializedName("cod_perito")
    @PrimaryKey
    @ColumnInfo(name = "cod_perito")
    var codPerito: Int = 0

    @SerializedName("user_perito")
    @ColumnInfo(name = "user_perito")
    var userPerito: Int = 0

    @SerializedName("cod_curriculo")
    @ColumnInfo(name = "cod_curriculo")
    var codCurriculo: Int = 0

    @SerializedName("exp")
    @ColumnInfo(name = "exp")
    var exp: String = ""

    @SerializedName("espec")
    @ColumnInfo(name = "espec")
    var espec: String = ""

// --------------------------       ###############      -----------------------------



    @SerializedName("nome_perito")
    @ColumnInfo(name = "nome_perito")
    var nome: String = ""

    @SerializedName("servico")
    @ColumnInfo(name = "servico")
    var servico: String = ""

    @SerializedName("temp")
    @ColumnInfo(name = "temp")
    var temp: String = ""

    @SerializedName("obs")
    @ColumnInfo(name = "obs")
    var obs: String = ""

    @SerializedName("valor")
    @ColumnInfo(name = "valor")
    var valor: String = ""

    @SerializedName("data_curriculo")
    @ColumnInfo(name = "data_curriculo")
    var dataCurriculo: String = ""

}