package br.arc_camp_tcc.soperito.service.model

// mapeamento de interface de banco
class TransacaoModel(
    val codTransc: Int,
    val codPerito: Int,
    val codEmp: Int,
    val nomeEmp: String,
    val nomePerito: String,
    val statusEmp: Boolean,
    val statusPerito: Boolean,
    val numEmp: Int,
    val numPerito: Int,
)

