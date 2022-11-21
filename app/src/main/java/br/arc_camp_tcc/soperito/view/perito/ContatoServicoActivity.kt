package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityContatoServicoBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.EmpregadorListener
import br.arc_camp_tcc.soperito.view.adapter.EmpregadorAdapter
import br.arc_camp_tcc.soperito.viewModel.ContratadosViewModel

class ContatoServicoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityContatoServicoBinding
    private lateinit var viewModel : ContratadosViewModel

    private val adapter = EmpregadorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContatoServicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ContratadosViewModel::class.java)

        binding.imgActionBar.setOnClickListener(this)

        // val recycle = binding.recyclerDispService
        binding.recyclerDispService.layoutManager = LinearLayoutManager(this)
        binding.recyclerDispService.adapter = adapter

        val listener = object : EmpregadorListener {
            override fun onlistClick(id: Int) {
                val intent = Intent(applicationContext, ShowContratanteActivity::class.java)
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
        viewModel.contatoServicoFireB()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_info_candidato) {
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
        }
    }

    private fun observe(){
        viewModel.listEmpregador.observe(this) {
            adapter.updateEmp(it)
        }

        viewModel.listFailure.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

}