package com.learn.start.repository;

import com.learn.start.entity.DeThi;
import com.learn.start.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiaoDichRepository  extends JpaRepository<GiaoDich, Integer> {
    GiaoDich findGiaoDichByMaGD(String maGD);
}
