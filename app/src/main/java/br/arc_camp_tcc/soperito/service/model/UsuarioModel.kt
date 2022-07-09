package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// mapeamento de interface de banco

@Entity(tableName = "usuario")
class UsuarioModel{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codigUsuario")
    var codigUsuario: Int = 0

    @ColumnInfo(name = "codEmp")
    var codEmp: Int = 0

    @ColumnInfo(name = "codPerito")
    var codPerito: Int = 0

    @ColumnInfo(name = "userPerito")
    var userPerito: Boolean = false

    @ColumnInfo(name = "userEmp")
    var userEmp: Boolean = false

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "nome")
    var nome: String = ""

    @ColumnInfo(name = "cpf")
    var cpf: String = ""

    @ColumnInfo(name = "telefone")
    var telefone: String = ""

    @ColumnInfo(name = "senha")
    var senha: String = ""

    @ColumnInfo(name = "codSeguranca")
    var codSeguranca: Int = 0
}