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

    List<CauHoi> findByMucDo(Integer mucDo);
    List<CauHoi> findByChuong_Mon_Lop_Id(Integer lopId);
    List<CauHoi> findByChuong_Mon_Lop_IdAndMucDo(Integer lopId, Integer mucDo);

    List<CauHoi> findByChuong_Mon_Id(Integer monId);
    List<CauHoi> findByChuong_Mon_IdAndMucDo(Integer monId, Integer mucDo);
    List<CauHoi> findByChuong_Id(Integer chuongId);

    List<CauHoi> findByChuong_IdAndMucDo(Integer chuongId, Integer mucDo);
    List<CauHoi> findByMucDoAndGiaoVien_Id(Integer mucDo,Integer giaoVienId);
    List<CauHoi> findByChuong_Mon_Lop_IdAndGiaoVien_Id(Integer lopId,Integer giaoVienId);
    List<CauHoi> findByChuong_Mon_Lop_IdAndMucDoAndGiaoVien_Id(Integer lopId, Integer mucDo,Integer giaoVienId);

    List<CauHoi> findByChuong_Mon_IdAndGiaoVien_Id(Integer monId,Integer giaoVienId);
    List<CauHoi> findByChuong_Mon_IdAndMucDoAndGiaoVien_Id(Integer monId, Integer mucDo,Integer giaoVienId);
    List<CauHoi> findByChuong_IdAndGiaoVien_Id(Integer chuongId,Integer giaoVienId);

    List<CauHoi> findByChuong_IdAndMucDoAndGiaoVien_Id(Integer chuongId, Integer mucDo,Integer giaoVienId);


}