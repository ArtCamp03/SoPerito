package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityForgotPasswordCodeBinding

class ForgotPasswordCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConfirm.setOnClickListener{
            callFogotPasswordConmfirm()
        }

        supportActionBar?.hide()

    }

    private fun callFogotPasswordConmfirm(){
        val code = binding.editConfirmCode.text.toString()

        if(code != ""){
            Toast.makeText(this,R.string.valid_registre,Toast.LENGTH_LONG).show()
            val fogotPassConfirm = Intent(this, ForgotPasswordConfirmActivity::class.java)
            startActivity(fogotPassConfirm)
        }else {
            Toast.makeText(this,R.string.camp_blank,Toast.LENGTH_LONG).show()
        }

    }
}