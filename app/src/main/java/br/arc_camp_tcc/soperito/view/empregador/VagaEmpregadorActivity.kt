package br.arc_camp_tcc.soperito.view.empregador

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.databinding.ActivityVagaEmpregadorBinding

class VagaEmpregadorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVagaEmpregadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVagaEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}