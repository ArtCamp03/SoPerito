package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityProfileChoiceBinding
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
            startActivity(Intent(this, ContaPeritoActivity::class.java))
            finish()
        }else if(v.id == R.id.button_empregador){
            startActivity(Intent(this, ContaEmpregadorActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.loadUsuario.observe(this){
            if(it.status()){
                Toast.makeText(this, R.string.load_dados, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadUser(){
        viewModel.loadDataUser()
    }

}