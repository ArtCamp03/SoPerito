package br.arc_camp_tcc.soperito.service.repository.local

import androidx.room.*
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel

// camada Data Access Object
@Dao
interface UsuarioDAO {

    /*
    // salva lista de  vagaas
    @Insert
    fun vagasSave(list : List<VagasModel>)

    // retorna lista de vagas
    @Query("SELECT * FROM vagas")
    fun vagasList(): List<VagasModel>
    */

    @Insert
    fun insert(usuarios: UsuarioModel): Long

    @Update
    fun update(usuarios: UsuarioModel): Int

    @Delete
    fun delete(usuarios: List<UsuarioModel>)

    @Query("SELECT email, senha FROM usuarios WHERE email = :email AND senha = :senha")
    fun getLogin(email: String, senha:String): Boolean

    @Query("SELECT * FROM usuarios WHERE cod_usuario = :id")
    fun get(id: Int): List<UsuarioModel>

    /*
    @Query("SELECT * FROM peritos")
    fun getAllPerito(): List<PeritoModel>
    */


    @Insert
    fun insertEmp(empregadores: EmpregadorModel): Long

    @Update
    fun updateEmp(empregadores: EmpregadorModel): Int

    @Delete
    fun deleteEmp(empregadores: List<EmpregadorModel>)

    /*
    @Query("SELECT * FROM empregadores")
    fun getAllEmpregador(): List<EmpregadorModel>

     */
}