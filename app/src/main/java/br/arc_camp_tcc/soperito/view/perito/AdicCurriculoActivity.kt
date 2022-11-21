package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityAdicCurriculoBinding
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.viewModel.CurriculosViewModel
import java.util.*

class AdicCurriculoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityAdicCurriculoBinding
    private lateinit var viewModel : CurriculosViewModel


    private lateinit var controlaDados:Controlador

    // busca data no sistema
    private val date = Calendar.getInstance().time
    private val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val dataModf = dateTimeFormat.format(date)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicCurriculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CurriculosViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSalvarCurriculo.setOnClickListener(this)
        binding.btnCancelarCurriuclo.setOnClickListener(this)

        binding.textDataCurriculo.setText(dataModf)

        controlaDados = Controlador()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_salvar_curriculo) {
            saveCurriculo()
        }else if(v.id == R.id.btn_cancelar_curriuclo) {
            startActivity(Intent(this, MenuPeritoActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.saveCurriculo.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.save, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveCurriculo(){

        val curriculo = ListPeritoModel().apply {

            this.servico = binding.editNomeVaga.text.toString()
            this.temp = binding.editTempXp.text.toString()
            this.obs = binding.editObs.text.toString()
            this.valor  = binding.editValorMin.text.toString()
            this.dataCurriculo  = dataModf.toString()
        }

        if(!curriculo.servico.trim().isEmpty() && !curriculo.temp.trim().isEmpty() && !curriculo.obs.trim().isEmpty() && !curriculo.valor.trim().isEmpty()){
            if(controlaDados.firebase){
                viewModel.saveCurriculoFireB(curriculo)
            }else if(controlaDados.api){
                //viewModel.saveCurriculo(curriculo)
            }
        }else {
            Toast.makeText(applicationContext, R.string.invalid_registre, Toast.LENGTH_LONG)
        }
    }

}