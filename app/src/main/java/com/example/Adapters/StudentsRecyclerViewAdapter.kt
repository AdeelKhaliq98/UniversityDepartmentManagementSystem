package com.example.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.StudentsDatabaseRecords.StudentEntity
import com.example.ums.R

class StudentsRecyclerViewAdapter(private var mContext: Context, var listener: ItemClickListener) :
    RecyclerView.Adapter<StudentsRecyclerViewAdapter.WordViewHolder>() {

    private var studentEnteries: List<StudentEntity>? = null

    fun setStudentsData(students_list: List<StudentEntity>) {
        studentEnteries = students_list
        notifyDataSetChanged()
    }

    fun getStudents(): List<StudentEntity>? {
        return studentEnteries

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.students_view_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val studentItems = studentEnteries?.get(position)

        holder.tv.text =
            "\t" + studentItems!!.studentName + "\n\t" + studentItems.studentRegNo + "\n\t" + studentItems.studentAge.toString()
                .toInt()

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(studentItems.std_local_id)
        }
    }

    override fun getItemCount(): Int {
        if (studentEnteries == null) {
            return 0
        }
        return studentEnteries!!.size
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.item_text)
    }

    interface ItemClickListener {
        fun onItemClickListener(student_local_id: Int)
    }
}