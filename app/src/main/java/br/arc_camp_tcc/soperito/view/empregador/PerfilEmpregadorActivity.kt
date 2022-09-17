package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilEmpregadorBinding
import br.arc_camp_tcc.soperito.viewModel.EmpregadorViewModel

class PerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPerfilEmpregadorBinding
    private lateinit var viewModel : EmpregadorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmpregadorViewModel::class.java)

        binding.btnFloatEditEmp.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)

        loadPerfil()

        observe()

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

    }

    private fun loadPerfil(){
        viewModel.loadPerfilEmp()
    }

}