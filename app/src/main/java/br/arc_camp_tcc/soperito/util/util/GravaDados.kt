package br.arc_camp_tcc.soperito.util.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class GravaDados(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

}