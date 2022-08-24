package br.arc_camp_tcc.soperito.service.constants

// Constants deixa o codigo mais seguro caso alguma variavel seja escrita errada
class DataBaseConstants private constructor() {

    object USER{
        const val TABLE_NAME = "usuarios"

        object COLUMNS {
            const val COD_USER = "codigUsuario"
            const val USER_PERITO = "userPerito"
            const val USER_EMP = "userEmp"
            const val COD_PERITO = "codPerito"
            const val COD_EMP = "codEmp"
            const val EMAIL = "email"
            const val NOME = "nome"
            const val CPF = "cpf"
            const val TELEFONE = "telefone"
            const val SENHA = "senha"
        }
    }

    object PERITO{
        const val TABLE_NAME = "peritos"

        object COLUMNS {
            const val COD_PERITO = "codPerito"
            const val USER_PERITO = "userPerito"
            const val COD_USER = "codigUsuario"
            const val SERVICE = "service"
            const val TEMP = "temp"
            const val OBS = "obs"
            const val VALOR = "valor"
            const val EXP = "exp"
            const val ESP = "esp"
        }

    }

    object EMPREGADOR{
        const val TABLE_NAME = "empregadores"

        object COLUMNS {
            const val COD_EMPREGADOR = "codEmpregador"
            const val COD_USER = "codigUsuario"
            const val USER_EMP = "userEmp"
            const val STATUS_EMP = "statusEmp"
            const val DISP_PAGAR = "dispPagar"
            const val SERV_DESEJADO = "servicoDesejado"
            const val TEMP_EXP = "tempExp"
            const val DESC_VAGA = "descVaga"
        }

    }

    object TRANSACAO{
        const val TABLE_NAME = "transacoes"

        object COLUMNS {
            const val COD_TRANSAC = "codTransc"
            const val COD_PERITO = "codPerito"
            const val COD_EMP = "codEmp"
            const val NOME_EMP = "nomeEmp"
            const val NOME_PERITO = "nomePerito"
            const val STATUS_EMP = "statusEmp"
            const val STATUS_PERITO = "statusPerito"
            const val NUM_EMP = "numEmp"
            const val NUM_PERITO = "numPerito"
        }

    }

    object HTTP{
        const val SUCCESS = 200
    }

    // requisiçoes API
    object HEADER{
        const val TOKEN_KEY = "senha"
        const val PERSON_KEY = "email"
    }

    // dados de compartilhamento
    object SHARED{
        const val TOKEN_KEY = "senha"
        const val PERSON_KEY = "email"
        const val USER_KEY = "codUser"
    }

}