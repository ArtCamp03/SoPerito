package br.arc_camp_tcc.soperito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.databinding.ActivityInfoCandidatoBinding
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity
import br.arc_camp_tcc.soperito.viewModel.PeritoViewModel

class InfoCandidatoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityInfoCandidatoBinding
    private lateinit var viewModel : PeritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PeritoViewModel::class.java)
        binding.btnOk.setOnClickListener(this)

        loadData()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_ok){
            startActivity(Intent(this, MenuEmpregadorActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.losdInfoSuccess.observe(this){
            binding.editNomePerito.setText(it.nome)
            binding.editEmail.setText(it.email)
            binding.editTelefone.setText(it.telefone)
        }
        viewModel.losdInfoFail.observe(this){
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun loadData(){
        viewModel.loadInfoCandiato()
    }

}