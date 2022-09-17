package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityRegistrationBinding
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.viewModel.RegistryViewModel

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var mViewModel: RegistryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel esta atrelada a ativitity
        mViewModel = ViewModelProvider(this).get(RegistryViewModel::class.java)

        binding.buttonConfirm.setOnClickListener(this)
        binding.buttonCancel.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_confirm) {
            handleSave()
            finish()
        }else if (v.id == R.id.button_cancel) {
            Toast.makeText(this, R.string.cadastro_no_complete, Toast.LENGTH_LONG).show()
            val cancel = Intent(this, IntroActivity::class.java)
            startActivity(cancel)
            finish()
        }
    }

    // observa variavel
    private fun observe(){
        mViewModel.registryUser.observe(this){
            if(it.status()){
                Toast.makeText(this,R.string.valid_registre, Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSave() {
        val usuario = UsuarioModel().apply {
            this.senha = binding.editPassword.text.toString()
            this.email = binding.editEmail.text.toString()
            this.cpf = binding.editCpf.text.toString()
            this.nome = binding.editName.text.toString()
            this.telefone = binding.editTelephone.text.toString()
        }

        val senhaConfirm = binding.editPasswordConfirm.text.toString()

        if(senhaConfirm.equals(usuario.senha)){
            mViewModel.registre(usuario)
        }else{
            Toast.makeText(this, R.string.senha_incompativel, Toast.LENGTH_LONG).show()
        }

    }
}