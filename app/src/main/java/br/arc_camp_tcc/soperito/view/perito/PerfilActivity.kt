package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFloatEdit.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_float_edit){
            val edit_perfil = Intent(this, EditPerfilActivity::class.java)
            startActivity(edit_perfil)
        }else if(v.id == R.id.btn_ok){
            Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_LONG).show()
            val btn_ok = Intent(this, MenuPeritoActivity::class.java)
            startActivity(btn_ok)
        }
    }
}