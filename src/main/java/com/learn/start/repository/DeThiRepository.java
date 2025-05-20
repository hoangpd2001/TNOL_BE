package com.learn.start.repository;

import com.learn.start.entity.CauHoi;
import com.learn.start.entity.DeThi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeThiRepository extends JpaRepository<DeThi, Integer> {
    List<DeThi> findByGiaoVien_Id( Integer giaoVienId);
    List<DeThi> findByGiaoVien_IdAndChuong_Mon_Lop_Id( Integer giaoVienId, Integer lopId);
    List<DeThi> findByGiaoVien_IdAndChuong_Mon_Id( Integer giaoVienId, Integer monId);
    List<DeThi> findByGiaoVien_IdAndChuong_Id( Integer giaoVienId, Integer chuongId);

    List<DeThi> findByChuong_Mon_Lop_Id( Integer lopId);
    List<DeThi> findByChuong_Mon_Id( Integer monId);
    List<DeThi> findByChuong_Id( Integer chuongId);
}
