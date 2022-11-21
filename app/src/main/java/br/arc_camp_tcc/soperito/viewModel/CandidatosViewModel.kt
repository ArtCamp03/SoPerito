package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class CandidatosViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    // Firebase
    private lateinit var bd: FirebaseFirestore

    // pega informaçoes
    private var lerDados = LerDados(application)
    private var listCand = ListPeritoModel()

    //  ------------------  LOAD CANDIADTOS  -------------------
    private val _listPerito = MutableLiveData<List<ListPeritoModel>>()
    val listPerito: MutableLiveData<List<ListPeritoModel>> = _listPerito

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure

    //  ------------------  LOAD CURRICULO  -------------------

    private val _contratarPerito = MutableLiveData<ValidationModel>()
    val contratarPerito: LiveData<ValidationModel> = _contratarPerito

    // ----------------     FIREBASE       --------------------

    fun peritoCandidatoFireB() {

        bd = FirebaseFirestore.getInstance()

        var listCandidatos: MutableList<ListPeritoModel> = ArrayList<ListPeritoModel>();

        val reference = bd!!.collection("Curriculos")

        val query = reference.whereEqualTo("empregador", lerDados.getCodEmp.toInt())

        query.addSnapshotListener { value, error ->
            if (value != null) {
                listCandidatos.clear()
                for (documents in value) {
                    val listCand = documents.toObject(ListPeritoModel::class.java)
                    listCandidatos.add(listCand)
                }
                _listPerito.value = listCandidatos
            } else if (error != null) {
                _listFailure.value = "ERRO curriculo nao encontrado !!"
            }
        }
    }

}