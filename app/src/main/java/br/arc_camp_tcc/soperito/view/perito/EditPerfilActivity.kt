package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityEditPerfilPeritoBinding

class EditPerfilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityEditPerfilPeritoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPerfilPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)
        binding.btnAddEsp.setOnClickListener(this)
        binding.btnAddXp.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_salvar){
            Toast.makeText(this, R.string.valid_registre, Toast.LENGTH_LONG).show()
            val salvar = Intent(this, MenuPeritoActivity::class.java)
            startActivity(salvar)
        }else if(v.id == R.id.btn_add_xp){
            addExperiencia()
        }else if(v.id == R.id.btn_add_esp){
            addEspecialdiade()
        }else if(v.id == R.id.btn_cancelar){
            val cancelar = Intent(this, PerfilActivity::class.java)
            startActivity(cancelar)
        }
    }

    fun addExperiencia(){
        var cont = 0
        while(cont < 3){
            var experiencia = binding.editAddExperiencia.text.toString()
            var listaXp : MutableList<String> = mutableListOf()
            listaXp.add(experiencia)
            binding.textResulXp.setText(listaXp.toString())
            cont = cont + 1
        }
    }

    fun addEspecialdiade(){
        var cont = 0
        while(cont < 3){
            var especialidade = binding.editAddEspecialidade.text.toString()
            var listaXp : MutableList<String> = mutableListOf()
            listaXp.add(especialidade)
            binding.textResulEsp.setText(listaXp.toString())
            cont = cont + 1
        }
    }


}