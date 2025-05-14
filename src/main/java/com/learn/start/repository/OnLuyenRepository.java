package com.learn.start.repository;

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

}
