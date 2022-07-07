package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository

// aplication instancia o context do repository
class TransacaoViewModel(aplication: Application) : AndroidViewModel(aplication) {

    private val repository = UsuarioRepository(aplication.applicationContext)

    fun insert(login: UsuarioModel){
        repository.insert(login)
    }

    fun get(login: UsuarioModel){
        repository.insert(login)
    }

    fun delete(login: UsuarioModel){
        repository.insert(login)
    }
}