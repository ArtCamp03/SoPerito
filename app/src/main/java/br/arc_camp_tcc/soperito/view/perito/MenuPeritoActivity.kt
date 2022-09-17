package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityMenuPeritoBinding
import br.arc_camp_tcc.soperito.view.usuario.MenuConfigActivity
import br.arc_camp_tcc.soperito.viewModel.PeritoViewModel

class MenuPeritoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMenuPeritoBinding
    private lateinit var viewModel : PeritoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PeritoViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnBusca.setOnClickListener(this)
        binding.btnServicoDispo.setOnClickListener(this)
        binding.btnContatoService.setOnClickListener(this)
        binding.btnAddCurriculo.setOnClickListener(this)
        binding.btnConfig.setOnClickListener(this)

        loadPerito()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar){
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
        }else if(v.id == R.id.btn_busca){
            startActivity(Intent(this, BuscaServicoActivity::class.java))
        }else if(v.id == R.id.btn_servico_dispo){
            startActivity(Intent(this, ServicoDisponiveisActivity::class.java))
        }else if(v.id == R.id.btn_contato_service){
            startActivity(Intent(this, ContatoServicoActivity::class.java))
        }else if(v.id == R.id.btn_add_curriculo){
            startActivity(Intent(this, AdicCurriculoActivity::class.java))
        } else if(v.id == R.id.btn_config){
            startActivity(Intent(this, MenuConfigActivity::class.java))
        }
    }

    private fun observe(){
      viewModel.loadPerito.observe(this) {
          if (it.status()) {
              Toast.makeText(applicationContext, R.string.load_dados, Toast.LENGTH_LONG).show()
          } else {
              Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
          }
      }
    }

    private fun loadPerito() {
        viewModel.loadPerito()
    }

}