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
import br.arc_camp_tcc.soperito.service.repository.EmrpegadorRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class EmpregadorViewModel(application: Application) : AndroidViewModel(application) {

    // private val repository = bancoRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val emrpegadorRepository = EmrpegadorRepository(application.applicationContext)


    // Firebase
    private lateinit var bd: FirebaseFirestore
    private var lerDados = LerDados(application)
    private var empregador = EmpregadorModel()
    private var usuario = UsuarioModel()


    private val userEmp = 1

    private val _criaEmp = MutableLiveData<ValidationModel>()
    val criaEmp: LiveData<ValidationModel> = _criaEmp

    private val _loadEmp = MutableLiveData<ValidationModel>()
    val loadEmp: LiveData<ValidationModel> = _loadEmp

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _loadInfoSuccess = MutableLiveData<UsuarioModel>()
    val loadInfoSuccess: LiveData<UsuarioModel> = _loadInfoSuccess

    private val _loadInfoFail = MutableLiveData<String>()
    val loadInfoFail: LiveData<String> = _loadInfoFail

/*

    fun criaEmrpegador(){
        emrpegadorRepository.criaEmpregador(getContUser, userEmp,getNomeEmp,getEmailEmp, getTelefoneEmp, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _criaEmp.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _criaEmp.value = ValidationModel(message)
            }
        })
    }

    fun loadEmrpegador(){
        emrpegadorRepository.loadEmp(getContUser, getContEmp, object : APIListener<EmpregadorModel> {
            override fun onSuccess(result: EmpregadorModel) {
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.NOME, result.nome).toString()
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE, result.telefone).toString()
                securityPreferences.store(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL, result.email).toString()
                _loadEmp.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _loadEmp.value = ValidationModel(message)
            }
        })
    }



    fun loadInfoVaga() {
        emrpegadorRepository.loadInfoVaga(getCodEmp, object : APIListener<UsuarioModel> {
            override fun onSuccess(result: UsuarioModel) {
                _loadInfoSuccess.value = result
            }

            override fun onFailure(message: String) {
                _loadInfoFail.value = message
            }
        })
    }

 */
    // -----------------    FIREBASE   -------------------------------

    fun contaEmpregadorFireb() {
        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Empregadores")

        val ident = (1..100).random()

        empregador.codUser = lerDados.getContUser.toInt()
        empregador.codEmp = ident
        empregador.userEmp = userEmp
        empregador.nome = lerDados.getNomeEmp.toString()
        empregador.email = lerDados.getEmailEmp.toString()
        empregador.telefone = lerDados.getTelefoneEmp.toString()

        val emp = hashMapOf<String, Any>(
            "codUser" to empregador.codUser.toInt(),
            "codEmp" to empregador.codEmp.toInt(),
            "userEmp" to empregador.userEmp.toInt(),
            "codVaga" to empregador.codVaga.toInt(),
            "nomeContact" to empregador.nome,
            "emailContact" to empregador.email,
            "telefoneContact" to empregador.telefone
        )

        reference.document("empregador").set(emp).addOnSuccessListener {
            val query = bd!!.collection("Usuarios").whereEqualTo("cod_usuario", empregador.codUser)

            query.addSnapshotListener { documento, erro ->
                if (documento != null) {

                    val editUser = hashMapOf<String, Any>(
                        "user_emp" to empregador.userEmp,
                        "cod_emp" to empregador.codEmp
                    )

                    bd!!.collection("Usuarios").document("usuario").update(editUser)
                        .addOnSuccessListener { task ->
                            if (task != null) {
                                _criaEmp.value = ValidationModel()
                            }
                        }.addOnFailureListener { err ->
                        _criaEmp.value = ValidationModel("Erro ao atualizar usuario : ${err}!!")
                    }
                    _criaEmp.value = ValidationModel()
                } else if (erro != null) {
                    _criaEmp.value = ValidationModel("Erro ao encontrar usuario !!")
                }
                _criaEmp.value = ValidationModel()
            }
            _criaEmp.value = ValidationModel()
        }.addOnFailureListener { erro ->
            _criaEmp.value = ValidationModel("Erro ao criar Empregador: ${erro} !!")
        }

    }

    fun loadEmpregadorFireb() {

        bd = FirebaseFirestore.getInstance()

        val query = bd!!.collection("Empregadores").whereEqualTo("cod_usuario", usuario.cod_usuario)

        query.get().addOnSuccessListener { documento ->
            if (documento != null) {

                for (documents in documento) {
                    if (documents.id.equals("empregador")) {

                        val empr = documents.toObject(EmpModel::class.java)

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.COD_USER,
                            empr.codigUsuario.toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.COD_EMPREGADOR,
                            empr.codEmp.toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.USER_EMP,
                            empr.userEmp.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.COD_VAGA,
                            empr.codVaga.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.NOME,
                            empr.nomeContact.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL,
                            empr.emailContact.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE,
                            empr.telefoneContact.toString()
                        )

                        empregador.codUser = empr.codigUsuario!!
                        empregador.codEmp = empr.codEmp!!
                        empregador.userEmp = empr.userEmp!!
                        empregador.codVaga = empr.codVaga!!
                        empregador.nome = empr.nomeContact!!
                        empregador.email = empr.emailContact!!
                        empregador.telefone = empr.telefoneContact!!
                    }
                }
                _loadEmp.value = ValidationModel()
            } else {
                _loadEmp.value = ValidationModel("Arquivo vazio ou inexistente !!")
            }
        }.addOnFailureListener {
            _loadEmp.value = ValidationModel("Erro na comunicaçao com o servidor !!")
        }
    }

    fun loadInfoVagaFireB() {
        bd = FirebaseFirestore.getInstance()

        //val dadosPerito : MutableList <PeritoModel> = ArrayList<PeritoModel>();

        val reference = bd!!.collection("Usuarios")
        val query = reference.whereEqualTo("cod_emp", lerDados.getContEmp.toInt())

        query.get().addOnSuccessListener { documento ->
            if (documento != null) {
                //dadosPerito.clear()
                for (docuemntos in documento) {
                    if (docuemntos.id.equals("usuario")) {
                        val infoEmp = docuemntos.toObject(UsuarioModel::class.java)
                        //dadosPerito.add(infoPerito)
                        _loadInfoSuccess.value = infoEmp
                    } else {
                        _loadInfoFail.value = "Usuario nao encontrado !!"
                    }
                }
            } else {
                _loadInfoFail.value = "Empregador inexistente !!"
            }
        }.addOnFailureListener {
            _loadInfoFail.value = "Empregador nao encontrado !!"
        }
    }

}