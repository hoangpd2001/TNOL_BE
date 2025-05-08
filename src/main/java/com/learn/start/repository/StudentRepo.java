package com.learn.start.repository;

import com.learn.start.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.MaSV = :maSV")
    Student findByMaSV(@Param("maSV") String maSV);

}
