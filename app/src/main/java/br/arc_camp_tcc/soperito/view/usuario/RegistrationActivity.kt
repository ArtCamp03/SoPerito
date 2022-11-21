package br.arc_camp_tcc.soperito.view.usuario

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityRegistrationBinding
import br.arc_camp_tcc.soperito.progressBar.DialogProgress
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.viewModel.RegistryViewModel
import com.br.jafapps.bdfirestore.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var mViewModel: RegistryViewModel
    private lateinit var auth : FirebaseAuth

    // Firebase
    private lateinit var bd : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewModel esta atrelada a ativitity
        mViewModel = ViewModelProvider(this).get(RegistryViewModel::class.java)

        binding.buttonConfirm.setOnClickListener(this)
        binding.buttonCancel.setOnClickListener(this)

        observe()

        // inicializa variavel de autetificaçao
        auth = Firebase.auth
        bd = FirebaseFirestore.getInstance()

        Manifest.permission()

        supportActionBar?.hide()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(applicationContext, "Aceite as permissoes para o app funcionar corretamente !!", Toast.LENGTH_SHORT).show()
                finish()
                break
            }
        }
    }

    override fun onClick(v: View) {
        val dialogProgress = DialogProgress(this)
        dialogProgress.startLoading()
        if (v.id == R.id.button_confirm) {
            handleSave()
            dialogProgress.isDismiss()
            finish()
        }else if (v.id == R.id.button_cancel) {
            dialogProgress.isDismiss()
            Toast.makeText(this, R.string.cadastro_no_complete, Toast.LENGTH_LONG).show()
            val cancel = Intent(this, IntroActivity::class.java)
            startActivity(cancel)
            finish()
        }
    }

    // observa variavel
    private fun observe(){
        val dialogProgress = DialogProgress(this)
        dialogProgress.startLoading()

        mViewModel.registryUser.observe(this){
            if(it.status()){
                dialogProgress.isDismiss()
                Toast.makeText(this,R.string.valid_registre, Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }else{
                dialogProgress.isDismiss()
                Toast.makeText(applicationContext, it.message(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSave() {
        val usuario = UsuarioModel().apply {
            this.senha = binding.editPassword.text.toString()
            this.email = binding.editEmail.text.toString()
            this.cpf = binding.editCpf.text.toString()
            this.nome = binding.editName.text.toString()
            this.telefone = binding.editTelephone.text.toString()
            this.cidade = binding.editCidade.text.toString().uppercase()
            this.estado = binding.editEstado.text.toString().uppercase()
        }

        val senhaConfirm = binding.editPasswordConfirm.text.toString()

        if(!usuario.nome.trim().isEmpty() && !usuario.email.trim().isEmpty() && !usuario.senha.trim().isEmpty()){
            if(senhaConfirm.equals(usuario.senha)){
                if(Util.statusInternet(baseContext)){
                    salvarDados(usuario)
                    //mViewModel.registre(usuario)
                }
            }else{
                Toast.makeText(this, R.string.senha_incompativel, Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun salvarDados(usuario : UsuarioModel){

        //val uid = Firebase.auth?.currentUser?.uid
        val ident = (1..100).random() // System.currentTimeMillis()

        val reference = bd!!.collection("Usuarios").
                document("usuario")

        var user = hashMapOf<String, Any>(
            "cidade" to usuario.cidade.toString(),
            "estado" to usuario.estado.toString(),
            "cod_emp" to usuario.cod_emp.toInt(),
            "cod_perito" to usuario.cod_perito.toInt(),
            "cod_usuario" to ident,
            "cpf" to usuario.cpf.toString(),
            "email" to usuario.email.toString(),
            "senha" to usuario.senha.toString(),
            "telefone" to usuario.telefone.toString(),
            "user_emp" to usuario.user_emp.toInt(),
            "user_perito" to usuario.user_perito.toInt(),
            "nome" to usuario.nome.toString()
        )

        reference.set(user).addOnSuccessListener {
            Toast.makeText(applicationContext,"Sucesso ao gravar dados", Toast.LENGTH_LONG).show()
            createUsuario(usuario.email, usuario.senha)
        }.addOnFailureListener {    erro ->
            Toast.makeText(applicationContext, "ERRO ao gravar dados: ${erro} !", Toast.LENGTH_LONG).show()
            Log.d("testeusuario", "Erro - key : ${erro}")
        }

    }

    fun createUsuario(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Firebase.auth.signOut()
                    Toast.makeText(applicationContext, "sucesso ao se cadastrar !", Toast.LENGTH_LONG).show()
                    // desloga usuario pelo Firebase
                } else {
                    Toast.makeText(applicationContext, "ERRO ao cadastrar !", Toast.LENGTH_LONG).show()
                }
            }
    }

}