package com.learn.start.repository;

import com.learn.start.entity.GiaoVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoVienRepository extends JpaRepository<GiaoVien, Integer> {
    @Query("SELECT MAX(g.id) FROM GiaoVien g")
    String findLastId();



}
