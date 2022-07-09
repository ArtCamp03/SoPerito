package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityLoginBinding
import br.arc_camp_tcc.soperito.service.model.Person.PersonLoginModel
import br.arc_camp_tcc.soperito.service.model.VerifyLoginModel
import br.arc_camp_tcc.soperito.viewModel.LoginViewModel
import br.arc_camp_tcc.soperito.viewModel.UsuarioViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //var auth: FirebaseAuth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // instancia viewModel e atrela ao ciclo de vida
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.buttonLogin.setOnClickListener(this)
        binding.textFogotPassword.setOnClickListener(this)

        //inicializa eventos
        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            //handleLogin()
            val fogotPassword = Intent(this, ProfileChoiceActivity::class.java)
            startActivity(fogotPassword)
        } else if (v.id == R.id.text_fogot_password) {
            val fogotPassword = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(fogotPassword)
        } else {
            Toast.makeText(this, R.string.erro_login, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observe() {
        mViewModel.login.observe(this) {
            if (it.status()) {
                startActivity(Intent(applicationContext, ProfileChoiceActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLogin() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editPassword.text.toString()
        (mViewModel.doLogin(email, senha))
    }

}