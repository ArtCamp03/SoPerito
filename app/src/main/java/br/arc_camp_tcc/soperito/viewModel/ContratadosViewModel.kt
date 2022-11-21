package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class ContratadosViewModel(application: Application) : AndroidViewModel(application) {

    // Firebase
    private lateinit var bd: FirebaseFirestore

    // pega informaçoes
    private var lerDados = LerDados(application)

    private val _listEmpregador = MutableLiveData<List<ListEmpregadorModel>>()
    val listEmpregador: LiveData<List<ListEmpregadorModel>> = _listEmpregador

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure


    //  --------------  FIREBASE   --------------------------

    fun contatoServicoFireB() {

        val listContratdos: MutableList<ListEmpregadorModel> = ArrayList<ListEmpregadorModel>();

        bd = FirebaseFirestore.getInstance()

        val reference =
            bd!!.collection("Vagas").whereEqualTo("contratado", lerDados.getPeritoCod.toInt())

        reference.addSnapshotListener { value, error ->
            if (value != null) {
                listContratdos.clear()
                for (documents in value) {
                    val contato = documents.toObject(ListEmpregadorModel::class.java)
                    listContratdos.add(contato)
                }
                _listEmpregador.value = listContratdos
            } else if(error != null) {
                _listFailure.value = "Lista vazio ou inexistente !!"
            }
        }

    }
}