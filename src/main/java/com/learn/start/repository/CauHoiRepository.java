package com.learn.start.repository;


import com.learn.start.dto.response.CauHoiDTO_Res;
import com.learn.start.entity.CauHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CauHoiRepository extends JpaRepository<CauHoi, Integer> {

    @Query("SELECT c FROM CauHoi c JOIN FETCH c.chuong ch JOIN FETCH c.giaoVien gv")
    List<CauHoi> getAllWithRelations();
    List<CauHoi> findByGiaoVienIdAndChuongId(int giaoVienId, int chuongId);

    List<CauHoi> findByGiaoVienIdAndChuongIdAndMucDo(int giaoVienId, int chuongId, int mucDo);
}