package es.pau.calculadoraoposiciones.features.taketopics

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import es.pau.calculadoraoposiciones.R
import es.pau.calculadoraoposiciones.utils.inflate
import kotlinx.android.synthetic.main.topic_item.view.*

class TopicListAdapter(val context: Context, val topicList: ArrayList<Int>) : RecyclerView.Adapter<TopicListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.topic_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(topicList[position])

    override fun getItemCount(): Int = topicList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(topic: Int) {
            itemView.topicNumber.text = topic.toString()
        }

    }


}
