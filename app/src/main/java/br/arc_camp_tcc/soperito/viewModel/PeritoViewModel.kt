package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class PeritoViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    //private val peritoRepository = PeritoRepository(application.applicationContext)

    // Firebase
    private lateinit var bd: FirebaseFirestore

    private var lerDados = LerDados(application)
    private var perito = PeritoModel()
    private var usuario = UsuarioModel()

    private val _savePerito = MutableLiveData<ValidationModel>()
    val savePerito: LiveData<ValidationModel> = _savePerito

    private val _loadPerito = MutableLiveData<ValidationModel>()
    val loadPerito: LiveData<ValidationModel> = _loadPerito

    private val _losdInfoSuccess = MutableLiveData<UsuarioModel>()
    val losdInfoSuccess: LiveData<UsuarioModel> = _losdInfoSuccess

    private val _losdInfoFail = MutableLiveData<String>()
    val losdInfoFail: LiveData<String> = _losdInfoFail

    private val userPerito = 1

    // quando criar perito setar userPerito em usuarios para 1

    /*
    fun criaPerito() {
        peritoRepository.criaPérito(getContUser, userPerito, getNomePerito, object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _savePerito.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _savePerito.value = ValidationModel(message)
                }
            })
    }

    fun loadPerito() {
        peritoRepository.loadPerito(getContUser, getContPerito, object : APIListener<PeritoModel> {
            override fun onSuccess(result: PeritoModel) {

                // salva os dados do Load nas variaveis

                securityPreferences.store(
                    DataBaseConstants.PERITO.COLUMNS.COD_USER,
                    result.codigUsuario.toString()
                )
                securityPreferences.store(
                    DataBaseConstants.PERITO.COLUMNS.COD_PERITO,
                    result.codPerito.toString()
                )
                securityPreferences.store(
                    DataBaseConstants.PERITO.COLUMNS.USER_PERITO,
                    result.userPerito.toString()
                )

                securityPreferences.store(DataBaseConstants.PERITO.COLUMNS.NOME, result.nome)

                _loadPerito.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _loadPerito.value = ValidationModel(message)
            }
        })
    }

    fun loadInfoCandiato() {
        peritoRepository.loadInfoCandiato(getPerito, object : APIListener<UsuarioModel> {
            override fun onSuccess(result: UsuarioModel) {
                _losdInfoSuccess.value = result
            }

            override fun onFailure(message: String) {
                _losdInfoFail.value = message
            }
        })
    }


     */

    // -----------------    FIREBASE   -------------------------------


    fun contaPeritoFireb() {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Peritos")

        val ident = (1..100).random()

        perito.codigUsuario = lerDados.getContUser.toInt()
        perito.codPerito = ident
        perito.userPerito = userPerito
        perito.nome = lerDados.getPeritoNome.toString()

        val pt = hashMapOf<String, Any>(
            "codigUsuario" to perito.codigUsuario.toInt(),
            "codPerito" to perito.codPerito.toInt(),
            "userPerito" to perito.userPerito.toInt(),
            "codCurriculo" to perito.userPerito.toInt(),
            "nome" to perito.nome.toString(),
            "exp" to perito.exp.toString(),
            "espec" to perito.espec.toString(),
        )

        reference.document("perito").set(pt).addOnSuccessListener {
            val query = bd!!.collection("Usuarios").whereEqualTo("cod_usuario", perito.codigUsuario)

            query.addSnapshotListener{documento , erro ->
                if(documento != null){

                    val editUser = hashMapOf<String, Any>(
                        "user_perito" to perito.userPerito,
                        "cod_perito" to perito.codPerito
                    )

                    bd!!.collection("Usuarios").document("usuario").update(editUser).addOnSuccessListener { task ->
                        if (task != null){
                            _savePerito.value = ValidationModel()
                        }
                    }.addOnFailureListener{ err ->
                        _savePerito.value = ValidationModel("Erro ao atualizar usuario : ${err}!!")
                    }
                    _savePerito.value = ValidationModel()
                }else if(erro != null){
                    _savePerito.value = ValidationModel("Erro ao encontrar usuario !!")
                }
                _savePerito.value = ValidationModel()
            }
            _savePerito.value = ValidationModel()
        }.addOnFailureListener {    erro ->
            _savePerito.value = ValidationModel("Erro ao criar Perito: ${erro} !!")
        }

    }

    fun loadPeritoFireb() {

        bd = FirebaseFirestore.getInstance()

        val query = bd!!.collection("Peritos").whereEqualTo("cod_usuario", usuario.cod_usuario)

        query.get().addOnSuccessListener { documento ->
            if (documento != null) {

                for (documents in documento) {
                    if (documents.id.equals("perito")) {

                        val perit = documents.toObject(PeritoModel::class.java)

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.COD_USER,
                            perit.codigUsuario.toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.COD_PERITO,
                            perit.codPerito.toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.USER_PERITO,
                            perit.userPerito.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.COD_CURRICULO,
                            perit.codCurriculo.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.NOME,
                            perit.nome.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.EXP,
                            perit.exp.toString()
                        )

                        securityPreferences.store(
                            DataBaseConstants.PERITO.COLUMNS.ESP,
                            perit.espec.toString()
                        )

                        perito.codigUsuario = perit.codigUsuario.toInt()
                        perito.codPerito = perit.codPerito.toInt()
                        perito.userPerito = perit.userPerito.toInt()
                        perito.codCurriculo = perit.codCurriculo.toInt()
                        perito.nome = perit.nome.toString()
                        perito.exp = perit.exp.toString()
                        perito.espec = perit.espec.toString()

                    }
                }

                _loadPerito.value = ValidationModel()
            } else {
                _loadPerito.value = ValidationModel("Arquivo vazio ou inexistente !!")
            }
        }.addOnFailureListener {
            _loadPerito.value = ValidationModel("Erro na comunicaçao com o servidor !!")
        }
    }

    fun loadInfoCandidatoFireB() {
        bd = FirebaseFirestore.getInstance()

        //val dadosPerito : MutableList <PeritoModel> = ArrayList<PeritoModel>();

        val reference = bd!!.collection("Usuarios")
        val query = reference.whereEqualTo("cod_perito", lerDados.getPeritoCod.toInt())

        query.get().addOnSuccessListener { documento ->
            if (documento != null) {
                //dadosPerito.clear()
                for (docuemntos in documento) {
                    if (docuemntos.id.equals("usuario")) {
                        val infoPerito = docuemntos.toObject(UsuarioModel::class.java)
                        //dadosPerito.add(infoPerito)
                        _losdInfoSuccess.value = infoPerito
                    } else {
                        _losdInfoFail.value = "Usuario nao encontrado !!"
                    }
                }
            }else {
                _losdInfoFail.value = "Empregador inexistente !!"
            }
        }.addOnFailureListener {
            _losdInfoFail.value = "Empregador nao encontrado !!"
        }
    }
}