package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityContaEmpregadorBinding
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity

class ContaEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityContaEmpregadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContaEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConfirm.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)

        supportActionBar?.hide()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_confirm) {
            val menuEmpregador = Intent(this, MenuEmpregadorActivity::class.java)
            startActivity(menuEmpregador)
        }else if(v.id == R.id.btn_cancel){
            val telaChoice = Intent(this, ProfileChoiceActivity::class.java)
            startActivity(telaChoice)
        }
    }

}