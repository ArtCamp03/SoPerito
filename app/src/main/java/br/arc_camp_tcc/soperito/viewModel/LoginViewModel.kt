package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.PersonRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.remote.api.RetrofitClient
import br.arc_camp_tcc.soperito.service.repository.remote.firebase.FirebaseClient
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application.applicationContext)
    private val personFirebaseAuth = FirebaseClient(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser

    // faz login usando APIListener
    fun doLogin(email: String, senha: String) {
        personRepository.login(email, senha,object : APIListener<PersonLoginModel> {
            override fun onSuccess(result: PersonLoginModel) {
                // salva dados no momento do login
                securityPreferences.store(DataBaseConstants.SHARED.PERSON_KEY, result.email)
                securityPreferences.store(DataBaseConstants.SHARED.TOKEN_KEY, result.senha)
                securityPreferences.store(DataBaseConstants.SHARED.USER_KEY, result.codUsuario.toString())

                // adiciona os headers
                RetrofitClient.addHeaders(result.email, result.senha)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }
        })
    }

    //  Verifica se o usuario esta logado
    fun verifyLoggedUser(auth:FirebaseAuth){

        val email = securityPreferences.get(DataBaseConstants.SHARED.PERSON_KEY)
        val senha = securityPreferences.get(DataBaseConstants.SHARED.TOKEN_KEY)

        // atualiza o headers
        RetrofitClient.addHeaders(email, senha)

        val user = auth?.currentUser

        if(user != null){
            _loggedUser.value = true
        }else{
            val logged = (email != "" && senha != "")
            _loggedUser.value = logged
        }

    }

    /*
    fun loginUsuario(email:String,senha:String){
        if(personFirebaseAuth.loginUsuario(email, senha)){
                _login.value = ValidationModel()
            }else{
            doLogin(email , senha)
        }
    }

    private fun loginUsuario(email: String, password: String, auth:FirebaseAuth) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Authentication failed: ${task.exception.toString()} ", Toast.LENGTH_SHORT).show()
                    Log.d("loginTeste", task.exception.toString())
                }
            }
    }

     */



}