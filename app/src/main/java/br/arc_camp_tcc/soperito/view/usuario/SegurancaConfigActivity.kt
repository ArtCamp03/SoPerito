package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivitySegurancaConfigBinding

class SegurancaConfigActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivitySegurancaConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySegurancaConfigBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.editSenhaAntiga.setOnClickListener(this)
        binding.editSenhaNova.setOnClickListener(this)
        binding.editSenhaConfirm.setOnClickListener(this)

        binding.btnCancelar.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_cancelar){
            startActivity(Intent(this,MenuConfigActivity::class.java))
            finish()
        }else if(v.id == R.id.btn_salvar){
            TODO("Tratar action")
        }
    }

    private fun observe(){

    }
}