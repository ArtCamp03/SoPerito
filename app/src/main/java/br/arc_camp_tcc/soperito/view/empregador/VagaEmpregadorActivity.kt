package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityVagaEmpregadorBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.view.perito.MenuPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.ListEmpregadorViewModel

class VagaEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityVagaEmpregadorBinding
    private lateinit var viewModel: ListEmpregadorViewModel

    // gerencia entre API e firebase
    private lateinit var controlaDados: Controlador

     private var codVaga : Int = 0
     private var codEmp : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVagaEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListEmpregadorViewModel::class.java)

        binding.btnCandidatar.setOnClickListener(this)
        binding.btnRecusar.setOnClickListener(this)

        controlaDados = Controlador()

        loadData()

        observe()

        // layout
        setContentView(binding.root)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_candidatar){
            candidatar()
        }else if(v.id == R.id.btn_recusar){
            startActivity(Intent(this, MenuPeritoActivity::class.java))
            finish()
        }
    }

    private fun  loadData(){
        val bundle =  intent.extras
        if(bundle != null){
            val codVaga = bundle.getInt(DataBaseConstants.BUNDLE.COD_VAGA)
            if(controlaDados.firebase){
                viewModel.loadVagaFireB(codVaga)
            }else if(controlaDados.api) {
                // viewModel.loadVaga(codVaga)
            }
        }else{
            Toast.makeText(applicationContext, R.string.erro_inesperado, Toast.LENGTH_LONG).show()
        }
    }

    private fun observe(){
        viewModel.saveVaga.observe(this){
            if(it.status()){
                Toast.makeText(this, R.string.save, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuEmpregadorActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loadVagaSuccess.observe(this){
            binding.editNomeVaga.setText(it.servico)
            binding.editTempXp.setText(it.tempExp)
            binding.editValorMin.setText(it.dispPagar)
            binding.editDescriptModoServico.setText(it.descricao)
            binding.textData.setText(it.vData)

            // dados da vaga em questao
            codVaga = it.codVaga
            codEmp = it.codEmp
        }

        viewModel.loadVagaOnfailure.observe(this){
            if(!it.status()){
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.addCandidato.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.save, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun candidatar(){
        if(controlaDados.firebase){
            viewModel.candidatoFireB(codVaga, codEmp)
        }else if(controlaDados.api) {
            // viewModel.candidato(codVaga, codEmp)
        }
    }


}