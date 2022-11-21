package br.arc_camp_tcc.soperito.util.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences

class LerDados(application: Application) : AndroidViewModel(application) {
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    // verifica se usuario ja possui conta Perito

    // ------------ USUARIO-------------
    var getContUser = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_USER)
    var getContPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_PERITO)
    var getUserPerito = securityPreferences.get(DataBaseConstants.USER.COLUMNS.USER_PERITO)
    var getContEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.COD_EMP).toInt()
    var getUserEmp = securityPreferences.get(DataBaseConstants.USER.COLUMNS.USER_EMP)


    //--------------    EMPREGADOR  ---------------------
    var getCodEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.COD_EMPREGADOR).toInt()
    var getNomeEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.NOME)
    var getTelefoneEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.TELEFONE)
    var getEmailEmp = securityPreferences.get(DataBaseConstants.EMPREGADOR.COLUMNS.EMAIL)

    //--------------    PERITO  ---------------------
    var getPeritoCod = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_PERITO)
    var getPeritoNome = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.NOME)
    var getPeritoCodUser = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_USER)
    var getPeritoCodCurriculo = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.COD_CURRICULO)
    var getPeritoCUserPerito = securityPreferences.get(DataBaseConstants.PERITO.COLUMNS.USER_PERITO)

    //--------------    CANDIDATOS  ---------------------

    var getCandCod = securityPreferences.get(DataBaseConstants.CANDIDATOS.COLUMNS.COD_PERITO)
    var getCandCodEmp = securityPreferences.get(DataBaseConstants.CANDIDATOS.COLUMNS.COD_EMP)
    var getCandCodVaga = securityPreferences.get(DataBaseConstants.CANDIDATOS.COLUMNS.COD_VAGA)
    var getCandCodPerito = securityPreferences.get(DataBaseConstants.CANDIDATOS.COLUMNS.COD_PERITO)
    var getCandStatus = securityPreferences.get(DataBaseConstants.CANDIDATOS.COLUMNS.STATUS_CAND)

    //--------------    CURRICULO  ---------------------
    var getUserLocal = securityPreferences.get(DataBaseConstants.USER.COLUMNS.CIDADE) + " - " + securityPreferences.get(DataBaseConstants.USER.COLUMNS.ESTADO)
}