package com.br.jafapps.bdfirestore.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class Util {

    companion object aquiVaiMetodosEstaticos {

        fun permissao(activity: Activity?, requestCode: Int, permissoes: Array<String>): Boolean {
            val list: MutableList<String> = ArrayList()
            for (permissao in permissoes) {
                val ok = ContextCompat.checkSelfPermission(activity!!, permissao) == PackageManager.PERMISSION_GRANTED
                if (!ok) {
                    list.add(permissao)
                }
            }

            if (list.isEmpty()) {
                return true
            }

            //solicita a permissao
            ActivityCompat.requestPermissions(activity!!, list.toTypedArray(), requestCode)
            return false
        }

        fun statusInternet(context: Context): Boolean {
            val conexao =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (conexao != null) {
                // PARA DISPOSTIVOS NOVOS
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    val recursosRede = conexao.getNetworkCapabilities(conexao.activeNetwork)

                    if (recursosRede != null) { //VERIFICAMOS SE RECUPERAMOS ALGO
                        if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                            //VERIFICAMOS SE DISPOSITIVO TEM 3G
                            return true
                        } else if (recursosRede.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                            //VERIFICAMOS SE DISPOSITIVO TEM WIFFI
                            return true
                        }

                        //NÃO POSSUI UMA CONEXAO DE REDE VÁLIDA
                        return false
                    }
                } else { //COMECO DO ELSE

                    // PARA DISPOSTIVOS ANTIGOS  (PRECAUÇÃO)
                    val informacao = conexao.activeNetworkInfo

                    return informacao != null && informacao.isConnected
                } //FIM DO ELSE
            }
            return false
        }
    }
}