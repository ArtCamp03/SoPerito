package br.arc_camp_tcc.soperito.view.usuario

import ProfileChoiceViewModel
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityProfileChoiceBinding
import br.arc_camp_tcc.soperito.progressBar.DialogProgress
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.view.empregador.MenuEmpregadorActivity
import br.arc_camp_tcc.soperito.view.perito.MenuPeritoActivity
import com.br.jafapps.bdfirestore.util.Util
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileChoiceActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityProfileChoiceBinding
    private lateinit var viewModel: ProfileChoiceViewModel

    // gerencia entre API e firebase
    private lateinit var controlaDados: Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ProfileChoiceViewModel::class.java)

        binding.buttonPerito.setOnClickListener(this)
        binding.buttonEmpregador.setOnClickListener(this)

        observe()

        // inicializa controle de dados
        controlaDados = Controlador()

        permission()
        listenAuthentic()
        loadUser()

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_perito) {
            contPerito()
        } else if (v.id == R.id.button_empregador) {
            contEmpregador()
        }
    }

    private fun observe() {
        val dialogProgress = DialogProgress(this)
        dialogProgress.startLoading()

        viewModel.loadUsuario.observe(this) {
            if (it.status()) {
                dialogProgress.isDismiss()
                //Toast.makeText(applicationContext, R.string.load_dados, Toast.LENGTH_LONG).show()
            } else {
                dialogProgress.isDismiss()
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.cntPerito.observe(this) {
            if (it) {
                dialogProgress.isDismiss()
                startActivity(Intent(applicationContext, MenuPeritoActivity::class.java))
                finish()
            } else {
                dialogProgress.isDismiss()
                startActivity(Intent(applicationContext, ContaPeritoActivity::class.java))
                finish()
            }
        }

        viewModel.cntEmpregador.observe(this) {
            if (it) {
                dialogProgress.isDismiss()
                startActivity(Intent(applicationContext, MenuEmpregadorActivity::class.java))
                finish()
            } else {
                dialogProgress.isDismiss()
                startActivity(Intent(applicationContext, ContaEmpregadorActivity::class.java))
                finish()
            }
        }
    }

    private fun loadUser() {
        if (controlaDados.firebase) {
            viewModel.loadDataUserFirebase()
        } else if(controlaDados.api){
            viewModel.loadDataUserAPI()
        }

    }

    private fun contPerito() {
        viewModel.verifyContaPerito()
    }

    private fun contEmpregador() {
        viewModel.verifyContaEmpregador()
    }

    // sempre escutara a respsota do Firebase
    private fun listenAuthentic() {
        Firebase.auth.addAuthStateListener { authentication ->
            if (authentication.currentUser != null) {
                Toast.makeText(applicationContext, " Usuarios logado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Usuarios deslogado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun permission() {
        val permissoes = arrayOf<String>(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        Util.permissao(this, 100, permissoes)
    }

}