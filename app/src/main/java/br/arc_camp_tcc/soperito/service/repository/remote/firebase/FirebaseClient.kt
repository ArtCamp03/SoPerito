package br.arc_camp_tcc.soperito.service.repository.remote.firebase

import android.content.Context
import br.arc_camp_tcc.soperito.service.repository.BaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseClient(context: Context): BaseRepository(context) {

    // declara e inicializa variavel de autenticaçao
    private var auth : FirebaseAuth = Firebase.auth
/*
    fun createUsuario(email:String, password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    return task.isSuccessful
                } else {
                    return task.isSuccessful
                }
            }
    }


    private fun loginUsuario(email: String, password: String, auth:FirebaseAuth) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Authentication success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Authentication failed: ${task.exception.toString()} ", Toast.LENGTH_SHORT).show()
                    Log.d("loginTeste", task.exception.toString())
                }
            }
    }

*/

}
