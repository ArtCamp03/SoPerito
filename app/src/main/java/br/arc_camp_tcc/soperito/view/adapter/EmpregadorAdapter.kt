package br.arc_camp_tcc.soperito.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.arc_camp_tcc.soperito.databinding.ActivityRowEmpregadorListBinding
import br.arc_camp_tcc.soperito.service.listeners.EmpregadorListener
import br.arc_camp_tcc.soperito.service.model.ListEmpregadorModel
import br.arc_camp_tcc.soperito.view.viewholder.EmpregadorViewHolder

class EmpregadorAdapter : RecyclerView.Adapter<EmpregadorViewHolder>(){

    private var listEmp : List<ListEmpregadorModel> = arrayListOf()
    private lateinit var listener: EmpregadorListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpregadorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding = ActivityRowEmpregadorListBinding.inflate(inflater, parent, false)
        return EmpregadorViewHolder(itembinding, listener)  // retorna o layout
    }

    // chama o PeritoViewHolder
    override fun onBindViewHolder(holder: EmpregadorViewHolder, position: Int) {
        holder.bindData(listEmp[position])
    }

    // tamanho da lista para organizaçao da recycleView
    override fun getItemCount(): Int {
        return listEmp.count()
    }

    // atualiza a lista do adapter
    fun updateEmp(list: List<ListEmpregadorModel>){
        listEmp = list
        notifyDataSetChanged()
    }

    fun attachListener(listEmp : EmpregadorListener){
        listener = listEmp
    }
}