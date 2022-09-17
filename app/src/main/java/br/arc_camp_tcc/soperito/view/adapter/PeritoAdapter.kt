package br.arc_camp_tcc.soperito.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.arc_camp_tcc.soperito.databinding.ActivityRowPeritoListBinding
import br.arc_camp_tcc.soperito.service.listeners.PeritoListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel
import br.arc_camp_tcc.soperito.view.viewholder.PeritoViewHolder

class PeritoAdapter : RecyclerView.Adapter<PeritoViewHolder>() {

    private var listPeritos : List<ListPeritoModel> = arrayListOf()
    private lateinit var listener: PeritoListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeritoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itembinding = ActivityRowPeritoListBinding.inflate(inflater, parent, false)
        return PeritoViewHolder(itembinding, listener)  // retorna o layout
    }

    // chama o PeritoViewHolder
    override fun onBindViewHolder(holder: PeritoViewHolder, position: Int) {
        holder.bindData(listPeritos[position])
    }

    // tamanho da lista para organizaçao da recycleView
    override fun getItemCount(): Int {
        return listPeritos.count()
    }

    // atualiza a lista do adapter
    fun updatePeritos(list: List<ListPeritoModel>){
        listPeritos = list
        notifyDataSetChanged()
    }

    fun attachListener(listPeritos : PeritoListener){
        listener = listPeritos
    }

}