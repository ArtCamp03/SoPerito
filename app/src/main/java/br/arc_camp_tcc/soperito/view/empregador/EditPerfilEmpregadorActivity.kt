package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityEditPerfilEmpregadorBinding
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilEmpregadorBinding

class EditPerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditPerfilEmpregadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            var nome = binding.editName.text.toString()
            var endereco = binding.editTelefone.text.toString()
            var telefone = binding.editTelefone.text.toString()
            var exp = binding.editXp.text.toString()

            if (nome != "" && endereco != "" && telefone != "" && exp != "" ) {
                Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_SHORT)
                val btnSalvar = Intent(this, ActivityPerfilEmpregadorBinding::class.java)
                startActivity(btnSalvar)
            } else {
                Toast.makeText(this, R.string.invalid_registre, Toast.LENGTH_SHORT)
            }
        } else if (v.id == R.id.btn_cancelar) {
            val btnCancelar = Intent(this, ActivityPerfilEmpregadorBinding::class.java)
            startActivity(btnCancelar)
        }
    }

}