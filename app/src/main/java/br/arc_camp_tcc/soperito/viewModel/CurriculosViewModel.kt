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

class CurriculosViewModel(application: Application) : AndroidViewModel(application) {

    private val peritoListRepository = ListPeritosRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    // Firebase
    private lateinit var bd: FirebaseFirestore

    // pega informaçoes
    private var lerDados = LerDados(application)

    private val _loadCurriculoSuccess = MutableLiveData<ListPeritoModel>()
    val loadCurriculoSuccess: LiveData<ListPeritoModel> = _loadCurriculoSuccess

    private val _loadCurriculoErr = MutableLiveData<ValidationModel>()
    val loadCurriculoErr: LiveData<ValidationModel> = _loadCurriculoErr

    private val _saveCurriculo = MutableLiveData<ValidationModel>()
    val saveCurriculo: LiveData<ValidationModel> = _saveCurriculo

    // --------------   CONTRATAR -----------------

    private val _contratarPerito = MutableLiveData<ValidationModel>()
    val contratarPerito: LiveData<ValidationModel> = _contratarPerito


    // --------------   FIREBASE -----------------


    fun saveCurriculoFireB(curriculo: ListPeritoModel) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Curriculos").document("curriculo")

        val ident = (1..10000).random()

        curriculo.codCurriculo = ident
        curriculo.codPerito = lerDados.getContPerito.toInt()
        curriculo.nome = lerDados.getPeritoNome.toString()

        var curr = hashMapOf<String, Any>(
            "codCurriculo" to curriculo.codCurriculo.toInt(),
            "codPerito" to curriculo.codPerito.toInt(),
            "statusCand" to curriculo.statusCand.toInt(),
            "empregador" to curriculo.empregador.toInt(),
            "nome" to curriculo.nome.toString(),
            "servico" to curriculo.servico.toString(),
            "temp" to curriculo.temp.toString(),
            "obs" to curriculo.obs.toString(),
            "valor" to curriculo.valor.toString(),
            "localizacao" to lerDados.getUserLocal.toString(),
            "dataCurriculo" to curriculo.dataCurriculo.toString()
        )
        reference.set(curr).addOnSuccessListener {

            val referecePerito = bd!!.collection("Peritos").whereEqualTo("codPerito", curriculo.codPerito)

            referecePerito.get().addOnSuccessListener { valor2 ->
                if (valor2 != null) {

                    var perit = hashMapOf<String, Any>(
                        "codCurriculo" to curriculo.codCurriculo.toInt()
                    )

                    bd!!.collection("Peritos").document("perito").update(perit)
                        .addOnSuccessListener {
                            _saveCurriculo.value = ValidationModel()
                        }.addOnFailureListener { erro ->
                            _saveCurriculo.value =
                                ValidationModel("ERRO ao atualziar  perito: ${erro} !!")
                        }

                } else {
                    _saveCurriculo.value = ValidationModel("ERRO arquivo vazio ou inexistente")
                }
            }.addOnFailureListener { erros ->
                _saveCurriculo.value = ValidationModel("ERRO ao atualziar  perito: ${erros} !!")
            }
        }.addOnFailureListener { erro ->
            _saveCurriculo.value = ValidationModel("ERRO ao salvar curriculo: ${erro} !!")
        }

    }

    fun loadCurriucloFireB(codCurriculo: Int) {
        bd = FirebaseFirestore.getInstance()

        //val lstCurriculo : MutableList <ListPeritoModel> = ArrayList<ListPeritoModel>();

        val reference = bd!!.collection("Curriculos")
        val query = reference.whereEqualTo("codCurriculo",codCurriculo)

        query.get().addOnSuccessListener { value ->
            if (value != null) {
                for (documents in value) {
                        val curri = documents.toObject(ListPeritoModel::class.java)
                       // lstCurriculo.add(curri)

                    _loadCurriculoSuccess.value = curri
                }

            } else {
                _loadCurriculoErr.value =
                    ValidationModel("Arquivo Curriculos vazio ou inexistente !!")
            }
        }.addOnFailureListener {
            _loadCurriculoErr.value =
                ValidationModel("ERRO ao procurar Curriculos  !!")
        }
    }


    fun contratanteFireB(codCurriculo: Int, codPerito: Int) {

        bd = FirebaseFirestore.getInstance()

        val reference = bd!!.collection("Contratados")

        val ident = (1..1000).random()

        val cand = hashMapOf<String, Any>(
            "codContrato" to ident.toInt(),
            "codEmp" to lerDados.getContEmp.toInt(),
            "codCurriculo" to codCurriculo.toInt(),
            "codPerito" to codPerito,
            "statusContratados" to 1
        )

        reference.document("contratado").set(cand).addOnSuccessListener {

            val refereceEmpregador = bd!!.collection("Curriculos").whereEqualTo("codPerito", codPerito)

            refereceEmpregador.get().addOnSuccessListener { valor2 ->
                if (valor2 != null) {

                    var curriAtt = hashMapOf<String, Any>(
                        "empregador" to lerDados.getCodEmp.toInt(),
                        "statusCand" to 1
                    )

                    bd!!.collection("Curriculos").document("curriculo").update(curriAtt)
                        .addOnSuccessListener {
                            _contratarPerito.value = ValidationModel()
                        }.addOnFailureListener { erro ->
                            _contratarPerito.value = ValidationModel("ERRO ao atualziar  perito: ${erro} !!")
                        }

                } else {
                    _contratarPerito.value = ValidationModel("ERRO arquivo vazio ou inexistente")
                }
            }.addOnFailureListener { erros ->
                _contratarPerito.value = ValidationModel("ERRO ao atualziar  perito: ${erros} !!")
            }

        }.addOnFailureListener { erro ->
            _contratarPerito.value = ValidationModel("ERRO ao salvar contratados: ${erro} !!")
        }

    }

}