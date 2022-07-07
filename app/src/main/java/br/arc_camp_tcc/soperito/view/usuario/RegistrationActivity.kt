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
import br.arc_camp_tcc.soperito.viewModel.UsuarioViewModel

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityRegistrationBinding
    private lateinit var viewModel : UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel esta atrelada a ativitity
        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)

        binding.buttonConfirm.setOnClickListener(this)
        binding.buttonCancel.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {

        if(v.id == R.id.button_confirm){
            val email = binding.editEmail.text.toString()
            val nome = binding.editName.text.toString()
            val cpf = binding.editCpf.text.toString()
            val telefone = binding.editTelephone.text.toString()
            val senha = binding.editPassword.text.toString()
            val novaSenha = binding.editPasswordConfirm.text.toString()

            val model = UsuarioModel(0,false,false,email,nome,cpf,telefone,senha, 0)
            viewModel.insert(model)

            if(email != "" && nome != "" && nome != "" && cpf != "" && senha != ""){
                Toast.makeText(this,R.string.valid_registre,Toast.LENGTH_LONG).show()
                val registre = Intent(this, LoginActivity::class.java)
                startActivity(registre)
            }else{
                Toast.makeText(this,R.string.camp_blank,Toast.LENGTH_LONG).show()
            }
        }else if(v.id == R.id.button_cancel){
            Toast.makeText(this,R.string.cadastro_no_complete,Toast.LENGTH_LONG).show()
            val cancel = Intent(this, IntroActivity::class.java)
            startActivity(cancel)
        }
    }
}