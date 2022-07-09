package br.arc_camp_tcc.soperito.service.repository.local

import android.content.Context
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel

//REPOSITORY manipulaçao de dados
//ninguem instancia a classe

class UsuarioRepository(context: Context) {

    // instancia UsuarioDataBase
    private val usuarioDataBase = UsuarioDataBase.getUsuario(context).usuarioDAO()

    // insercao de dados
    fun insert(usuario: UsuarioModel): Boolean {
        return usuarioDataBase.insert(usuario) > 0
    }

    fun update(usuario: UsuarioModel): Boolean {
        return usuarioDataBase.update(usuario) > 0
    }

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

    fun getAll(): List<EmpregadorModel> {
        return usuarioDataBase.getAllEmpregador()
    }

    fun getPerito(): List<PeritoModel> {
        return usuarioDataBase.getAllPerito()
    }

}