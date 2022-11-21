package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityAdicVagaBinding
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.view.perito.PerfilPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.ListEmpregadorViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class AdicVagaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityAdicVagaBinding
    private lateinit var viewModel : ListEmpregadorViewModel

    private lateinit var controlaDados:Controlador

    // busca data no sistema
    private val date = Calendar.getInstance().time
    private val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val dataModf = dateTimeFormat.format(date)

    // provedor de localizacao
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var localGPS : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicVagaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListEmpregadorViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)
        binding.texData.setText(dataModf)

        // instancia localizaçao
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        controlaDados = Controlador()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.img_action_bar) {
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
        } else if (v.id == R.id.btn_salvar) {
            saveVaga()
        } else if (v.id == R.id.btn_cancelar) {
            startActivity(Intent(this, MenuEmpregadorActivity::class.java))
            finish()
        }
    }

    private fun observe(){
        viewModel.saveVaga.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.save, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuEmpregadorActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveVaga(){

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                localGPS = location.toString()
            }

        val vaga = ListEmpregadorModel().apply {
            this.servico = binding.editNomeVaga.text.toString()
            this.tempExp = binding.editTempXp.text.toString()
            this.dispPagar = binding.editValorMin.text.toString()
            this.descricao = binding.editDescricao.text.toString()
            this.vData = dataModf.toString()
            this.local = localGPS.toString()
        }

        if(vaga.servico != "" && vaga.tempExp != "" && vaga.descricao != "" && vaga.dispPagar != ""){
            if(controlaDados.firebase){
                viewModel.saveVagaFireB(vaga)
            }else if(controlaDados.api){
                //viewModel.saveVaga(vaga)
            }
        }else{
            Toast.makeText(applicationContext, R.string.invalid_registre, Toast.LENGTH_LONG)
        }

    }
}