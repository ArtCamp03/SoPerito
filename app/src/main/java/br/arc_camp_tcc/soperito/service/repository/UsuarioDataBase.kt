package br.arc_camp_tcc.soperito.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.arc_camp_tcc.soperito.constants.DataBaseConstants

// DATABASE Conexao com o banco

class UsuarioDataBase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    // cria o nome do banco
    companion object {
        private const val NAME = "soperitoDB"
        private const val VERSION = 1

    }

    override fun onCreate(db: SQLiteDatabase) {
        // chamado uma unica vez quando a aplicaçao acessa o banco
        var usuario = "CREATE TABLE " + DataBaseConstants.USER.TABLE_NAME + "(" +
                    DataBaseConstants.USER.COLUMNS.COD_USER + "codigUsuario integer primary key autoincrement," +
                    DataBaseConstants.USER.COLUMNS.COD_SEG + "codSeguranca integer," +
                    DataBaseConstants.USER.COLUMNS.USER_PERITO + "codSeguranca text," +
                    DataBaseConstants.USER.COLUMNS.USER_EMP + "codSeguranca text," +
                    DataBaseConstants.USER.COLUMNS.EMAIL + "email text," +
                    DataBaseConstants.USER.COLUMNS.NOME + "nome text," +
                    DataBaseConstants.USER.COLUMNS.CPF + "cpf text," +
                    DataBaseConstants.USER.COLUMNS.TELEFONE + "telefone text," +
                    DataBaseConstants.USER.COLUMNS.SENHA + "senha text);"


        var perito = "CREATE TABLE " + DataBaseConstants.PERITO.TABLE_NAME + "(" +
                    DataBaseConstants.PERITO.COLUMNS.COD_PERITO + "codPerito integer primary key autoincrement," +
                    DataBaseConstants.PERITO.COLUMNS.USER_PERITO + "userPerito text," +
                    DataBaseConstants.PERITO.COLUMNS.COD_USER + "codigUsuario integer," +
                    DataBaseConstants.PERITO.COLUMNS.SERVICE + "service text," +
                    DataBaseConstants.PERITO.COLUMNS.TEMP + "temp integer," +
                    DataBaseConstants.PERITO.COLUMNS.OBS + "obs text," +
                    DataBaseConstants.PERITO.COLUMNS.VALOR + "valor real," +
                    DataBaseConstants.PERITO.COLUMNS.EXP + "exp text," +
                    DataBaseConstants.PERITO.COLUMNS.ESP + "esp text);"


        var empregador = "CREATE TABLE " + DataBaseConstants.EMPREGADOR.TABLE_NAME + "(" +
                    DataBaseConstants.EMPREGADOR.COLUMNS.COD_EMPREGADOR + "codEmpregador integer primary key autoincrement," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.COD_USER + "codigUsuario integer," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.USER_EMP + "userEmp text," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.STATUS_EMP + "statusEmp text," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.DISP_PAGAR + "dispPagar real," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.SERV_DESEJADO + "servicoDesejado text," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.TEMP_EXP + "tempExp integer," +
                    DataBaseConstants.EMPREGADOR.COLUMNS.DESC_VAGA + "descVaga text);"


        var transacao = "CREATE TABLE " + DataBaseConstants.TRANSACAO.TABLE_NAME + "(" +
                    DataBaseConstants.TRANSACAO.COLUMNS.COD_TRANSAC + "codTransc integer primary key autoincrement," +
                    DataBaseConstants.TRANSACAO.COLUMNS.COD_PERITO + "codTransc integer," +
                    DataBaseConstants.TRANSACAO.COLUMNS.COD_EMP + "codTransc integer," +
                    DataBaseConstants.TRANSACAO.COLUMNS.NOME_EMP + "nomeEmp text," +
                    DataBaseConstants.TRANSACAO.COLUMNS.NOME_PERITO + "nomePerito text," +
                    DataBaseConstants.TRANSACAO.COLUMNS.STATUS_EMP + "statusEmp text," +
                    DataBaseConstants.TRANSACAO.COLUMNS.STATUS_PERITO + "statusPerito text," +
                    DataBaseConstants.TRANSACAO.COLUMNS.NUM_EMP + "numEmp integer," +
                    DataBaseConstants.TRANSACAO.COLUMNS.NUM_PERITO + "numPerito integer);"


        // ria as tabela
        db.execSQL(usuario)
        db.execSQL(perito)
        db.execSQL(empregador)
        db.execSQL(transacao)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Atualzia versao do banco
        /*
            if(oldVersion == 1){
                if(newVersion == 2){
                    // Atualizaçao
                }
            }
         */
    }

}