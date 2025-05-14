package com.learn.start.repository;

import com.learn.start.entity.CauHoiDeThi;
import com.learn.start.entity.CauHoiDeThiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CauHoiDeThiRepository extends JpaRepository<CauHoiDeThi, CauHoiDeThiId> {
    List<CauHoiDeThi> findByDeThiId(Integer idDeThi);
}
