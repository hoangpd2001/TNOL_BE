package com.learn.start.repository;

import com.learn.start.entity.Sach;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


//@Repository
//public interface SachRepository extends JpaRepository<Sach, String> {
//
//    @Query(value = "SELECT FN_Hello(:p_string) FROM dual", nativeQuery = true)
//    String getSachByMaSach2(@Param("p_string") String maSach);
//
//    @Query(value = "SELECT FN_GetSachByMaSach(:p_MaSach) FROM dual", nativeQuery = true)
//    Sach getSachByMaSach(@Param("p_MaSach") String maSach);
//
//    @Query(value = "SELECT FN_GetSachByMaSach('MS1') FROM dual", nativeQuery = true)
//    List<Sach> getSach();
//}
@Repository
public class SachRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Sach> getSachByMaSach(String maSach) {
        return entityManager.createNamedStoredProcedureQuery("Sach.getSachByMaSach")
                .setParameter("p_MaSach", maSach)
                .getResultList();
    }

    private Sach convertToSach(Object[] row) {
        Sach sach = new Sach();
        sach.setMaSach((String) row[0]);
        sach.setTenSach((String) row[1]);
        sach.setTacGia((String) row[2]);
        sach.setLoaiSach((String) row[3]);
        sach.setNxb((String) row[4]);
        sach.setSoLuong((int)row[5]);
        sach.setDonGia((int)row[6]);
        return sach;
    }
}

