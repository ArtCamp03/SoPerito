package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityBuscaServicoPeritoBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.EmpregadorListener
import br.arc_camp_tcc.soperito.view.adapter.EmpregadorAdapter
import br.arc_camp_tcc.soperito.view.empregador.VagaEmpregadorActivity
import br.arc_camp_tcc.soperito.viewModel.ListEmpregadorViewModel

class BuscaServicoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityBuscaServicoPeritoBinding
    private lateinit var viewModel : ListEmpregadorViewModel

    private val adapter = EmpregadorAdapter()

    private var pesquisa : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBuscaServicoPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListEmpregadorViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)
        binding.btnSearch.setOnClickListener(this)

        // val recycle = binding.recyclerDispService
        binding.recyclerBuscaService.layoutManager = LinearLayoutManager(this)
        binding.recyclerBuscaService.adapter = adapter

        val listener = object : EmpregadorListener{
            override fun onlistClick(id: Int) {
                val intent = Intent(this@BuscaServicoActivity, VagaEmpregadorActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.BUNDLE.COD_VAGA, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                TODO("Not yet implemented")
            }

        }

        adapter.attachListener(listener)

        observe()

        supportActionBar?.hide()
    }

    // quando voltar a açao on resume e chamado
    override fun onResume() {
        super.onResume()
        viewModel.buscEmpregador(pesquisa)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_search){
            buscar()
        }else if(v.id == R.id.img_action_bar){
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
            finish()
        }
    }

    private fun observe() {
        viewModel.bscEmrpegadorSucess.observe(this) {
            adapter.updateEmp(it)
        }

        viewModel.bscEmrpegadorFailure.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun buscar(){
        pesquisa = binding.editBarraSearch.text.toString()
        onResume()
    }

}