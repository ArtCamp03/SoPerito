package br.arc_camp_tcc.soperito.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.arc_camp_tcc.soperito.databinding.ActivityRowPeritoListBinding
import br.arc_camp_tcc.soperito.service.listeners.PeritoListener
import br.arc_camp_tcc.soperito.service.model.ListPeritoModel

class PeritoViewHolder(private val itemBinding: ActivityRowPeritoListBinding, val listener: PeritoListener):
    RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(listPerito: ListPeritoModel) {

            itemBinding.textNomePerito.text = listPerito.nome
            itemBinding.textNomeVaga.text = listPerito.servico

            val nome = listPerito.nome
            itemBinding.textIconPerito.text = nome.first().toString()

            itemBinding.textData.text = listPerito.dataCurriculo

            /*
            itemBinding.textIconPerito.setOnClickListener() {
                listener.onlistClick(listPerito.codCurriculo)
            }
             */

            itemBinding.relativePerito.setOnClickListener {
                listener.onlistClick(listPerito.codCurriculo)
            }

        }

            /*

            val data = SimpleDateFormat("yyyy-MM-dd").parse(listPerito.data)

            itemBinding.textData.text = SimpleDateFormat("dd/MM/yyyy").format(data)

            itemBinding.imageAceptPerito.setOnClickListener{
                AlertDialog.Builder(itemView.context)
                    .setTitle("Aceitar Perito")
                    .setMessage("Aceitar Perito candidatop ?")
                    .setPositiveButton("Sim") { dialog, wish ->
                        listener.onDelete(listPerito.codCurriculo)
                    }
                    .setNeutralButton("cancelar", null)
                    .show()
                true
            }

            itemBinding.imageDeletePerito.setOnClickListener{
                AlertDialog.Builder(itemView.context)
                    .setTitle("Recusar Perito")
                    .setMessage("Recusar Perito candidatop ?")
                    .setPositiveButton("Sim") { dialog, wish ->
                        listener.onDelete(listPerito.codCurriculo)
                    }
                    .setNeutralButton("cancelar", null)
                    .show()
                true
            }

         */
}