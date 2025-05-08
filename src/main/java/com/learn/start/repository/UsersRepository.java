package com.learn.start.repository;

import com.learn.start.entity.Student;
import com.learn.start.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByGmail(String gmail);
    Optional<Users> getUsersById(int Id);
}