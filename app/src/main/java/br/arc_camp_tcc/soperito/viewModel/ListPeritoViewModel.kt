package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.ListPeritosRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class ListPeritoViewModel(application: Application): AndroidViewModel(application) {

    private val peritoListRepository = ListPeritosRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val getCodEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.COD_EMPREGADOR).toInt()
    private val getContPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_PERITO).toInt()
    private val getNomePerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)

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

    fun saveCurriculo(curriculo:ListPeritoModel) {
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

}