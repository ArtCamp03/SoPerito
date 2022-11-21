package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.ListPeritosRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class ListPeritoViewModel(application: Application): AndroidViewModel(application) {

    private val peritoListRepository = ListPeritosRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    // Firebase
    private lateinit var bd : FirebaseFirestore

    // pega informaçoes
    private var curriculos = ListPeritoModel()
    private var lerDados = LerDados(application)

   // private val getCodEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.COD_EMPREGADOR).toInt()
    // private val getContPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_PERITO).toInt()
   //  private val getNomePerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)

    private val _listPerito = MutableLiveData<List<ListPeritoModel>>()
    val listPerito: LiveData<List<ListPeritoModel>> = _listPerito

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure

    private val _loadCurriculoErr = MutableLiveData<ValidationModel>()
    val loadCurriculoErr: LiveData<ValidationModel> = _loadCurriculoErr

    private val _loadCurriculoSuccess = MutableLiveData<ListPeritoModel>()
    val loadCurriculoSuccess: LiveData<ListPeritoModel> = _loadCurriculoSuccess

    private val _saveCurriculo = MutableLiveData<ValidationModel>()
    val saveCurriculo: LiveData<ValidationModel> = _saveCurriculo

    private val _contratarPerito = MutableLiveData<ValidationModel>()
    val contratarPerito: LiveData<ValidationModel> = _contratarPerito

    /*

    fun listPerito() {
        peritoListRepository.listPeritos( object : APIListener<List<ListPeritoModel>> {
            override fun onSuccess(result: List<ListPeritoModel>) {
                _listPerito.value = result
            }
            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })

    }

    fun buscPerito(busc: String) {
        peritoListRepository.buscPerito(busc, object : APIListener<List<ListPeritoModel>> {
            override fun onSuccess(result: List<ListPeritoModel>) {

                _listPerito.value = result
            }
            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })
    }

    fun peritoCandidato() {
        peritoListRepository.peritoCandidato(getCodEmp, object : APIListener<List<ListPeritoModel>> {
            override fun onSuccess(result: List<ListPeritoModel>) {
                _listPerito.value = result
            }
            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })
    }

    //      CURRICULOS

    fun saveCurriculo(curriculo: ListPeritoModel) {
        peritoListRepository.saveCurriculo(getContPerito, getNomePerito, curriculo, object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _saveCurriculo.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _saveCurriculo.value = ValidationModel(message)
                }

            })
    }

    fun loadCurriuclo(codCurriculo: Int) {
        peritoListRepository.loadCurriculo(codCurriculo, object : APIListener<ListPeritoModel> {
            override fun onSuccess(result: ListPeritoModel) {

                securityPreferences.store(
                    DataBaseConstants.BUNDLE.COD_PERITO,
                    result.codPerito.toString()
                )

                _loadCurriculoSuccess.value = result
            }

            override fun onFailure(message: String) {
                _loadCurriculoErr.value = ValidationModel(message)
            }
        })
    }

    fun contratante(codCurriculo:Int, codPerito:Int){
        peritoListRepository.contratante(codCurriculo,getCodEmp,codPerito, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _contratarPerito.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _contratarPerito.value = ValidationModel(message)
            }
        })
    }


     */
    // ----------------     FIREBASE       --------------------

    fun listPeritoFireB() {

        val listCurriculos: MutableList<ListPeritoModel> = ArrayList<ListPeritoModel>();

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Curriculos")

        reference.get().addOnSuccessListener { value ->
            if (value != null) {
                listCurriculos.clear()
                for (documents in value) {
                    val pert = documents.toObject(ListPeritoModel::class.java)
                    listCurriculos.add(pert)
                }
                    _listPerito.value = listCurriculos
            }else {
                _listFailure.value = "Lista vazio ou inexistente !!"
            }
        }.addOnFailureListener { err ->
            _listFailure.value = "Lista perito vazio ou inexistente !!"
        }

    }


    fun buscPeritoFireB(busc: String) {

        val listCurri : MutableList <ListPeritoModel> = ArrayList<ListPeritoModel>();

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Curriculos")
        val query = reference.whereEqualTo("servico", busc)

        query.addSnapshotListener { value, error ->
            if (value != null) {
                listCurri.clear()
                for (documents in value) {
                    val vagss = documents.toObject(ListPeritoModel::class.java)
                    listCurri.add(vagss)
                }
                _listPerito.value = listCurri
            } else if(error != null) {
                _listFailure.value = "Lista vazio ou inexistente !!"
            }
        }
    }

}