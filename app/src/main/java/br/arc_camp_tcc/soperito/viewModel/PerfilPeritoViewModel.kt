package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.Firebase_model.PeritModel
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.PeritoRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class PerfilPeritoViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val peritoRepository = PeritoRepository(application.applicationContext)


    // Firebase
    private lateinit var bd: FirebaseFirestore
    private var lerDados = LerDados(application)
    private var perito = PeritoModel()

    private val codUser = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_USER).toInt()
    private val codPerito =
        securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_PERITO).toInt()

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    private val _esp = MutableLiveData<String>()
    val esp: LiveData<String> = _esp

    private val _exp = MutableLiveData<String>()
    val exp: LiveData<String> = _exp

    private val _setPerfil = MutableLiveData<ValidationModel>()
    val setPerfil: LiveData<ValidationModel> = _setPerfil

    private val _loadPerfil = MutableLiveData<ValidationModel>()
    val loadPerfil: LiveData<ValidationModel> = _loadPerfil

    fun setPerfil(exp: String, esp: String) {
        peritoRepository.addPerfil(codUser, codPerito, exp, esp, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _setPerfil.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _setPerfil.value = ValidationModel(message)
            }
        })
    }

    fun loadPerfil() {
        peritoRepository.loadPerfil(codUser, codPerito, object : APIListener<PeritoModel> {
            override fun onSuccess(result: PeritoModel) {


                securityPreferences.store(DataBaseConstants.PERITO.COLUMNS.ESP, result.espec)
                    .toString()
                securityPreferences.store(DataBaseConstants.PERITO.COLUMNS.EXP, result.exp)
                    .toString()

                _nome.value = result.nome
                _esp.value = result.espec
                _exp.value = result.exp

                _loadPerfil.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _loadPerfil.value = ValidationModel(message)
            }

        })

    }

    //  ------------------------------- FIREBASE        ------------

    fun setPerfilFireB(exp: String, esp: String) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Peritos")
        val query = reference.whereEqualTo("codPerito", perito.codPerito)

        query.addSnapshotListener { documento, erro ->
            if (documento != null) {

                val editPerit = hashMapOf<String, Any>(
                    "exp" to exp,
                    "espec" to esp
                )

                reference.document("perito").update(editPerit).addOnSuccessListener { task ->
                    if (task != null) {
                        _setPerfil.value = ValidationModel()
                    }
                }.addOnFailureListener { err ->
                    _setPerfil.value = ValidationModel("Erro ao atualizar perfil : ${err}!!")
                }
                _setPerfil.value = ValidationModel()
            } else if (erro != null) {
                _setPerfil.value = ValidationModel("Erro ao encontrar perfil !!")
            }

        }
    }

    fun loadPerfilFireB() {
        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Peritos")
        //val query = reference.whereEqualTo("codPerito", perito.codPerito)

        reference.get().addOnSuccessListener { documento ->
            if (documento != null) {

                for (docuemntos in documento) {

                    if (docuemntos.id.equals("perito")) {

                        val perit = docuemntos.toObject(PeritModel::class.java)

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.EXP,
                            docuemntos.get("exp").toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.ESP,
                            docuemntos.get("espec").toString()
                        )

                        perito.exp = perit!!.exp.toString()
                        perito.espec = perit!!.espec.toString()

                        _nome.value = lerDados.getNomeEmp.toString()
                        _esp.value = perito.espec.toString()
                        _exp.value = perito.exp.toString()

                        _loadPerfil.value = ValidationModel()
                    } else {
                        _loadPerfil.value = ValidationModel("Perito nao encontrado !!")
                    }
                }

                _loadPerfil.value = ValidationModel()
            }else {
                _loadPerfil.value = ValidationModel("Perito inexistente !!")
            }
        }.addOnFailureListener {
            _loadPerfil.value = ValidationModel("Perito nao encontrado !!")
        }
    }
}