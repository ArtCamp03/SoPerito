package br.arc_camp_tcc.soperito.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.arc_camp_tcc.soperito.databinding.ActivityRowEmpregadorListBinding
import br.arc_camp_tcc.soperito.service.listeners.EmpregadorListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel

class EmpregadorViewHolder(private val itembinding: ActivityRowEmpregadorListBinding, val listener: EmpregadorListener):
    RecyclerView.ViewHolder(itembinding.root) {
    fun bindData(listEmp: ListEmpregadorModel) {

        itembinding.textNomeEmpregador.text = listEmp.servico
        itembinding.textDescricao.text = listEmp.descricao

        val nome = listEmp.nomeEmp
        itembinding.textIconEmpregador.text = nome.first().toString()

        itembinding.textDataEmpregador.text = listEmp.vData

        itembinding.relativeEmpregador.setOnClickListener {
            listener.onlistClick(listEmp.codVaga)
        }
    }
}