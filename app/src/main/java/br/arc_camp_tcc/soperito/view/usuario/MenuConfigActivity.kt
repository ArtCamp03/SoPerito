package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityMenuConfigBinding
import br.arc_camp_tcc.soperito.view.perito.PerfilActivity

class MenuConfigActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMenuConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnConfigSeguranca.setOnClickListener(this)
        binding.btnTrocaPerfil.setOnClickListener(this)
        binding.btnSairDaConta.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar) {
            val perfil = Intent(this, PerfilActivity::class.java)
            startActivity(perfil)
        }else if(v.id == R.id.btn_config_seguranca){
            //
        }else if(v.id == R.id.btn_troca_perfil){
            val trocaPerfil = Intent(this, ProfileChoiceActivity::class.java)
            startActivity(trocaPerfil)
        }else if(v.id == R.id.btn_sair_da_conta){
            val sairConta = Intent(this, LoginActivity::class.java)
            startActivity(sairConta)
        }
    }

}