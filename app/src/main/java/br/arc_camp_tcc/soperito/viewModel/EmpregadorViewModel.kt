package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.repository.bancoRepository

class EmpregadorViewModel(aplication: Application): AndroidViewModel(aplication)  {

    private val repository = bancoRepository(aplication.applicationContext)
/*
    fun insert(empregadores: EmpregadorModel){
        repository.insert(empregadores)
    }

    fun get(empregadores: EmpregadorModel){
        repository.insert(empregadores)
    }

    fun delete(empregadores: EmpregadorModel){
        repository.insert(empregadores)
    }

 */
}