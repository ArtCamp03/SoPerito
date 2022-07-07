package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityProfileChoiceBinding

class ProfileChoiceActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityProfileChoiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPerito.setOnClickListener(this)
        binding.buttonEmpregador.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_perito) {
            val perito = Intent(this, ContaPeritoActivity::class.java)
            startActivity(perito)
        }else if(v.id == R.id.button_empregador){
            val empregador = Intent(this, ContaEmpregadorActivity::class.java)
            startActivity(empregador)
        }
    }
}