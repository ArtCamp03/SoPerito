package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilPeritoBinding
import br.arc_camp_tcc.soperito.viewModel.PerfilPeritoViewModel

class PerfilPeritoActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPerfilPeritoBinding
    private lateinit var viewModel : PerfilPeritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PerfilPeritoViewModel::class.java)

        binding.btnFloatEdit.setOnClickListener(this)
        binding.btnOk.setOnClickListener(this)

        viewModel.loadPerfil()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_float_edit){
            startActivity(Intent(this, EditPerfilActivity::class.java))
        }else if(v.id == R.id.btn_ok){
            Toast.makeText(this, R.string.save, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MenuPeritoActivity::class.java))
        }
    }

    private fun observe(){
        viewModel.loadPerfil.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext,R.string.load_dados, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.nome.observe(this){
            binding.textNameUser.setText(it)
        }

        viewModel.esp.observe(this){
            binding.textEspecialidadeOne.setText(it)
        }

        viewModel.exp.observe(this){
            binding.textExperienciaOne.setText(it)
        }
    }

}