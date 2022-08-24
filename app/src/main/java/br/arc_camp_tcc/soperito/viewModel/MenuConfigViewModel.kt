package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class MenuConfigViewModel(application: Application) :  AndroidViewModel(application) {

    // instancia securityPreferences
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    fun logout(){
        securityPreferences.remove(DataBaseConstants.SHARED.PERSON_KEY)
        securityPreferences.remove(DataBaseConstants.SHARED.TOKEN_KEY)
    }
}