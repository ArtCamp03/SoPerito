package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityAdicCurriculoBinding

class AdicCurriculoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityAdicCurriculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicCurriculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar) {
            val perfil = Intent(this, PerfilActivity::class.java)
            startActivity(perfil)
        }else if(v.id == R.id.btn_salvar) {
            var nomeVaga = binding.editNomeVaga.text.toString()
            var tempExp = binding.editTempXp.text.toString()
            var obs = binding.editObs.text.toString()
            var valorMin = binding.editValorMin.text.toString()

            if(nomeVaga != "" && tempExp != "" && obs != "" && valorMin != ""){
                Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_SHORT)
                val salvar = Intent(this, MenuPeritoActivity::class.java)
                startActivity(salvar)
            }else {
                Toast.makeText(this, R.string.invalid_registre, Toast.LENGTH_SHORT)
            }

        }else if(v.id == R.id.btn_cancelar) {
            val cancelar = Intent(this, MenuPeritoActivity::class.java)
            startActivity(cancelar)
        }
    }
}