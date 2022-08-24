package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// recebe as informaçoes da API
@Entity(tableName = "usuarios")
class UsuarioModel {

    @SerializedName("cod_usuario")
    @ColumnInfo(name = "cod_usuario")
    @PrimaryKey
    var codigUsuario: Int = 0

    @SerializedName("user_perito")
    @ColumnInfo(name = "user_perito")
    var userPerito: Boolean = false

    @SerializedName("user_emp")
    @ColumnInfo(name = "user_emp")
    var userEmp: Boolean = false

    @SerializedName("cod_perito")
    @ColumnInfo(name = "cod_perito")
    var codPerito: Int = 0

    @SerializedName("cod_emp")
    @ColumnInfo(name = "cod_emp")
    var codEmp: Int = 0

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

    @SerializedName("senha")
    @ColumnInfo(name = "senha")
    lateinit var senha: String

}