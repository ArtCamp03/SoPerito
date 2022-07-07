package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityMenuPeritoBinding
import br.arc_camp_tcc.soperito.view.usuario.MenuConfigActivity

class MenuPeritoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMenuPeritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnBusca.setOnClickListener(this)
        binding.btnServicoDispo.setOnClickListener(this)
        binding.btnContatoService.setOnClickListener(this)
        binding.btnAddCurriculo.setOnClickListener(this)
        binding.btnConfig.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar){
            val perfil = Intent(this, PerfilActivity::class.java)
            startActivity(perfil)
        }else if(v.id == R.id.btn_busca){
            val busc_service = Intent(this, BuscaServicoActivity::class.java)
            startActivity(busc_service)
        }else if(v.id == R.id.btn_servico_dispo){
            val service_disp = Intent(this, ServicoDisponiveisActivity::class.java)
            startActivity(service_disp)
        }else if(v.id == R.id.btn_contato_service){
            val contato = Intent(this, ContatoServicoActivity::class.java)
            startActivity(contato)
        }else if(v.id == R.id.btn_add_curriculo){
            val curriculo = Intent(this, AdicCurriculoActivity::class.java)
            startActivity(curriculo)
        } else if(v.id == R.id.btn_config){
            val config = Intent(this, MenuConfigActivity::class.java)
            startActivity(config)
        }
    }
}