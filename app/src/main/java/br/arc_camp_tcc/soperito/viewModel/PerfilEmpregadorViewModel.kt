package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.model.EmpregadorModel
import br.arc_camp_tcc.soperito.service.model.Firebase_model.EmpModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class PerfilEmpregadorViewModel(application: Application): AndroidViewModel(application) {

    // private val repository = bancoRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)
    //private val emrpegadorRepository = EmrpegadorRepository (application.applicationContext)

    private val _editPerfil = MutableLiveData<ValidationModel>()
    val editPerfil : LiveData<ValidationModel> = _editPerfil

    private val _loadPerfil = MutableLiveData<ValidationModel>()
    val loadPerfil : LiveData<ValidationModel> = _loadPerfil

    private val _nomeEmp = MutableLiveData<String>()
    val nomeEmp : LiveData<String> = _nomeEmp

    private val _telefone = MutableLiveData<String>()
    val telefone : LiveData<String> = _telefone

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    // Firebase
    private lateinit var bd: FirebaseFirestore
    private var lerDados = LerDados(application)
    private var empregador = EmpregadorModel()
    private var usuario = UsuarioModel()

    // -----------------    FIREBASE    -------------------

    fun editPerfilFireB(nome: String, telefone: String, email: String) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Empregadores")
        val query = reference.whereEqualTo("codUser", empregador.codUser)

        query.addSnapshotListener { documento, erro ->
            if (documento != null) {

                empregador.nome = nome
                empregador.email = email
                empregador.telefone = telefone

                val editEmp = hashMapOf<String, Any>(
                    "nomeContact" to empregador.nome,
                    "telefoneContact" to empregador.telefone,
                    "emailContact" to empregador.email
                )

                reference.document("empregador").update(editEmp).addOnSuccessListener { task ->
                    if (task != null) {
                        _editPerfil.value = ValidationModel()
                    }
                }.addOnFailureListener { err ->
                    _editPerfil.value = ValidationModel("Erro ao atualizar perfil : ${err}!!")
                }
                _editPerfil.value = ValidationModel()
            } else if (erro != null) {
                _editPerfil.value = ValidationModel("Erro ao encontrar perfil !!")
            }

        }
    }

    fun loadPerfilFireB() {
        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Empregadores")
        //val query = reference.whereEqualTo("cod_usuario", empregador.codUser)

        reference.get().addOnSuccessListener { documento ->
            if (documento != null) {

                for (docuemntos in documento) {

                    if (docuemntos.id.equals("empregador")) {

                        val userEmp = docuemntos.toObject(EmpModel::class.java)

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.NOME,
                            docuemntos.get("nomeContact").toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL,
                            docuemntos.get("emailContact").toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE,
                            docuemntos.get("telefoneContact").toString()
                        )

                        empregador.nome = docuemntos.get("nomeContact").toString()
                        empregador.email = docuemntos.get("emailContact").toString()
                        empregador.telefone = docuemntos.get("telefoneContact").toString()

                        _nomeEmp.value = lerDados.getNomeEmp.toString()
                        _telefone.value = lerDados.getTelefoneEmp.toString()
                        _email.value = lerDados.getEmailEmp.toString()

                        _loadPerfil.value = ValidationModel()
                    } else {
                        _loadPerfil.value = ValidationModel("Perito nao encontrado !!")
                    }
                }

                _loadPerfil.value = ValidationModel()
            }else {
                _loadPerfil.value = ValidationModel("Empregador inexistente !!")
            }
        }.addOnFailureListener {
            _loadPerfil.value = ValidationModel("Empregador nao encontrado !!")
        }
    }

    // -------------------  API -------------------------------

    /*
    fun loadPerfilEmp(){
        _nomeEmp.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.NOME)
        _telefone.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE)
        _email.value = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL)
    }

    fun editPerfil(nome:String, telefone:String, email:String){
        emrpegadorRepository.editPerfilEmp(lerDados.getContEmp.toInt(), nome, telefone,email, object :
            APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.NOME, nome)
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE,telefone)
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL,email)
                _editPerfil.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _editPerfil.value = ValidationModel(message)
            }

        })

    }

     */
}