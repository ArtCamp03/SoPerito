package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityCandidatosEmpregadorBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.PeritoListener
import br.arc_camp_tcc.soperito.view.adapter.PeritoAdapter
import br.arc_camp_tcc.soperito.view.perito.CurriculoPeritoActivity
import br.arc_camp_tcc.soperito.view.perito.PerfilPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.ListPeritoViewModel

class CandidatosEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCandidatosEmpregadorBinding
    private lateinit var viewModel: ListPeritoViewModel

    private val adapter = PeritoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCandidatosEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListPeritoViewModel::class.java)

        val listener = object : PeritoListener {
            override fun onlistClick(id: Int) {
                val intent = Intent(applicationContext, CurriculoPeritoActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.BUNDLE.COD_CURRICULO, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                TODO("Not yet implemented")
            }

        }

        adapter.attachListener(listener)

        binding.imgActionBar.setOnClickListener(this)

        // val recycle = binding.recyclerDispService
        binding.recyclerDispCandidat.layoutManager = LinearLayoutManager(this)
        binding.recyclerDispCandidat.adapter = adapter

        observe()

        supportActionBar?.hide()
    }

    // quando voltar a açao on resume e chamado
    override fun onResume() {
        super.onResume()
        viewModel.listPerito()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.img_action_bar) {
            val perfil = Intent(this, PerfilPeritoActivity::class.java)
            startActivity(perfil)
        }
    }

    private fun observe() {
        viewModel.listPerito.observe(this) {
            adapter.updatePeritos(it)
        }

        viewModel.listFailure.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        }
    }

}