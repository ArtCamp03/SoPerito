package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// recebe as informaçoes da API
@Entity(tableName = "usuarios")
class UsuarioModel{

    @SerializedName("cod_usuario")
    @ColumnInfo(name = "cod_usuario")
    @PrimaryKey
    var cod_usuario: Int = 0

    @SerializedName("user_perito")
    @ColumnInfo(name = "user_perito")
    var user_perito: Int = 0

    @SerializedName("user_emp")
    @ColumnInfo(name = "user_emp")
    var user_emp: Int = 0

    @SerializedName("cod_perito")
    @ColumnInfo(name = "cod_perito")
    var cod_perito: Int = 0

    @SerializedName("cod_emp")
    @ColumnInfo(name = "cod_emp")
    var cod_emp: Int = 0

    @SerializedName("email")
    @ColumnInfo(name = "email")
    lateinit var email: String

    @SerializedName("nome")
    @ColumnInfo(name = "nome")
    var nome: String = ""

    @SerializedName("cpf")
    @ColumnInfo(name = "cpf")
    lateinit var cpf: String

    @SerializedName("telefone")
    @ColumnInfo(name = "telefone")
    lateinit var telefone: String

    @SerializedName("cidade")
    @ColumnInfo(name = "cidade")
    lateinit var cidade: String

    @SerializedName("estado")
    @ColumnInfo(name = "estado")
    lateinit var estado: String

    @SerializedName("senha")
    @ColumnInfo(name = "senha")
    lateinit var senha: String

}