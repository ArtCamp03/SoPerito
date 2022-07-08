package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// mapeamento de interface de banco

@Entity(tableName = "Perito")
class PeritoModel{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codPerito")
    var codPerito: Int = 0

    @ForeignKey("Usuario","codigUsuario")
    @ColumnInfo(name = "codigUsuario")
    var codigUsuario: Int = 0

    @ColumnInfo(name = "userPerito")
    var userPerito: Boolean = false

    @ColumnInfo(name = "service")
    var service: String = ""

    @ColumnInfo(name = "temp")
    var temp: Int = 0

    @ColumnInfo(name = "obs")
    var obs: String = ""

    @ColumnInfo(name = "valor")
    var valor: Float = 0.0f

    @ColumnInfo(name = "exp")
    var exp: String = ""

    @ColumnInfo(name = "esp")
    var esp: String = ""

}

