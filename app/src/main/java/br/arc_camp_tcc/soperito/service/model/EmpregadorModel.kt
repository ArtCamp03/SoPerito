package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// mapeamento de interface de banco
// utilizando anotations da biblioteca ROOM

@Entity(tableName = "empregadores")
class EmpregadorModel {

    @SerializedName("cod_usuario")
    @ColumnInfo(name = "cod_usuario")
    var codUser: Int = 0

    @SerializedName("cod_emp")
    @ColumnInfo(name = "cod_emp")
    @PrimaryKey
    var codEmp: Int = 0

    @SerializedName("user_emp")
    @ColumnInfo(name = "user_emp")
    var userEmp: Int = 0

    @SerializedName("nome_empregador")
    @ColumnInfo(name = "nome_empregador")
    var nome: String = ""

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String = ""

    @SerializedName("telefone")
    @ColumnInfo(name = "telefone")
    var telefone: String = ""

    @SerializedName("cod_vaga")
    @ColumnInfo(name = "cod_vaga")
    var codVaga: Int = 0

    @SerializedName("status_emp")
    @ColumnInfo(name = "status_emp")
    var statusEmp: String = ""

}