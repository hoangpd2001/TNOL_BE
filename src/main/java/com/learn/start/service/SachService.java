package com.learn.start.service;

import com.learn.start.entity.Sach;
import com.learn.start.repository.SachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SachService {
    @Autowired
    private SachRepository sachRepository;

    public List<Sach> getSachByMaSach(String maSach) {

        List<Sach> list = sachRepository.getSachByMaSach(maSach);
        System.out.println(list);
        return list;
    }

//    public List<Sach> getSach() {
//
//        return  sachRepository.getSach();
//    }
}

