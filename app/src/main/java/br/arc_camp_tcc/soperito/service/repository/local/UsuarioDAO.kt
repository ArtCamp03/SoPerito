package br.arc_camp_tcc.soperito.service.repository.local

import androidx.room.*
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel

// camada Data Access Object
@Dao
interface UsuarioDAO {

    @Insert
    fun insert(usuario: UsuarioModel): Long

    @Update
    fun update(usuario: UsuarioModel): Int

    @Delete
    fun delete(usuario: List<UsuarioModel>)

    @Query("SELECT email, senha FROM usuario WHERE email = :email AND senha = :senha")
    fun getLogin(email: String, senha:String): Boolean

    @Query("SELECT * FROM Usuario WHERE codigUsuario = :id")
    fun get(id: Int): List<UsuarioModel>

    @Query("SELECT * FROM Perito")
    fun getAllPerito(): List<PeritoModel>

    @Query("SELECT * FROM Empregador")
    fun getAllEmpregador(): List<EmpregadorModel>
}