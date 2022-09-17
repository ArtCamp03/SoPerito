package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityBuscaServicoPeritoBinding

class BuscaServicoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityBuscaServicoPeritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBuscaServicoPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_search){
            var pesquisa = binding.editBarraSearch.text.toString()
        }else if(v.id == R.id.img_action_bar){
            val btn_perfil = Intent(this, PerfilPeritoActivity::class.java)
            startActivity(btn_perfil)
        }
    }

}