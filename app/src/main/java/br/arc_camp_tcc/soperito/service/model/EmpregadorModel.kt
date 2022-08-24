package br.arc_camp_tcc.soperito.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// mapeamento de interface de banco
// utilizando anotations da biblioteca ROOM

@Entity(tableName = "empregadores")
class EmpregadorModel {

    @SerializedName("cod_emp")
    @ColumnInfo(name = "cod_emp")
    @PrimaryKey
    var codEmp: Int = 0

    @SerializedName("status_emp")
    @ColumnInfo(name = "status_emp")
    var statusEmp: String = ""


}