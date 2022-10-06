package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.ListEmpregadoresRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class ListEmpregadorViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val empregadorListRepository = ListEmpregadoresRepository(application.applicationContext)

    private val getCodEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_EMP).toInt()
    private val codPerito = securityPreferences.get(DataBaseConstants.BUNDLE.COD_PERITO).toInt()
    private val getNomeEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)

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

    // VAGAS

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

    fun candidato(codVaga:Int, codEmp:Int){
        empregadorListRepository.candidato(codVaga,codEmp,codPerito, object : APIListener<Boolean> {
            override fun onSuccess(result: Boolean) {
                _addCandidato.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _addCandidato.value = ValidationModel(message)
            }
        })
    }

    fun contatoServico() {
        empregadorListRepository.contatoServico(codPerito, object : APIListener<List<ListEmpregadorModel>> {
            override fun onSuccess(result: List<ListEmpregadorModel>) {
                _listEmpregador.value = result
            }
            override fun onFailure(message: String) {
                _listFailure.value = message
            }
        })
    }


}