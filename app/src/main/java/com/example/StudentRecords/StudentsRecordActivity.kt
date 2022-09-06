package com.example.StudentRecords

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.StudentsRecyclerViewAdapter
import com.example.Helper.provideStudentViewModelFactory
import com.example.StudentsDatabaseRecords.StudentEntity
import com.example.ViewModels.StudentViewModel
import com.example.todoaac.sendingDepartID
import com.example.todoaac.startAddStudentsActivity
import com.example.todoaac.startStudentsRecordActivity
import com.example.ums.databinding.ActivityStudentsDataBinding
import kotlinx.coroutines.launch

class StudentsRecordActivity : AppCompatActivity(), StudentsRecyclerViewAdapter.ItemClickListener {

    private lateinit var binding: ActivityStudentsDataBinding
    private lateinit var studentsRecyclerViewAdapter: StudentsRecyclerViewAdapter
    private lateinit var studentViewModel: StudentViewModel
    private var depart_id_reciver = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityStudentsDataBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        studentsRecyclerViewAdapter = StudentsRecyclerViewAdapter(this, this)
        binding.recycleview.adapter = studentsRecyclerViewAdapter
        binding.recycleview.layoutManager = LinearLayoutManager(this)

        studentViewModel = ViewModelProvider(
            this,
            provideStudentViewModelFactory()
        ).get(StudentViewModel::class.java)

        depart_id_reciver = intent.getIntExtra(startStudentsRecordActivity, depart_id_reciver)

        binding.addStudentsBtn.setOnClickListener {
            val intent = Intent(this, AddStudentsActivity::class.java)
            intent.putExtra(sendingDepartID, depart_id_reciver)
            startActivity(intent)
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val studentRecord: StudentEntity? =
                    studentsRecyclerViewAdapter.getStudents()?.get(viewHolder.adapterPosition)
                lifecycleScope.launch {
                    if (studentRecord != null) {
                        studentViewModel.deleteStudents(studentRecord)
                    }
                }
            }
        }).attachToRecyclerView(binding.recycleview)
        setupViewModel()
    }

    override fun onItemClickListener(student_local_id: Int) {
        val intent = Intent(this, AddStudentsActivity::class.java)
        intent.putExtra(startAddStudentsActivity, student_local_id)
        intent.putExtra(sendingDepartID, depart_id_reciver)
        startActivity(intent)
    }

    private fun setupViewModel() {
        studentViewModel.getAllStudents(depart_id_reciver).observe(this,
            Observer<List<Any?>?> { taskEntries ->
                studentsRecyclerViewAdapter.setStudentsData(taskEntries as List<StudentEntity>)
            }
        )
    }
}