package com.example.ums

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.DepartmentRecycleViewAdapter
import com.example.Helper.provideDepartmentViewModelFactory
import com.example.StudentRecords.StudentsRecordActivity
import com.example.UniversityDatabase.DepartmentEntity
import com.example.ViewModels.DepartmentViewModel
import com.example.todoaac.startStudentsRecordActivity
import com.example.ums.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), DepartmentRecycleViewAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var departmentViewModel: DepartmentViewModel? = null
    private lateinit var departmentRecycleViewAdapter: DepartmentRecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        departmentRecycleViewAdapter = DepartmentRecycleViewAdapter(this, this)
        binding.recycleview.adapter = departmentRecycleViewAdapter
        binding.recycleview.layoutManager = LinearLayoutManager(this)

        departmentViewModel = ViewModelProvider(
            this,
            provideDepartmentViewModelFactory()).get(DepartmentViewModel::class.java)

        binding.addDepartmentBtn.setOnClickListener {
            val intent = Intent(this, AddDepartmentsActivity::class.java)
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
                if (swipeDir == 1 shl 2) {
                    lifecycleScope.launch {
                        val getDepartmentData: DepartmentEntity? =
                            departmentRecycleViewAdapter.getDepartments()?.get(viewHolder.adapterPosition)
                        if (getDepartmentData != null) {
                            departmentViewModel?.deleteDepartment(getDepartmentData)
                        }
                    }

                }
                else if (swipeDir == 1 shl 3) {
                    val depart_local_id: Int = departmentRecycleViewAdapter.getDepartments()
                        ?.get(viewHolder.adapterPosition)!!.depart_local_id
                    val intent = Intent(this@MainActivity, AddDepartmentsActivity::class.java)
                    intent.putExtra(startStudentsRecordActivity, depart_local_id)
                    startActivity(intent)
                }
            }
        }).attachToRecyclerView(binding.recycleview)
        setupViewModel()
    }

    override fun onItemClickListener(department_local_id: Int) {
        val intent = Intent(this, StudentsRecordActivity::class.java)
        intent.putExtra(startStudentsRecordActivity, department_local_id)
        startActivity(intent)
    }

    private fun setupViewModel() {
        departmentViewModel?.getDepartments()?.observe(this,
            Observer<List<Any?>?> { DepartmentEntries ->
                departmentRecycleViewAdapter.setDepartments(DepartmentEntries as List<DepartmentEntity>)
            })
    }
}