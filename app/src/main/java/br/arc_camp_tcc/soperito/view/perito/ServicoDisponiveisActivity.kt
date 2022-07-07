package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityServicoDisponiveisBinding

class ServicoDisponiveisActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityServicoDisponiveisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServicoDisponiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar){
            val btn_perfil = Intent(this, PerfilActivity::class.java)
            startActivity(btn_perfil)
        }
    }
}