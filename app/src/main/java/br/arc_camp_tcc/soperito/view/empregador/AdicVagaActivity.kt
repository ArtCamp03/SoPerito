package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityAdicVagaBinding
import br.arc_camp_tcc.soperito.view.perito.PerfilPeritoActivity

class AdicVagaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityAdicVagaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicVagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.img_action_bar) {
            val perfil = Intent(this, PerfilPeritoActivity::class.java)
            startActivity(perfil)
        } else if (v.id == R.id.btn_salvar) {
            var nomeVaga = binding.editNomeVaga.text.toString()
            var tempExp = binding.textTempXp.text.toString()
            var descriptVaga = binding.editDescriptVaga.text.toString()
            var valorMin = binding.editValorMin.text.toString()

            if(nomeVaga != "" && tempExp != "" && descriptVaga != "" && valorMin != ""){
                Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_SHORT)
                val salver = Intent(this, MenuEmpregadorActivity::class.java)
                startActivity(salver)
            }
            Toast.makeText(this, R.string.invalid_registre, Toast.LENGTH_SHORT)
        } else if (v.id == R.id.btn_cancelar) {
            val cancelar = Intent(this, MenuEmpregadorActivity::class.java)
            startActivity(cancelar)
        }
    }
}