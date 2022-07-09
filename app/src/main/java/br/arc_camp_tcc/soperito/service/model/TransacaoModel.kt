package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// mapeamento de interface de banco

@Entity(tableName = "transacao")
class TransacaoModel{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "codTransc")
    var codTransc: Int = 0

    @ColumnInfo(name = "codPerito")
    var codPerito: Int = 0

    @ColumnInfo(name = "codEmp")
    var codEmp: Int = 0

    @ColumnInfo(name = "nomeEmp")
    var nomeEmp: String = ""

    @ColumnInfo(name = "nomePerito")
    var nomePerito: String = ""

    @ColumnInfo(name = "statusEmp")
    var statusEmp: Boolean = false

    @ColumnInfo(name = "statusPerito")
    var statusPerito: Boolean = false

    @ColumnInfo(name = "numEmp")
    var numEmp: Int = 0

    @ColumnInfo(name = "numPerito")
    var numPerito: Int = 0
}



