package com.example.demo.service;

import com.example.demo.model.InstructorDetail;
import com.example.demo.repository.InstructorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorDetailService {

    private final InstructorDetailRepository detailRepo;

    @Autowired
    public InstructorDetailService(InstructorDetailRepository detailRepo) {
        this.detailRepo = detailRepo;
    }

    public InstructorDetail findById(long id) {
        return detailRepo.findById(id).orElseGet(InstructorDetail::new);
    }

    public void save(InstructorDetail detail) {
        detailRepo.save(detail);
    }
}
