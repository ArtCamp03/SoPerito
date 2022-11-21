package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityServicoDisponiveisBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.EmpregadorListener
import br.arc_camp_tcc.soperito.view.adapter.EmpregadorAdapter
import br.arc_camp_tcc.soperito.view.empregador.VagaEmpregadorActivity
import br.arc_camp_tcc.soperito.viewModel.ListEmpregadorViewModel

class ServicoDisponiveisActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityServicoDisponiveisBinding
    private lateinit var viewModel : ListEmpregadorViewModel

    private val adapter = EmpregadorAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServicoDisponiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListEmpregadorViewModel::class.java)

        binding.recyclerDispService.layoutManager = LinearLayoutManager(this)
        binding.recyclerDispService.adapter = adapter

        val listener = object : EmpregadorListener {
            override fun onlistClick(id: Int) {
                val intent = Intent(this@ServicoDisponiveisActivity, VagaEmpregadorActivity::class.java)
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

        binding.imgActionBar.setOnClickListener(this)

        observe()

        supportActionBar?.hide()

    }

    // quando voltar a açao on resume e chamado
    override fun onResume() {
        super.onResume()
        viewModel.listEmpregadorFireB()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.img_action_bar){
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
            finish()
        }
    }

    private fun observe() {
        viewModel.listEmpregador.observe(this) {
            adapter.updateEmp(it)
        }

        viewModel.listFailure.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

}