package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener{
            callLogin()
        }

        binding.buttonCadastro.setOnClickListener{
            callRegistration()
        }

        supportActionBar?.hide()
    }

    private fun callLogin() {
        val login = Intent(this, LoginActivity::class.java)
        startActivity(login)
    }

    private fun callRegistration() {
        val registration = Intent(this, RegistrationActivity::class.java)
        startActivity(registration)
    }

}