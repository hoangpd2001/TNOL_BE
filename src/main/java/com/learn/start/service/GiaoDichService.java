package com.learn.start.service;

import com.learn.start.entity.GiaoDich;
import com.learn.start.repository.GiaoDichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiaoDichService {
    @Autowired
    GiaoDichRepository giaoDichRepository;
    public GiaoDich createGiaoDich(GiaoDich giaoDich) {
        return giaoDichRepository.save(giaoDich);
    }
    public GiaoDich findGiaoDichById(String id) {
        return giaoDichRepository.findGiaoDichByMaGD(id);
    }
}
