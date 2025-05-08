package com.learn.start.repository;

import com.learn.start.entity.Chuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChuongRepository extends JpaRepository<Chuong, Integer> {
    List<Chuong> findByIdMon(int idMon);
}
