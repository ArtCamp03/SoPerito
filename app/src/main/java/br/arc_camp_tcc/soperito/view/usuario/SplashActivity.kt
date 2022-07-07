package br.arc_camp_tcc.soperito.view.usuario

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.arc_camp_tcc.soperito.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mudaParaLogin()
    }

    fun mudaParaLogin(){
        val intent = Intent(this, IntroActivity::class.java)

        Handler().postDelayed({
            intent.mudar()
        }, 5000)

    }

    fun Intent.mudar(){
        startActivity(this)
        finish()
    }
}