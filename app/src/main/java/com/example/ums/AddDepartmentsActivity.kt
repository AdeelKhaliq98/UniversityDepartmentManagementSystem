package com.example.ums

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.Helper.provideDepartmentAddTaskViewModelFactory
import com.example.UniversityDatabase.DepartmentEntity
import com.example.todoaac.startStudentsRecordActivity
import com.example.ums.databinding.ActivityAddDepartmentsBinding
import kotlinx.coroutines.launch

class AddDepartmentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDepartmentsBinding
    private var departId = -1
    private lateinit var viewModel: DepartmentAddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddDepartmentsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            provideDepartmentAddTaskViewModelFactory()
        ).get(DepartmentAddTaskViewModel::class.java)

        departId = intent.getIntExtra(startStudentsRecordActivity, departId)

        if (intent != null && intent.hasExtra(startStudentsRecordActivity)) {
            binding.saveBtn.setText("Update")
            if (departId != -1) {
                viewModel.getDepartments(departId)?.observe(this, object : Observer<DepartmentEntity?> {
                    override fun onChanged(@Nullable studentEntries: DepartmentEntity?) {
                        viewModel.getDepartments(departId)?.removeObserver(this)
                        populateUI(studentEntries)
                    }
                })
            }
        }

        binding.saveBtn.setOnClickListener {
            val depart_name = binding.departmentName.text.toString()
            val depart_location = binding.departmentLocation.text.toString()
            val departmentData = DepartmentEntity(0, depart_name, depart_location)
                lifecycleScope.launch{
                    if (departId != -1) {
                        departmentData.depart_local_id = departId
                        viewModel.updateDepartment(departmentData)
                    } else {
                        viewModel.insertDepartment(departmentData)
                    }
                }
                finish()
        }
    }

    fun populateUI(task: DepartmentEntity?) {
        if (task != null) {
            binding.departmentName.setText(task.departmentName)
            binding.departmentLocation.setText(task.departmentLocation)
        }
    }
}