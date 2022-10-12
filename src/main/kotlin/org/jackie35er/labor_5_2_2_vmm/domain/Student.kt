package org.jackie35er.labor_5_2_2_vmm.domain

import com.querydsl.core.annotations.QueryEntity
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "students")
@QueryEntity
data class Student(

    @GeneratedValue
    @Id
    val id: Long,

    val lastName: String,

    val firstName: String,

    @OneToMany(mappedBy = "student")
    val grades: List<Grade>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Student

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

