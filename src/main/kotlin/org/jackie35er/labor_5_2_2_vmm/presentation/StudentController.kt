package org.jackie35er.labor_5_2_2_vmm.presentation

import org.jackie35er.labor_5_2_2_vmm.domain.Grade
import org.jackie35er.labor_5_2_2_vmm.domain.Student
import org.jackie35er.labor_5_2_2_vmm.domain.Subject
import org.jackie35er.labor_5_2_2_vmm.persistence.GradeRepository
import org.jackie35er.labor_5_2_2_vmm.persistence.StudentRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate

@Controller
@RequestMapping("/")
class StudentController(
    val studentRepository: StudentRepository,
    val gradeRepository: GradeRepository,
) {

    @GetMapping("/")
    fun indexPage(model: Model): String{
        model.addAttribute("students", studentRepository.findAll())
        return "index"
    }

    @GetMapping("/details/{id}")
    fun getStudentDetails(@PathVariable id: Long, model: Model): String {
        model.addAttribute("student", studentRepository.findById(id).orElseThrow())
        model.addAttribute("subjects", Subject.values())
        model.addAttribute("date", LocalDate.now())
        model.addAttribute("grade", Grade(
            id = 0, student = Student(
                id = 0,
                lastName = "",
                firstName = "",
                grades = listOf()
            ), assignedDate = LocalDate.now(), subject = Subject.AM, gradeNumber = 0

        ))
        return "student-details"
    }

    @PostMapping("/details/{id}")
    fun addGrade(@PathVariable id: Long, @ModelAttribute("grade") grade: Grade ,model: Model): String {
        gradeRepository.save(grade)
        return getStudentDetails(id,model)
    }
}