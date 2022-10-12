package org.jackie35er.labor_5_2_2_vmm.persistence

import org.jackie35er.labor_5_2_2_vmm.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long> {
}
