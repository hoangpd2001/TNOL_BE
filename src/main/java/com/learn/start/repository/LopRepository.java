package com.learn.start.repository;

import com.learn.start.entity.Lop;
import com.learn.start.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LopRepository extends JpaRepository<Lop, Integer> {

    List<Lop> findAll();
    Optional<Lop> findById(int id);

}
