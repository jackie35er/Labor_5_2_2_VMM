package org.jackie35er.labor_5_2_2_vmm.domain

import com.querydsl.core.annotations.QueryEntity
import org.hibernate.Hibernate
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
@QueryEntity
data class Grade (
    @Id
    @GeneratedValue
    val id: Long,

    @ManyToOne
    val student: Student,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    val assignedDate: LocalDate,

    @Enumerated(EnumType.STRING)
    val subject: Subject,

    @Min(1)
    @Max(5)
    val gradeNumber: Int,

    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Grade

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
    override fun toString(): String {
        return "Grade(id=$id, student=$student, assignedDate=$assignedDate, subject=$subject, grade=$gradeNumber)"
    }


}