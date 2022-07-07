package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository

class EmpregadorViewModel(aplication: Application): AndroidViewModel(aplication)  {

    private val repository = UsuarioRepository.getInstance(aplication)

    fun insert(empregador: UsuarioModel){
        repository.insert(empregador)
    }

    fun get(empregador: UsuarioModel){
        repository.insert(empregador)
    }

    fun delete(empregador: UsuarioModel){
        repository.insert(empregador)
    }

}