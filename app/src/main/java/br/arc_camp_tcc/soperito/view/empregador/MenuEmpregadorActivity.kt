package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityMenuEmpregadorBinding
import br.arc_camp_tcc.soperito.progressBar.DialogProgress
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.view.usuario.MenuConfigActivity
import br.arc_camp_tcc.soperito.viewModel.EmpregadorViewModel

class MenuEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMenuEmpregadorBinding
    private lateinit var viewModel : EmpregadorViewModel

    // gerencia entre API e firebase
    private lateinit var controlaDados: Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(EmpregadorViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnBusca.setOnClickListener(this)
        binding.btnPeritosDispo.setOnClickListener(this)
        binding.btnAddVaga.setOnClickListener(this)
        binding.btnCandidatos.setOnClickListener(this)
        binding.btnConfig.setOnClickListener(this)

        controlaDados = Controlador()
        observe()
        loadEmpregador()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar){
            val perfil = Intent(this, PerfilEmpregadorActivity::class.java)
            startActivity(perfil)
        }else if(v.id == R.id.btn_busca){
            val busc_perito = Intent(this, BuscaPeritoActivity::class.java)
            startActivity(busc_perito)
        }else if(v.id == R.id.btn_peritos_dispo){
            val perito_disp = Intent(this, PeritosDisponiveisActivity::class.java)
            startActivity(perito_disp)
        }else if(v.id == R.id.btn_add_vaga){
            val addVaga = Intent(this, AdicVagaActivity::class.java)
            startActivity(addVaga)
        }else if(v.id == R.id.btn_candidatos){
            val candidatos = Intent(this, CandidatosEmpregadorActivity::class.java)
            startActivity(candidatos)
        } else if(v.id == R.id.btn_config){
            val config = Intent(this, MenuConfigActivity::class.java)
            startActivity(config)
        }
    }

    private fun observe(){
        val dialogProgress = DialogProgress(this)
        dialogProgress.startLoading()

        viewModel.loadEmp.observe(this){
            if(it.status()){
                dialogProgress.isDismiss()
                //Toast.makeText(applicationContext, R.string.load_dados, Toast.LENGTH_LONG).show()
            }else{
                dialogProgress.isDismiss()
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadEmpregador(){
        if(controlaDados.firebase){
            viewModel.loadEmpregadorFireb()
        }else if(controlaDados.api){
           // viewModel.loadEmrpegador()
        }
    }

}