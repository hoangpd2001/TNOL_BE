package com.learn.start.repository;

import com.learn.start.entity.DeThi;
import com.learn.start.entity.OnLuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OnLuyenRepository extends JpaRepository<OnLuyen, Integer>  {
    Optional<OnLuyen> findFirstByDeThi_IdAndUsers_IdAndThoiGianKetThucIsNullOrderByThoiGianBatDauDesc(
            Integer idDeThi,
            Integer idUser
    );
    List<OnLuyen> findByUsers_Id(Integer usersId);
    List<OnLuyen> findByUsers_IdAndDeThi_Chuong_Mon_Lop_Id( Integer usersId, Integer lopId);
    List<OnLuyen> findByUsers_IdAndDeThi_Chuong_Mon_Id( Integer usersId, Integer monId);
    List<OnLuyen> findByUsers_IdAndDeThi_Chuong_Id(Integer usersId, Integer chuongId);

    List<OnLuyen> findByDeThi_Chuong_Mon_Lop_Id( Integer lopId);
    List<OnLuyen> findByDeThi_Chuong_Mon_Id( Integer monId);
    List<OnLuyen> findByDeThi_Chuong_Id( Integer chuongId);
}
