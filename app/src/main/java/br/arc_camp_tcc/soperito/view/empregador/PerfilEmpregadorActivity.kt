package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilEmpregadorBinding
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.viewModel.PerfilEmpregadorViewModel

class PerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPerfilEmpregadorBinding
    private lateinit var viewModel : PerfilEmpregadorViewModel

    private lateinit var controlaDados: Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PerfilEmpregadorViewModel::class.java)

        binding.btnFloatEditEmp.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)

        // inicializa controle de dados
        controlaDados = Controlador()

        observe()
        loadPerfil()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_float_edit_emp){
            startActivity(Intent(this, EditPerfilEmpregadorActivity::class.java))
        }else if(v.id == R.id.btn_ok){
            Toast.makeText(this,R.string.save,Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MenuEmpregadorActivity::class.java))
        }
    }

    private fun observe(){
        viewModel.nomeEmp.observe(this){
            binding.textNameUser.setText(it)
        }

        viewModel.email.observe(this){
            binding.textResultEmail.setText(it)
        }

        viewModel.telefone.observe(this){
            binding.textResultTelefone.setText(it)
        }

        viewModel.loadPerfil.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext,R.string.load_dados, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun loadPerfil(){
        if(controlaDados.firebase){
            viewModel.loadPerfilFireB()
        }else if(controlaDados.api){
            // viewModel.loadPerfilEmp()
        }
    }

}