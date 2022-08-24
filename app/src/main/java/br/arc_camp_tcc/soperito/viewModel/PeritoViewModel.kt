package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.bancoRepository

class PeritoViewModel(application: Application) : AndroidViewModel(aplication){

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val repository = bancoRepository(application.applicationContext)

    // verifica se usuario ja possui conta Perito
    private val getContPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.USER_PERITO)


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