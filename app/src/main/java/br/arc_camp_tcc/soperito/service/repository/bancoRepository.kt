package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.local.UsuarioDataBase

//REPOSITORY manipulaçao de dados
//ninguem instancia a classe

class bancoRepository(context: Context) {

    // instancia UsuarioDataBase
    private val usuarioDataBase = UsuarioDataBase.getUsuario(context).usuarioDAO()

    // insercao de dados Usuarios
    fun insert(usuarios: UsuarioModel): Boolean {
        return usuarioDataBase.insert(usuarios) > 0
    }

    fun update(usuarios: UsuarioModel): Boolean {
        return usuarioDataBase.update(usuarios) > 0
    }

    // insercao de dados Empregador
    /*
        fun insert(empregadores: EmpregadorModel): Boolean {
            return usuarioDataBase.insert(empregadores) > 0
        }

        fun update(empregadores: EmpregadorModel): Boolean {
            return usuarioDataBase.update(empregadores) > 0
        }
     */

    fun delete(id: Int) {
        val usuar = get(id)
        usuarioDataBase.delete(usuar)
    }

    fun get(id: Int): List<UsuarioModel> {
        return usuarioDataBase.get(id)
    }

    // retorna atributos e valores de todo os usuarios
    fun getLogin(email: String, senha:String): Boolean {
        return usuarioDataBase.getLogin(email,senha)
    }
    /*
      fun getAll(): List<EmpregadorModel> {
          return usuarioDataBase.getAllEmpregador()
      }


      fun getPerito(): List<PeritoModel> {
          return usuarioDataBase.getAllPerito()
      }
    */
}