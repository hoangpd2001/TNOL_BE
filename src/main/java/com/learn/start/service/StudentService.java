package com.learn.start.service;

import com.learn.start.dto.request.StudentCreationRequest;
import com.learn.start.entity.Student;
import com.learn.start.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Student creatStudent(StudentCreationRequest request){
        Student student = new Student();
        student.setMaSV(request.getMaSV());
        student.setHoTen(request.getHoTen());
        student.setGioiTinh(request.getGioiTinh());
        student.setNgaySinh(request.getNgaySinh());
        student.setDiaChi(request.getDiaChi());
        student.setSoDienThoai(request.getSoDienThoai());
        student.setLop(request.getLop());
        return studentRepo.save(student);
    }
    public List<Student> selectStudent(){
        return studentRepo.findAll();
    }
    public Student selectStudentById(String id){
        return studentRepo.findByMaSV(id);
    }

}
