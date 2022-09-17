package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityCurriculoPeritoBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity
import br.arc_camp_tcc.soperito.viewModel.PeritoViewModel

class CurriculoPeritoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityCurriculoPeritoBinding
    private lateinit var viewModel : PeritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // variaveis de classe
        viewModel= ViewModelProvider(this).get(PeritoViewModel::class.java)
        binding = ActivityCurriculoPeritoBinding.inflate(layoutInflater)

        // eventos
        binding.btnAceitarCurriculo.setOnClickListener(this)
        binding.btnRecusarCurrculo.setOnClickListener(this)

        loadData()

        observe()

        // layout
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_aceitar_curriculo){

        }else if(v.id == R.id.btn_recusar_currculo){
            startActivity(Intent(this, MenuEmpregadorActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.saveCurriculo.observe(this){
            if(it.status()){
                Toast.makeText(this, R.string.save, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }

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
          //  val codPerito = bundle.getInt(DataBaseConstants.BUNDLE.COD_PERITO).toInt()
            val codCurriuyclo = bundle.getInt(DataBaseConstants.BUNDLE.COD_CURRICULO)
            viewModel.loadCurriuclo(codCurriuyclo)
        }else{
            Toast.makeText(applicationContext, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
        }

    }

}