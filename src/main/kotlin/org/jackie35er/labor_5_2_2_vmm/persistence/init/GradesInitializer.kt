package org.jackie35er.labor_5_2_2_vmm.persistence.init

import org.jackie35er.labor_5_2_2_vmm.persistence.GradeRepository
import org.jackie35er.labor_5_2_2_vmm.persistence.StudentRepository
import org.jackie35er.labor_5_2_2_vmm.domain.Grade
import org.jackie35er.labor_5_2_2_vmm.domain.Subject
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate

@Component
class GradesInitializer(
    private val studentsRepository: StudentRepository,
    private val gradesRepository: GradeRepository,
): ApplicationRunner{

    override fun run(args: ApplicationArguments?) {
        val gradesCsv = Path.of(
            GradesInitializer::class.java.classLoader.getResource("grades.csv")?.toURI()
                ?: throw FileNotFoundException("File not found")
        )

        Files.lines(gradesCsv)
            .skip(1)
            .map { it.mapToGrade() }
            .forEach(gradesRepository::save)
    }

    private fun String.mapToGrade(): Grade {
        val csv = this.split(",")
        val student = studentsRepository.findById(csv[0].toLong()).get()
        val date = LocalDate.parse(csv[1])
        val subject = Subject.valueOf(csv[2])
        val grade = csv[3].toInt()
        return Grade(0,student,date, subject, grade)
    }
}