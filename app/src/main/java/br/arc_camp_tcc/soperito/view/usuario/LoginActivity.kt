package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityLoginBinding
import br.arc_camp_tcc.soperito.service.model.VerifyLoginModel
import br.arc_camp_tcc.soperito.viewModel.UsuarioViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel : UsuarioViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //var auth: FirebaseAuth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // instancia viewModel e atrela ao ciclo de vida
        mViewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        binding.buttonLogin.setOnClickListener(this)
        binding.textFogotPassword.setOnClickListener(this)

        //inicializa eventos
        setLiteners()
        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_login){

            val email = binding.editEmail.text.toString()
            val senha = binding.editPassword.text.toString()

            if(verifyLoggedUser(email,senha)){
                Toast.makeText(this,R.string.confirm_user, Toast.LENGTH_SHORT).show()
                val login = Intent(this, ProfileChoiceActivity::class.java)
                startActivity(login)
            }else{
                Toast.makeText(this,R.string.erro_login, Toast.LENGTH_SHORT).show()
            }
        }else if(v.id == R.id.text_fogot_password){
            val fogotPassword = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(fogotPassword)
        }
    }

    private fun setLiteners(){
        //
    }

    private fun observe(){
        //
    }

    private fun verifyLoggedUser(email: String,senha: String): Boolean{
        val verifyLog = VerifyLoginModel(email, senha)

        if(mViewModel.getLogin(verifyLog.email,verifyLog.senha)) {
            return  true
        }
        return false
    }

}