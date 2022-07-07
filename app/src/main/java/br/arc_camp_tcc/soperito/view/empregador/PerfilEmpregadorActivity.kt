package br.arc_camp_tcc.soperito.view.empregador

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilEmpregadorBinding

class PerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPerfilEmpregadorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFloatEditEmp.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_float_edit_emp){
            val editPerfil = Intent(this, EditPerfilEmpregadorActivity::class.java)
            startActivity(editPerfil)
        }
    }
}