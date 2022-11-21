package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuConfigViewModel(application: Application) :  AndroidViewModel(application) {

    // instancia securityPreferences
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private lateinit var auth : FirebaseAuth

    private val _logOut = MutableLiveData<ValidationModel>()
    val logOut: LiveData<ValidationModel> = _logOut

    fun logout(){
        // desloga usuario pela API
        securityPreferences.remove(DataBaseConstants.SHARED.PERSON_KEY)
        securityPreferences.remove(DataBaseConstants.SHARED.TOKEN_KEY)
        Firebase.auth.signOut()

        _logOut.value = ValidationModel()

    }
}