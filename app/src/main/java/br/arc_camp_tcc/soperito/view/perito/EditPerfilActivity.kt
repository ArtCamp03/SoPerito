package br.arc_camp_tcc.soperito.view.perito

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityEditPerfilPeritoBinding
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.viewModel.PerfilPeritoViewModel

class EditPerfilActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityEditPerfilPeritoBinding
    private lateinit var viewModel : PerfilPeritoViewModel

    private lateinit var controlaDados: Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPerfilPeritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PerfilPeritoViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)
        binding.btnAddEsp.setOnClickListener(this)
        binding.btnAddXp.setOnClickListener(this)

        // inicializa controle de dados
        controlaDados = Controlador()

        observe()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        val experiencia = binding.editAddExperiencia.text.toString()
        val especialidade = binding.editAddEspecialidade.text.toString()

        if(v.id == R.id.btn_add_xp){
            binding.textResulXp.setText(experiencia)
        }else if(v.id == R.id.btn_add_esp){
            binding.textResulEsp.setText(especialidade)
        }else if(v.id == R.id.btn_cancelar){
            startActivity(Intent(this, PerfilPeritoActivity::class.java))
        }else if(v.id == R.id.btn_salvar){
            if(experiencia != null && especialidade != null){
                addPerfil(experiencia, especialidade)
            }else{
                addPerfil("", "")
            }
        }
    }

    fun addPerfil(exp : String ,esp : String){
        if(controlaDados.firebase){
            viewModel.setPerfilFireB(exp,esp)
        }else if(controlaDados.api){
            viewModel.setPerfil(exp,esp)
        }
    }

    private fun observe(){
        viewModel.setPerfil.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext,R.string.save, Toast.LENGTH_LONG).show()
                finish()
                startActivity(Intent(applicationContext, PerfilPeritoActivity::class.java))
            }else{
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_LONG).show()
            }
        }
    }

}