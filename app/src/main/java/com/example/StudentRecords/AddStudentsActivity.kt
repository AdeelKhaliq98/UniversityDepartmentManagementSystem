package com.example.StudentRecords

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.Helper.provideStudentAddTaskViewModelFactory
import com.example.StudentsDatabaseRecords.StudentEntity
import com.example.todoaac.sendingDepartID
import com.example.todoaac.startAddStudentsActivity
import com.example.ums.databinding.ActivityAddStudentsBinding
import kotlinx.coroutines.launch

class AddStudentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentsBinding

    private lateinit var viewModel: StudentAddTaskViewModel

    private var DepartLocalId = -1
    private var StudentLocalId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddStudentsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            provideStudentAddTaskViewModelFactory()
        ).get(StudentAddTaskViewModel::class.java)

        StudentLocalId = intent.getIntExtra(startAddStudentsActivity, StudentLocalId)
        DepartLocalId = intent.getIntExtra(sendingDepartID, StudentLocalId)

        if (intent != null && intent.hasExtra(startAddStudentsActivity)) {
            binding.saveBtn.setText("Update")
            if (StudentLocalId != -1) {
                viewModel.getStudents(StudentLocalId)
                    ?.observe(this, object : Observer<StudentEntity?> {
                        override fun onChanged(@Nullable taskEntry: StudentEntity?) {
                            viewModel.getStudents(StudentLocalId)?.removeObserver(this)
                            populateUI(taskEntry)
                        }
                    })
            }
        }
        binding.saveBtn.setOnClickListener {

            val student_Name = binding.studentName.text.toString()
            val student_RegNo = binding.studentRegNumber.text.toString()
            val student_age = binding.studentAge.text.toString().toInt()

            val gatherdStudentData =
                StudentEntity(0, student_Name, student_RegNo, student_age, DepartLocalId)

            lifecycleScope.launch {
                if (StudentLocalId != -1) {
                    gatherdStudentData.std_local_id = StudentLocalId
                    gatherdStudentData.stdId = DepartLocalId
                    viewModel.updateStudents(gatherdStudentData)
                } else {
                    viewModel.insertStudents(gatherdStudentData)
                }
            }
            finish()
        }
    }

    fun populateUI(task: StudentEntity?) {
        if (task != null) {
            binding.studentName.setText(task.studentName)
            binding.studentRegNumber.setText(task.studentRegNo)
            binding.studentAge.setText(task.studentAge.toString())
        }
    }
}