package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityForgotPasswordConfirmBinding

class ForgotPasswordConfirmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordConfirmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConfirm.setOnClickListener{
            callLogin()
        }

        supportActionBar?.hide()

    }

    private fun callLogin(){

        val novaSenha = binding.editPassword.text.toString()
        val confirmSenha = binding.editPasswordConfirm.text.toString()

        if(novaSenha.equals(confirmSenha) && novaSenha != ""){
            Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_LONG).show()
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }else if(!novaSenha.equals(confirmSenha)){
            Toast.makeText(this, R.string.invalid_registre,Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this,R.string.camp_blank, Toast.LENGTH_LONG).show()
        }

    }
}