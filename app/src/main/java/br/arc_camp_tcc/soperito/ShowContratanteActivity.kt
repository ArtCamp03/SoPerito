package br.arc_camp_tcc.soperito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.databinding.ActivityShowContratanteBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.viewModel.ListEmpregadorViewModel

class ShowContratanteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityShowContratanteBinding
    private lateinit var viewModel : ListEmpregadorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowContratanteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(ListEmpregadorViewModel::class.java)

        // eventos
        binding.btnInfoContratante.setOnClickListener(this)

        loadData()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_info_contratante){
            startActivity(Intent(this, InfoContratanteActivity::class.java))
        }
    }

    private fun observe(){
        viewModel.loadVagaSuccess.observe(this){
            binding.editNomeVaga.setText(it.servico)
            binding.editTempXp.setText(it.tempExp)
            binding.editDescriptModoServico.setText(it.descricao)
            binding.editValorMin.setText(it.dispPagar)
            binding.textData.setText(it.vData)
        }

        viewModel.loadVagaOnfailure.observe(this){
            if(!it.status()){
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadData(){
        val bundle =  intent.extras
        if(bundle != null){
            val codCurriuyclo = bundle.getInt(DataBaseConstants.BUNDLE.COD_VAGA)
            viewModel.loadVaga(codCurriuyclo)
        }else{
            Toast.makeText(applicationContext, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
        }
    }



}