package com.example.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.UniversityDatabase.DepartmentEntity
import com.example.ums.R

class DepartmentRecycleViewAdapter(private var mContext: Context, var listener: ItemClickListener) :
    RecyclerView.Adapter<DepartmentRecycleViewAdapter.WordViewHolder>() {

    private var DepartmentEntries: List<DepartmentEntity>? = null

    fun setDepartments(department_List: List<DepartmentEntity>) {
        DepartmentEntries = department_List
        notifyDataSetChanged()
    }

    fun getDepartments(): List<DepartmentEntity?>? {
        return DepartmentEntries

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.department_viw_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        var departmentItem = DepartmentEntries?.get(position)

        holder.tv.text = departmentItem!!.departmentName.toString() + "\n"+ departmentItem.departmentLocation
        holder.itemView.setOnClickListener {
            listener.onItemClickListener(departmentItem.depart_local_id)
        }
    }

    override fun getItemCount(): Int {
        if (DepartmentEntries == null) {
            return 0;
        }
        return DepartmentEntries!!.size
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.item_text)
    }

    interface ItemClickListener {
        fun onItemClickListener(depart_local_id: Int)
    }
}