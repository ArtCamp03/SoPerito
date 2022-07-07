package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// mapeamento de interface de banco
// utilizando anotations da biblioteca ROOM

@Entity(tableName = "Empregador")
class EmpregadorModel  {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codEmp")
    var codEmp: Int = 0

    @ColumnInfo(name = "codigUsuario")
    var codigUsuario: Int = 0

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

    @ColumnInfo(name = "statusEmp")
    var statusEmp: String = ""

    @ColumnInfo(name = "dispPagar")
    var dispPagar: Float = 0.0f

    @ColumnInfo(name = "servicoDesejado")
    var servicoDesejado: String = ""

    @ColumnInfo(name = "tempExp")
    var tempExp: Int = 0

    @ColumnInfo(name = "descVaga")
    var descVaga: String = ""
}