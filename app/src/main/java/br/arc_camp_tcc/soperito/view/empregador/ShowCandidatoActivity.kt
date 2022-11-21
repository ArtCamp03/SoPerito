package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityShowCandidatoBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.viewModel.CurriculosViewModel

class ShowCandidatoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityShowCandidatoBinding
    private lateinit var viewModel : CurriculosViewModel

    // atualiza codperito
  //  private var lerDados = LerDados(application)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // variaveis de classe
        binding = ActivityShowCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this).get(CurriculosViewModel::class.java)

        // eventos
        binding.btnInfoCandidato.setOnClickListener(this)

        loadData()

        observe()

        supportActionBar?.hide()
    }


    override fun onClick(v: View) {
        if(v.id == R.id.btn_info_candidato){
            startActivity(Intent(this, InfoCandidatoActivity::class.java))
        }
    }

    private fun observe(){
        viewModel.loadCurriculoSuccess.observe(this){
            binding.editNomeVaga.setText(it.servico)
            binding.editTempXp.setText(it.temp)
            binding.editDescriptModoServico.setText(it.obs)
            binding.editValorMin.setText(it.valor)
            binding.texData.setText(it.dataCurriculo)
            binding.editEsp.setText(it.espec)
            binding.editExp.setText(it.exp)
        }

        viewModel.loadCurriculoErr.observe(this){
            if(!it.status()){
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadData(){
        val bundle =  intent.extras
        if(bundle != null){
            val codPerito = bundle.getInt(DataBaseConstants.BUNDLE.COD_PERITO)
            val codCurriculo = bundle.getInt(DataBaseConstants.BUNDLE.COD_CURRICULO).toInt()
           // lerDados.getPeritoCod = codPerito.toString()
            viewModel.loadCurriucloFireB(codCurriculo)
        }else{
            Toast.makeText(applicationContext, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
        }
    }

}