package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import android.app.Service
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.PersonRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    // faz login usando APIListener
    fun doLogin(email: String, senha: String) {
        personRepository.login(email, senha, object : APIListener<PersonLoginModel> {
            override fun onSuccess(result: PersonLoginModel) {
                // salva dados no momento do login
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.EMAIL, result.email)
                securityPreferences.store(DataBaseConstants.USER.COLUMNS.SENHA, result.senha)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }
        })
    }

}