import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.arc_camp_tcc.soperito.service.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.listeners.APIListener
import br.arc_camp_tcc.soperito.service.model.Firebase_model.UserModel
import br.arc_camp_tcc.soperito.service.model.UsuarioModel
import br.arc_camp_tcc.soperito.service.model.ValidationModel
import br.arc_camp_tcc.soperito.service.repository.SecurityPreferences
import br.arc_camp_tcc.soperito.service.repository.UsuarioRepository
import com.google.firebase.firestore.FirebaseFirestore

class ProfileChoiceViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val usuarioRepository = UsuarioRepository(application.applicationContext)

    // Firebase
    private lateinit var bd: FirebaseFirestore

    private var usuario = UsuarioModel()

    private val _loadUsuario = MutableLiveData<ValidationModel>()
    val loadUsuario: LiveData<ValidationModel> = _loadUsuario

    private val _cntPerito = MutableLiveData<Boolean>()
    val cntPerito: LiveData<Boolean> = _cntPerito

    private val _cntEmpregador = MutableLiveData<Boolean>()
    val cntEmpregador: LiveData<Boolean> = _cntEmpregador

    fun loadDataUserAPI() {
        usuarioRepository.loadUser(usuario.cod_usuario,
            object : APIListener<UsuarioModel> {
                override fun onSuccess(result: UsuarioModel) {

                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.COD_USER,
                        result.cod_usuario.toString()
                    )
                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.USER_PERITO,
                        result.user_perito.toString()
                    )
                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.USER_EMP,
                        result.user_emp.toString()
                    )
                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.COD_PERITO,
                        result.cod_perito.toString()
                    )
                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.COD_EMP,
                        result.cod_emp.toString()
                    )
                    securityPreferences.store(DataBaseConstants.USER.COLUMNS.NOME, result.nome)
                    securityPreferences.store(DataBaseConstants.USER.COLUMNS.CPF, result.cpf)
                    securityPreferences.store(
                        DataBaseConstants.USER.COLUMNS.TELEFONE,
                        result.telefone
                    )
                    securityPreferences.store(DataBaseConstants.USER.COLUMNS.CIDADE, result.cidade)
                    securityPreferences.store(DataBaseConstants.USER.COLUMNS.ESTADO, result.estado)
                    securityPreferences.store(DataBaseConstants.USER.COLUMNS.EMAIL, result.email)

                    _loadUsuario.value = ValidationModel()
                }

                override fun onFailure(message: String) {
                    _loadUsuario.value = ValidationModel(message)
                }

            })
    }

    fun verifyContaPerito() {

        var contaPerito: Boolean = false

        if (usuario.cod_usuario != 0 && usuario.user_perito == 1) {
            contaPerito = true
        }

        _cntPerito.value = contaPerito
    }

    fun verifyContaEmpregador() {

        var contaEmpregador: Boolean = false

        if (usuario.cod_usuario != 0 && usuario.user_emp == 1) {
            contaEmpregador = true
        }

        _cntEmpregador.value = contaEmpregador
    }

    fun loadDataUserFirebase() {

        // val listUsuario : MutableList <UserModel> = ArrayList<UserModel>();

        bd = FirebaseFirestore.getInstance()

        bd!!.collection("Usuarios").get().addOnSuccessListener { documentos ->

            if (documentos != null) {

                for (docuemnt in documentos) {
                    if (docuemnt.id.equals("usuario")) {

                        val usar = docuemnt.toObject(UserModel::class.java)

                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.COD_USER,
                            docuemnt.get("cod_usuario").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.USER_PERITO,
                            docuemnt.get("user_perito").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.USER_EMP,
                            docuemnt.get("user_emp").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.COD_PERITO,
                            docuemnt.get("cod_perito").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.COD_EMP,
                            docuemnt.get("cod_emp").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.NOME,
                            docuemnt.get("nome").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.CPF,
                            docuemnt.get("cpf").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.TELEFONE,
                            docuemnt.get("telefone").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.CIDADE,
                            docuemnt.get("cidade").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.ESTADO,
                            docuemnt.get("estado").toString()
                        )
                        securityPreferences.store(
                            DataBaseConstants.USER.COLUMNS.EMAIL,
                            docuemnt.get("email").toString()
                        )

                        usuario.cod_usuario = usar.cod_usuario!!
                        usuario.user_perito = usar.user_perito!!
                        usuario.user_emp = usar.user_emp!!
                        usuario.cod_perito = usar.cod_perito!!
                        usuario.cod_emp = usar.cod_emp!!
                        usuario.nome = usar.nome!!
                        usuario.cpf = usar.cpf!!
                        usuario.telefone = usar.telefone!!
                        usuario.cidade = usar.cidade!!
                        usuario.estado = usar.estado!!
                        usuario.email = usar.email!!
                    }
                }

                // Toast.makeText(applicationContext, "Dados lidos corretamente ", Toast.LENGTH_SHORT).show()
                _loadUsuario.value = ValidationModel()
            } else {
                _loadUsuario.value = ValidationModel("Pasta vazia ou inexistente !!")
            }
        }.addOnFailureListener { err ->
            _loadUsuario.value = ValidationModel("Dados lidos com ERRO: ${err.message.toString()}")
        }
    }

}