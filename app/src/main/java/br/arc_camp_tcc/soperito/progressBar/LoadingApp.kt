package br.arc_camp_tcc.soperito.progressBar

import android.app.Activity
import android.app.AlertDialog
import br.arc_camp_tcc.soperito.R

class LoadingApp (val mActivity: Activity) {
    private lateinit var isdialog: AlertDialog

    fun startLoading(){

        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading_item,null)

        val builder = AlertDialog.Builder(mActivity)

        builder.setView(dialogView)
        builder.setCancelable(false)

        isdialog = builder.create()
        isdialog.show()

    }

    fun isDismiss(){
        isdialog.dismiss()
    }
}