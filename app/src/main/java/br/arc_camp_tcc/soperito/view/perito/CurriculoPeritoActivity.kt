package br.arc_camp_tcc.soperito.view.perito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.databinding.ActivityCurriculoPeritoBinding

class CurriculoPeritoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCurriculoPeritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCurriculoPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}