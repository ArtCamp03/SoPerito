package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityEditPerfilEmpregadorBinding
import br.arc_camp_tcc.soperito.viewModel.EmpregadorViewModel

class EditPerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditPerfilEmpregadorBinding
    private lateinit var viewModel : EmpregadorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmpregadorViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            editPerfil()
        } else if (v.id == R.id.btn_cancelar) {
            startActivity(Intent(this, PerfilEmpregadorActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.editPerfil.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.valid_registre, Toast.LENGTH_LONG)
                startActivity(Intent(applicationContext, PerfilEmpregadorActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext,it.message(), Toast.LENGTH_LONG)
            }
        }
    }

    private fun editPerfil(){

        val nome = binding.editName.text.toString()
        val telefone = binding.editTelefone.text.toString()
        val email = binding.editEmail.text.toString()

        viewModel.editPerfil(nome, telefone, email)

    }

}