package com.learn.start.repository;

import com.learn.start.entity.Mon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonRepository extends JpaRepository<Mon, Integer> {
    List<Mon> findByLop_Id(int idLop);
}
