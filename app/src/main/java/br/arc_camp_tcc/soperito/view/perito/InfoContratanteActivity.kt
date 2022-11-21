package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityInfoContratanteBinding
import br.arc_camp_tcc.soperito.viewModel.EmpregadorViewModel

class InfoContratanteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityInfoContratanteBinding
    private lateinit var viewModel : EmpregadorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoContratanteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmpregadorViewModel::class.java)
        binding.btnOk.setOnClickListener(this)

        loadData()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_ok){
            startActivity(Intent(this, MenuPeritoActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.loadInfoSuccess.observe(this){
            binding.editNomeEmp.setText(it.nome)
            binding.editEmail.setText(it.email)
            binding.editTelefone.setText(it.telefone)
        }
        viewModel.loadInfoFail.observe(this){
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun loadData(){
        //viewModel.loadInfoVaga()
        viewModel.loadInfoVagaFireB()
    }
}