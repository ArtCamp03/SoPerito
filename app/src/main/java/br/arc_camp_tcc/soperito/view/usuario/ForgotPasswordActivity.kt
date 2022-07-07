package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConfirm.setOnClickListener{
            callFogotCodePassword()
        }

        supportActionBar?.hide()
    }

    private fun callFogotCodePassword(){

        val email = binding.editEmail.text.toString()
        val cpf = binding.editCpf.text.toString()

        if(email != "" && cpf != ""){
            Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_LONG).show()
            val fogotPassCode = Intent(this, ForgotPasswordCodeActivity::class.java)
            startActivity(fogotPassCode)
        }else{
            Toast.makeText(this, R.string.camp_blank, Toast.LENGTH_LONG).show()
        }

    }
}