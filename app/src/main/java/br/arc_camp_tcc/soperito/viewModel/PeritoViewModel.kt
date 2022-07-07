package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository

class PeritoViewModel(aplication: Application) : AndroidViewModel(aplication){

    private val repository = UsuarioRepository(aplication.applicationContext)

    fun insert(prito: UsuarioModel){
        repository.insert(prito)
    }

    fun get(prito: UsuarioModel){
        repository.insert(prito)
    }

    fun delete(prito: UsuarioModel){
        repository.insert(prito)
    }
}