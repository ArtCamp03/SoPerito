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
            const val CIDADE = "cidade"
            const val ESTADO = "estado"
            const val SENHA = "senha"

        }
    }

    object PERITO{
        const val TABLE_NAME = "peritos"

        object COLUMNS {
            const val COD_PERITO = "codPerito"
            const val COD_USER = "codigUsuario"
            const val COD_CURRICULO = "codCurriculo"
            const val USER_PERITO = "userPerito"
            const val NOME = "nome"
            const val EXP = "exp"
            const val ESP = "espec"
        }

    }

    object CURRICULO{
        const val TABLE_NAME = "curriculos"

        object COLUMNS {
            const val COD_PERITO = "codPerito"
            const val COD_CURRICULO = "codCurriculo"
            const val NOME = "nome"
            const val SERVICO = "servico"
            const val TEMP = "temp"
            const val OBS = "obs"
            const val VALOR = "valor"
            const val DATA = "dataCurriculo"
            const val LOCAL = "local"
        }

    }

    object EMPREGADOR{
        const val TABLE_NAME = "empregadores"

        object COLUMNS {
            const val COD_EMPREGADOR = "codEmp"
            const val COD_USER = "codUser"
            const val USER_EMP = "userEmp"
            const val STATUS_EMP = "statusEmp"
            const val NOME = "nome"
            const val COD_VAGA = "codVaga"
            const val TELEFONE = "telefone"
            const val EMAIL = "email"
            /*
            const val DISP_PAGAR = "dispPagar"
            const val SERV_DESEJADO = "servicoDesejado"
            const val TEMP_EXP = "tempExp"
            const val DESC_VAGA = "descVaga"

             */
        }

    }


    object CANDIDATOS{
            const val TABLE_NAME = "candidatos"

        object COLUMNS {
            const val COD_CANDIDATO = "codCandidato"
            const val COD_EMP = "codEmp"
            const val COD_VAGA = "codVaga"
            const val COD_PERITO = "codPerito"
            const val STATUS_CAND = "statusCand"
        }

    }

    object HTTP{
        const val SUCCESS = 200
        const val FAILURE = 400
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

    // dados de compartilhamento
    object BUNDLE{
        const val COD_PERITO = "codPerito"
        const val COD_EMP = "codEmp"
        const val COD_CURRICULO = "cod_curriculo"
        const val NOME_VAGA = "servico"
        const val COD_VAGA = "cod_vaga"
    }

}