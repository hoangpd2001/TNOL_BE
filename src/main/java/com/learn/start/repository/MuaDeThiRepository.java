package com.learn.start.repository;

import com.learn.start.entity.MuaDeThi;
import com.learn.start.entity.MuaDeThiID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuaDeThiRepository extends JpaRepository<MuaDeThi, MuaDeThiID> {

    List<MuaDeThi> findByUsersId(Integer idUsers);

    List<MuaDeThi> findByDeThiId(Integer idDeThi);
}
