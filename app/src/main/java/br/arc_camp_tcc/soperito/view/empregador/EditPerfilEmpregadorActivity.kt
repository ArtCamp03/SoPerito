package br.arc_camp_tcc.soperito.view.empregador

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.arc_camp_tcc.soperito.R
import br.arc_camp_tcc.soperito.databinding.ActivityEditPerfilEmpregadorBinding
import br.arc_camp_tcc.soperito.view.Controlador
import br.arc_camp_tcc.soperito.viewModel.PerfilEmpregadorViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class EditPerfilEmpregadorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditPerfilEmpregadorBinding
    private lateinit var viewModel : PerfilEmpregadorViewModel

    private lateinit var controlaDados: Controlador

    // variaveis da imagem
    private lateinit var uri_imagem: Uri
    private lateinit var storage: FirebaseStorage
    private var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditPerfilEmpregadorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PerfilEmpregadorViewModel::class.java)

        binding.btnSalvar.setOnClickListener(this)
        binding.btnCancelar.setOnClickListener(this)

        // inicializa controle de dados
        controlaDados = Controlador()

        observe()

        storage = Firebase.storage

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            editPerfil()
        } else if (v.id == R.id.btn_cancelar) {
            startActivity(Intent(this, PerfilEmpregadorActivity::class.java))
            finish()
        } else if (v.id == R.id.img_user) {
           // obterImageCamera()
           // uploadImagem()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            /*
            if (requestCode == 11 && data != null) {
                uri_imagem = data.data!!
                binding.imgUser.setImageURI(uri_imagem)
            }
             */

            if (requestCode == 22 && uri_imagem != null) {
                binding.imgUser.setImageURI(uri_imagem)
            }
        }
    }

    private fun observe(){
        viewModel.editPerfil.observe(this){
            if(it.status()){
                Toast.makeText(applicationContext, R.string.valid_registre, Toast.LENGTH_LONG)
                startActivity(Intent(applicationContext, PerfilEmpregadorActivity::class.java))
                finish()
            }else{
                Toast.makeText(applicationContext,it.message(), Toast.LENGTH_LONG)
            }
        }
    }

    private fun editPerfil(){

        val nome = binding.editName.text.toString()
        val telefone = binding.editTelefone.text.toString()
        val email = binding.editEmail.text.toString()

        if(controlaDados.firebase){
            viewModel.editPerfilFireB(nome, telefone, email)
        }else if(controlaDados.api){
            //viewModel.editPerfil(nome, telefone, email)
        }

    }



    // --------------   FIREBASE    ----------------------

    /*
     private fun uploadImagem() {
         // tratamento da imagem
         Glide.with(baseContext).asBitmap().
         load(uri_imagem).apply(RequestOptions.overrideOf(1024, 768)).listener(object : RequestListener<Bitmap> {
             override fun onLoadFailed(
                 e: GlideException?,
                 model: Any?,
                 target: Target<Bitmap>?,
                 isFirstResource: Boolean
             ): Boolean {
                 Toast.makeText(applicationContext,"Falha ao diminuir da imagem:", Toast.LENGTH_LONG)
                 return false
             }

             override fun onResourceReady(
                 bitmap: Bitmap?,
                 model: Any?,
                 target: Target<Bitmap>?,
                 dataSource: DataSource?,
                 isFirstResource: Boolean
             ): Boolean {
                 val baos = ByteArrayOutputStream()
                 bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                 val data = baos.toByteArray()

                 // local para armazenamento da imagem
                 val uid = Firebase.auth.currentUser?.uid
                 val reference = storage.reference.child("arquivosUsuarios").child(uid.toString()).child("imagem"+ cont + ".jpg")
                 cont += 1

                 var uploadTask = reference.putBytes(data)
                 uploadTask.addOnSuccessListener {
                     Toast.makeText(applicationContext,"Sucesso ao realizar upload da imagem", Toast.LENGTH_LONG)
                 }.addOnFailureListener { error ->
                     Toast.makeText(applicationContext,"Falha ao realizar upload da imagem: ${error.message.toString()}", Toast.LENGTH_LONG)
                 }
                 return false
             }

         }).submit()
     }

     // opçao de camera
     private fun obterImageCamera() {

         val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
             //  VERSAO MAIS NOVA DO ANDROID
             val contentValues = ContentValues()
             contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
             //contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "NomeImagem")
             val resolver = contentResolver

             uri_imagem = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!

             intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
         } else {
             //  VERSAO MAIS ANTIGA DO ANDROID
             val autorizacao = "br.arc_camp.firebaseactivity"
             val diretorio =
                 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
             val nomeImagem = diretorio.path +"/UsuarioImg"+ System.currentTimeMillis() + ".jpg"
             val file = File(nomeImagem)
             uri_imagem = FileProvider.getUriForFile(baseContext, autorizacao, file)
         }

         intent.putExtra(MediaStore.EXTRA_OUTPUT, uri_imagem)
         startActivityForResult(intent, 22)
     }

      */
}