package br.arc_camp_tcc.soperito.progressBar

import android.app.Activity
import android.app.AlertDialog
import androidx.fragment.app.DialogFragment
import br.arc_camp_tcc.soperito.R

class DialogProgress(val mActivity:Activity):DialogFragment() {

    private lateinit var isdialog: AlertDialog

    fun startLoading(){

        val infalter = mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.dialog_progress,null)

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