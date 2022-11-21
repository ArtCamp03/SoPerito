package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.ListEmpregadoresRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.util.util.LerDados
import com.google.firebase.firestore.FirebaseFirestore

class ListEmpregadorViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val empregadorListRepository = ListEmpregadoresRepository(application.applicationContext)


    // Firebase
    private lateinit var bd: FirebaseFirestore

    // pega informaçoes
    private var lerDados = LerDados(application)
    private var vags : ListEmpregadorModel? = null
    /*
    private val getCodEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_EMP).toInt()
    private val codPerito = securityPreferences.get(DataBaseConstants.BUNDLE.COD_PERITO).toInt()
    private val getNomeEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)
     */

    private val _listEmpregador = MutableLiveData<List<ListEmpregadorModel>>()
    val listEmpregador: LiveData<List<ListEmpregadorModel>> = _listEmpregador

    private val _listFailure = MutableLiveData<String>()
    val listFailure: LiveData<String> = _listFailure

    private val _bscEmrpegadorSucess = MutableLiveData<List<ListEmpregadorModel>>()
    val bscEmrpegadorSucess: LiveData<List<ListEmpregadorModel>> = _bscEmrpegadorSucess

    private val _bscEmrpegadorFailure = MutableLiveData<String>()
    val bscEmrpegadorFailure: LiveData<String> = _bscEmrpegadorFailure

    private val _saveVaga = MutableLiveData<ValidationModel>()
    val saveVaga: LiveData<ValidationModel> = _saveVaga

    private val _addCandidato = MutableLiveData<ValidationModel>()
    val addCandidato: LiveData<ValidationModel> = _addCandidato

    private val _loadVagaSuccess = MutableLiveData<ListEmpregadorModel>()
    val loadVagaSuccess: LiveData<ListEmpregadorModel> = _loadVagaSuccess

    private val _loadVagaOnfailure = MutableLiveData<ValidationModel>()
    val loadVagaOnfailure: LiveData<ValidationModel> = _loadVagaOnfailure

    /*
    fun listEmpregador() {
        empregadorListRepository.listEmpregadores(object : APIListener<List<ListEmpregadorModel>> {
            override fun onSuccess(result: List<ListEmpregadorModel>) {
                _listEmpregador.value = result
            }

            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })
    }

    fun buscEmpregador(servico: String) {
        empregadorListRepository.buscEmpregadores(
            servico,
            object : APIListener<List<ListEmpregadorModel>> {
                override fun onSuccess(result: List<ListEmpregadorModel>) {
                    _bscEmrpegadorSucess.value = result
                }

                override fun onFailure(message: String) {
                    _bscEmrpegadorFailure.value = message
                }

            })
    }


     */
    //  --------------  VAGAS   --------------------------
/*
    fun saveVaga(vaga: ListEmpregadorModel) {
        empregadorListRepository.saveVaga(
            getCodEmp,
            getNomeEmp,
            vaga,
            object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _saveVaga.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _saveVaga.value = ValidationModel(message)
                }

            })
    }

    fun loadVaga(codVaga: Int) {
        empregadorListRepository.loadVaga(codVaga, object : APIListener<ListEmpregadorModel> {
            override fun onSuccess(result: ListEmpregadorModel) {

                securityPreferences.store(
                    DataBaseConstants.BUNDLE.COD_EMP,
                    result.codEmp.toString()
                )

                _loadVagaSuccess.value = result
            }

            override fun onFailure(message: String) {
                _loadVagaOnfailure.value = ValidationModel(message)
            }

        })
    }

    fun candidato(codVaga: Int, codEmp: Int) {
        empregadorListRepository.candidato(
            codVaga,
            codEmp,
            codPerito,
            object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _addCandidato.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _addCandidato.value = ValidationModel(message)
                }
            })
    }

    fun contatoServico() {
        empregadorListRepository.contatoServico(
            codPerito,
            object : APIListener<List<ListEmpregadorModel>> {
                override fun onSuccess(result: List<ListEmpregadorModel>) {
                    _listEmpregador.value = result
                }

                override fun onFailure(message: String) {
                    _listFailure.value = message
                }
            })
    }

 */
    //  --------------  FIREBASE   --------------------------

    fun saveVagaFireB(vaga: ListEmpregadorModel) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Vagas").document("vaga")

        val ident = (1..10000).random()

        vaga.codVaga = ident
        vaga.codEmp = lerDados.getCodEmp.toInt()
        vaga.nomeEmp = lerDados.getNomeEmp.toString()

        var vag = hashMapOf<String, Any>(
            "codVaga" to vaga.codVaga.toInt(),
            "codEmp" to vaga.codEmp.toInt(),
            "contratado" to vaga.contratado.toInt(),
            "nomeEmp" to vaga.nomeEmp.toString(),
            "servico" to vaga.servico.toString(),
            "tempExp" to vaga.tempExp.toString(),
            "dispPagar" to vaga.dispPagar.toString(),
            "descricao" to vaga.descricao.toString(),
            "vData" to vaga.vData.toString(),
            "local" to lerDados.getUserLocal.toString()
        )

        reference.set(vag).addOnSuccessListener {

            val referecePerito = bd!!.collection("Empregadores").whereEqualTo("codEmp", vaga.codEmp)

            referecePerito.get().addOnSuccessListener{ valor2 ->
                if (valor2 != null) {

                    var perit = hashMapOf<String, Any>(
                        "codVaga" to vaga.codVaga.toInt()
                    )

                    bd!!.collection("Empregadores").document("empregador").update(perit).addOnSuccessListener {
                        _saveVaga.value = ValidationModel()
                    }.addOnFailureListener { erro ->
                        _saveVaga.value = ValidationModel("ERRO ao atualziar  empregador: ${erro} !!")
                    }

                }else{
                    _saveVaga.value = ValidationModel("ERRO arquivo vazio ou inexistente")
                }
            }.addOnFailureListener {    erros ->
                _saveVaga.value = ValidationModel("ERRO ao encontrar  empregador: ${erros} !!")
            }


        }.addOnFailureListener { erro ->
            _saveVaga.value = ValidationModel("ERRO ao salvar vaga: ${erro} !!")
        }

    }

    fun listEmpregadorFireB() {

        val listVagass : MutableList <ListEmpregadorModel> = ArrayList<ListEmpregadorModel>();

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Vagas")

        reference.get().addOnSuccessListener { documento ->
            if (documento != null) {
                listVagass.clear()
                for (documents in documento) {
                        val vagss = documents.toObject(ListEmpregadorModel::class.java)
                    listVagass.add(vagss)
                }
                _listEmpregador.value = listVagass
            } else {
                _listFailure.value = "Lista vazio ou inexistente !!"
            }
        }.addOnFailureListener {
            _listFailure.value = "Erro na comunicaçao com o servidor !!"
        }
    }

    fun loadVagaFireB(codVaga: Int) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Vagas")
        val query = reference.whereEqualTo("codVaga",codVaga)

        query.get().addOnSuccessListener { documento ->
            if (documento != null) {
                for (documents in documento) {

                    val vagass = documents.toObject(ListEmpregadorModel::class.java)
                    _loadVagaSuccess.value = vagass

                }
            } else {
                _loadVagaOnfailure.value = ValidationModel("Lista vazio ou inexistente !!")
            }
        }.addOnFailureListener {
            _loadVagaOnfailure.value = ValidationModel("Erro na comunicaçao com o servidor !!")
        }

    }

    fun candidatoFireB(codVaga:Int, codEmp:Int){

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Candidatos")

        val ident = (2..1000).random()

        val cand = hashMapOf<String, Any>(
            "codCandidato" to ident.toInt(),
            "codEmp" to codEmp.toInt(),
            "codVaga" to codVaga.toInt(),
            "codPerito" to lerDados.getPeritoCod.toInt(),
            "statusCand" to 1
        )

        reference.document("candidato").set(cand).addOnSuccessListener {
            val refereceCAndiato = bd!!.collection("Vagas").whereEqualTo("codEmp", codEmp)

            refereceCAndiato.get().addOnSuccessListener { valor2 ->
                if (valor2 != null) {

                    var vagaAtt = hashMapOf<String, Any>(
                        "contratado" to lerDados.getContPerito.toInt()
                    )

                    bd!!.collection("Vagas").document("vaga").update(vagaAtt)
                        .addOnSuccessListener {
                            _addCandidato.value = ValidationModel()
                        }.addOnFailureListener { erro ->
                            _addCandidato.value = ValidationModel("ERRO ao atualziar  perito: ${erro} !!")
                        }

                } else {
                    _addCandidato.value = ValidationModel("ERRO arquivo vazio ou inexistente")
                }
            }.addOnFailureListener { erros ->
                _addCandidato.value = ValidationModel("ERRO ao atualziar  perito: ${erros} !!")
            }
        }.addOnFailureListener { erro ->
            _addCandidato.value = ValidationModel("ERRO ao salvar candidato: ${erro} !!")
        }
    }

       fun buscEmpregadorFireB(servico: String) {
           val listVagass : MutableList <ListEmpregadorModel> = ArrayList<ListEmpregadorModel>();

           bd = FirebaseFirestore.getInstance()

           val reference = bd!!.collection("Vagas")
           val query = reference.whereEqualTo("servico", servico)//.startAt("servico",servico)

           query.addSnapshotListener { value, error ->
               if (value != null) {
                   listVagass.clear()
                   for (documents in value) {
                       val vagss = documents.toObject(ListEmpregadorModel::class.java)
                       listVagass.add(vagss)
                   }
                   _bscEmrpegadorSucess.value = listVagass
               } else if(error != null) {
                   _bscEmrpegadorFailure.value = "Lista vazio ou inexistente !!"
               }
           }

       }


}
