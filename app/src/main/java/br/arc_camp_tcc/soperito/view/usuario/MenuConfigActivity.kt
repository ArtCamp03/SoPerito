package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityMenuConfigBinding
import br.arc_camp_tcc.soperito.progressBar.DialogProgress
import br.arc_camp_tcc.soperito.view.perito.PerfilPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.MenuConfigViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuConfigActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMenuConfigBinding
    private lateinit var mViewModel : MenuConfigViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(MenuConfigViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnConfigSeguranca.setOnClickListener(this)
        binding.btnTrocaPerfil.setOnClickListener(this)
        binding.btnSairDaConta.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar) {
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
        }else if(v.id == R.id.btn_config_seguranca){
            startActivity(Intent(this, SegurancaConfigActivity::class.java))
        }else if(v.id == R.id.btn_troca_perfil){
            startActivity(Intent(this, ProfileChoiceActivity::class.java))
            finish()
        }else if(v.id == R.id.btn_sair_da_conta){
            userLogout()
        }
    }

    private fun observe(){
        mViewModel.logOut.observe(this){
            if(it.status()){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun userLogout(){
        mViewModel.logout()
    }

}