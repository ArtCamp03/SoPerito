package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityContaEmpregadorBinding
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity
import br.arc_camp_tcc.soperito.viewModel.EmpregadorViewModel

class ContaEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityContaEmpregadorBinding
    private lateinit var viewModel : EmpregadorViewModel

    // gerencia entre API e firebase
    private lateinit var controlaDados: Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContaEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmpregadorViewModel::class.java)

        binding.btnConfirm.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)

        controlaDados = Controlador()

        observe()

        supportActionBar?.hide()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_confirm) {
            criaEmpregador()
        }else if(v.id == R.id.btn_cancel){
            val telaChoice = Intent(this, ProfileChoiceActivity::class.java)
            startActivity(telaChoice)
        }
    }

    private fun observe(){
        viewModel.criaEmp.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.valid_registre, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuEmpregadorActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun criaEmpregador(){
        if(controlaDados.firebase){
            viewModel.contaEmpregadorFireb()
        }else if(controlaDados.api){
           // viewModel.criaEmrpegador()
        }

    }

}