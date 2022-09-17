package br.arc_camp_tcc.soperito.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.service.model.PeritoModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.ListPeritosRepository
import br.arc_camp_tcc.soperito.service.repository.PeritoRepository
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class PeritoViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val peritoRepository = PeritoRepository(application.applicationContext)
    private val listPeritosRepository = ListPeritosRepository(application.applicationContext)


    private val _savePerito = MutableLiveData<ValidationModel>()
    val savePerito: LiveData<ValidationModel> = _savePerito

    private val _loadPerito = MutableLiveData<ValidationModel>()
    val loadPerito: LiveData<ValidationModel> = _loadPerito

    private val _loadCurriculoErr = MutableLiveData<ValidationModel>()
    val loadCurriculoErr: LiveData<ValidationModel> = _loadCurriculoErr

    private val _loadCurriculoSuccess = MutableLiveData<ListPeritoModel>()
    val loadCurriculoSuccess: LiveData<ListPeritoModel> = _loadCurriculoSuccess

    private val _saveCurriculo = MutableLiveData<ValidationModel>()
    val saveCurriculo: LiveData<ValidationModel> = _saveCurriculo

    // verifica se usuario ja possui conta Perito
    private val getContUser = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_USER).toInt()
    private val getContPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_PERITO).toInt()
    private val getNomePerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.NOME)
    private val userPerito = 1

    // quando criar perito setar userPerito em usuarios para 1

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


    // CURRIUCLO

    fun loadCurriuclo(codCurriculo: Int) {
        listPeritosRepository.loadCurriculo(codCurriculo, object : APIListener<ListPeritoModel> {
            override fun onSuccess(result: ListPeritoModel) {
                _loadCurriculoSuccess.value = result
            }

            override fun onFailure(message: String) {
                _loadCurriculoErr.value = ValidationModel(message)
            }
        })
    }

    fun saveCurriculo(service: String, temp: String, obs: String, valor: String, dataCurriculo: String) {
        peritoRepository.saveCurriculo(getContPerito, getNomePerito, service, temp, obs,
            valor, dataCurriculo, object : APIListener<Boolean> {
                override fun onSuccess(result: Boolean) {
                    _saveCurriculo.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _saveCurriculo.value = ValidationModel(message)
                }

            })
    }

    /*

        fun insert(prito: UsuarioModel){
            repository.insert(prito)
        }

        fun get(prito: UsuarioModel){
            repository.insert(prito)
        }

        fun delete(prito: UsuarioModel){
            repository.insert(prito)
        }

     */
}