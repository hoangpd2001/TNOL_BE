package com.learn.start.repository;

import com.learn.start.entity.KQOnLuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KQOnLuyenRepository extends JpaRepository<KQOnLuyen, Integer> {

    List<KQOnLuyen> findKQOnLuyenByOnLuyenId(Integer onLuyenId);
}
