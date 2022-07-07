package br.arc_camp_tcc.soperito.service.repository

import android.content.ContentValues
import android.content.Context
import br.arc_camp_tcc.soperito.constants.DataBaseConstants
import br.arc_camp_tcc.soperito.service.model.UsuarioModel

//REPOSITORY manipulaçao de dados
//ninguem instancia a classe

class UsuarioRepository private constructor(context: Context) {

    // instancia UsuarioDataBase
    private val usuarioDataBase = UsuarioDataBase(context)

    //Singleton (padrao de projeto) controla as instancias da classe
    // fecha o construtor da claase mas possibilita obter a instancia

    companion object {

        // variavel privada para armazenar a instancia
        private lateinit var repository: UsuarioRepository

        fun getInstance(context: Context): UsuarioRepository {
            // verifica se a variavel repository ja foi inicializada alguma vez
            // Se nao estiver inicializado executa o comando

            if (!::repository.isInitialized) {
                // instancia SoPeritoRepository
                repository = UsuarioRepository(context)
            }
            return repository
        }
    }


    // insercao de dados
    fun insert(usuario: UsuarioModel): Boolean {
        // usuarioRepository()

        return try {

            val db = usuarioDataBase.writableDatabase

            val values = ContentValues()

            values.put(DataBaseConstants.USER.COLUMNS.EMAIL, usuario.email)
            values.put(DataBaseConstants.USER.COLUMNS.NOME, usuario.nome)
            values.put(DataBaseConstants.USER.COLUMNS.CPF, usuario.cpf)
            values.put(DataBaseConstants.USER.COLUMNS.TELEFONE, usuario.telefone)
            values.put(DataBaseConstants.USER.COLUMNS.SENHA, usuario.senha)
            //values.put(DataBaseConstants.USER.COLUMNS.COD_USER, usuario.codigUsuario)

            db.insert(DataBaseConstants.USER.TABLE_NAME, null, values)

            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(usuario: UsuarioModel): Boolean {

        return try {
            val db = usuarioDataBase.writableDatabase

            val values = ContentValues()
            values.put(DataBaseConstants.USER.COLUMNS.EMAIL, usuario.email)
            values.put(DataBaseConstants.USER.COLUMNS.NOME, usuario.nome)
            values.put(DataBaseConstants.USER.COLUMNS.CPF, usuario.cpf)
            values.put(DataBaseConstants.USER.COLUMNS.TELEFONE, usuario.telefone)
            values.put(DataBaseConstants.USER.COLUMNS.SENHA, usuario.senha)
            //values.put(DataBaseConstants.USER.COLUMNS.COD_USER, usuario.codigUsuario)

            val selection = DataBaseConstants.USER.COLUMNS.COD_USER + "= ?"
            val args = arrayOf(usuario.codigUsuario.toString())

            db.update(DataBaseConstants.USER.TABLE_NAME, values, selection, args)

            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {

        return try {
            val db = usuarioDataBase.writableDatabase

            val selection = DataBaseConstants.USER.COLUMNS.COD_USER + "= ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.USER.TABLE_NAME, selection, args)

            true
        } catch (e: Exception) {
            false
        }
    }

    // retorna atributos e valores de todo os usuarios
    fun getAll(): List<UsuarioModel> {

        // definir a lista
        val list = mutableListOf<UsuarioModel>()

        try {
            val db = usuarioDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.USER.COLUMNS.COD_USER,
                DataBaseConstants.USER.COLUMNS.EMAIL,
                DataBaseConstants.USER.COLUMNS.NOME,
                DataBaseConstants.USER.COLUMNS.CPF,
                DataBaseConstants.USER.COLUMNS.TELEFONE,
                DataBaseConstants.USER.COLUMNS.SENHA,
            )

            val cursor = db.query(
                DataBaseConstants.USER.TABLE_NAME, projection   ,
                null, null,
                null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val cod_user =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.COD_USER))
                    val email =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.EMAIL))
                    val nome =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.NOME))
                    val cpf =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.CPF))
                    val telefone =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.TELEFONE))
                    val senha =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.SENHA))

                }

            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun getLogin(email: String, senha:String): Boolean {

        try {
            val db = usuarioDataBase.readableDatabase

            val cursor = db.rawQuery("select email, senha from usuario",null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val email =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.EMAIL))
                    val senha =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.SENHA))
                }
                return true
            }
            cursor.close()
            return false
        } catch (e: Exception) {
            return false
        }
    }

    fun getPerito(): List<UsuarioModel> {

        // definir a lista
        val list = mutableListOf<UsuarioModel>()

        try {
            val db = usuarioDataBase.readableDatabase

            val cursor = db.rawQuery("select email from usuario",null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val cod_user =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.COD_USER))
                    val email =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.EMAIL))
                    val nome =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.NOME))
                    val cpf =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.CPF))
                    val telefone =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.TELEFONE))
                    val senha =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER.COLUMNS.SENHA))

                }

            }
            cursor.close()

        } catch (e: Exception) {
            return list
        }
        return list
    }


}