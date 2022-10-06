package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivitySegurancaConfigBinding
import br.arc_camp_tcc.soperito.viewModel.SecurityViewModel

class SegurancaConfigActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivitySegurancaConfigBinding
    private lateinit var viewModel : SecurityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySegurancaConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(SecurityViewModel::class.java)

        binding.editSenhaAntiga.setOnClickListener(this)
        binding.editSenhaNova.setOnClickListener(this)
        binding.editSenhaConfirm.setOnClickListener(this)

        binding.btnCancelar.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_cancelar){
            startActivity(Intent(this,MenuConfigActivity::class.java))
            finish()
        }else if(v.id == R.id.btn_salvar){
            confirmDados()
        }
    }

    private fun observe(){
        viewModel.verifySenha.observe(this){
            if(it.status()){
                alteraSenha()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.alteraSenha.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.alter_password, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MenuConfigActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun confirmDados(){
        val senha = binding.editSenhaAntiga.text.toString()
        viewModel.confirmSenha(senha)
    }

    private fun alteraSenha(){
        val Nsenha = binding.editSenhaNova.text.toString()
        val ConfirmSenha = binding.editSenhaConfirm.text.toString()

        if(Nsenha.equals(ConfirmSenha)){
            viewModel.alterSenha(Nsenha)
        }
    }

}