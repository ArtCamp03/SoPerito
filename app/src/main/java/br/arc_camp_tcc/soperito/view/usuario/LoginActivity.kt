package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityLoginBinding
import br.arc_camp_tcc.soperito.progressBar.DialogProgress
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth


    // gerencia entre API e firebase
    private lateinit var controlaDados : Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        //var auth: FirebaseAuth = Firebase.auth

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // instancia viewModel e atrela ao ciclo de vida
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.buttonLogin.setOnClickListener(this)
        binding.textFogotPassword.setOnClickListener(this)

        // inicializa variavel de autetificaçao
        auth = Firebase.auth

        //  verifica se o usuario ja estava logado
        mViewModel.verifyLoggedUser(auth)

        controlaDados = Controlador()

        //inicializa eventos
        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            handleLogin()
        } else if (v.id == R.id.text_fogot_password) {
            val fogotPassword = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(fogotPassword)
            finish()
        } else {
            Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show()
        }
    }

    // observa variavel
    private fun observe() {
        mViewModel.login.observe(this) {
            if (it.status()) {
                Toast.makeText(applicationContext, "Login com sucesso!!", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(Intent(applicationContext, ProfileChoiceActivity::class.java))
            } else {
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }

        mViewModel.loggedUser.observe(this){
            if(it){
                finish()
                startActivity(Intent(applicationContext, ProfileChoiceActivity::class.java))
            }
        }
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editPassword.text.toString()

        if(!email.trim().equals("") && !senha.trim().equals("")){
            if(controlaDados.firebase){
                loginUsuario(email, senha)
            }else{
                controlaDados.firebase = false
                controlaDados.api = true
            }

            if (controlaDados.api){
                mViewModel.doLogin(email,senha)
            }
        }

    }

    // ---------------------------- MODEL VIEW  -----------------------------------

    private fun loginUsuario(email: String, password: String) {

        val dialogoProgress = DialogProgress(this)
        dialogoProgress.startLoading()

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                dialogoProgress.isDismiss()
                startActivity(Intent(applicationContext, ProfileChoiceActivity::class.java))
                finish()
            }.addOnFailureListener{ err ->
                dialogoProgress.isDismiss()
                // tratar erro do firebase
                val erro = err.message.toString()
                errosFirebase(erro)
            }

    }

    private fun errosFirebase(erro: String){
        if(erro.contains("The email address is badly formatted")){
            Toast.makeText(baseContext, "Email invalido.", Toast.LENGTH_SHORT).show()
        }else if(erro.contains("There is no user record corresponding to this identifier")){
            Toast.makeText(baseContext, "Email nao existente.", Toast.LENGTH_SHORT).show()
        }else if(erro.contains("The password is invalid or the user does not have a password")){
            Toast.makeText(baseContext, "Senha invalida.", Toast.LENGTH_SHORT).show()
        }
    }


}