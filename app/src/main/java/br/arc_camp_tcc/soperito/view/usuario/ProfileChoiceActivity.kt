package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityProfileChoiceBinding
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity
import br.arc_camp_tcc.soperito.view.perito.MenuPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.ProfileChoiceViewModel

class ProfileChoiceActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityProfileChoiceBinding
    private lateinit var viewModel : ProfileChoiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ProfileChoiceViewModel::class.java)

        binding.buttonPerito.setOnClickListener(this)
        binding.buttonEmpregador.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        loadUser()
        if(v.id == R.id.button_perito) {
            contPerito()
        }else if(v.id == R.id.button_empregador){
            contEmpregador()
        }
    }

    private fun observe(){
        viewModel.loadUsuario.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.load_dados, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.cntPerito.observe(this){
            if(it){
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            }else{
                startActivity(Intent(applicationContext, ContaPeritoActivity::class.java))
                finish()
            }
        }

        viewModel.cntEmpregador.observe(this){
            if(it){
                startActivity(Intent(applicationContext, MenuEmpregadorActivity::class.java))
                finish()
            }else{
                startActivity(Intent(applicationContext, ContaEmpregadorActivity::class.java))
                finish()
            }
        }
    }

    private fun loadUser(){
        viewModel.loadDataUser()
    }

    private fun contPerito(){
        viewModel.verifyContaPerito()
    }

    private fun contEmpregador(){
        viewModel.verifyContaEmpregador()
    }

}