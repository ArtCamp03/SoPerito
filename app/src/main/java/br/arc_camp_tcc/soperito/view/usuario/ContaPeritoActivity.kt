package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityContaPeritoBinding
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.view.perito.MenuPeritoActivity
import br.arc_camp_tcc.soperito.viewModel.PeritoViewModel

class ContaPeritoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityContaPeritoBinding
    private lateinit var viewModel: PeritoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContaPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PeritoViewModel::class.java)

        binding.btnConfirm.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
            if (v.id == R.id.btn_confirm) {
                criaPerito()
            } else if (v.id == R.id.btn_cancel) {
                startActivity(Intent(this, ProfileChoiceActivity::class.java))
                finish()
            }
    }

    private fun observe() {
        viewModel.savePerito.observe(this) {
            if (it.status()) {
                Toast.makeText(applicationContext, R.string.valid_registre, Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun criaPerito() {
        viewModel.criaPerito()
    }

}