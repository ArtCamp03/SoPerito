package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository

class UsuarioViewModel(aplication: Application) : AndroidViewModel(aplication)  {

    private val repository = UsuarioRepository.getInstance(aplication)

    fun insert(usuario: UsuarioModel){
        repository.insert(usuario)
    }

    fun get(usuario: UsuarioModel){
        repository.insert(usuario)
    }

    fun getLogin(email: String, senha: String): Boolean {
        return repository.getLogin(email, senha)
    }

    fun delete(usuario: UsuarioModel){
        repository.insert(usuario)
    }

}