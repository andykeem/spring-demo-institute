package com.example.demo.service;

import com.example.demo.model.Instructor;
import com.example.demo.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {

    private final Logger log = LoggerFactory.getLogger(InstructorService.class);
    private final InstructorRepository instRepo;
    private final InstructorDetailService instDetailService;

    @Autowired
    public InstructorService(InstructorRepository instRepo, InstructorDetailService instDetailService) {
        this.instRepo = instRepo;
        this.instDetailService = instDetailService;
    }

    public List<Instructor> findAll() {
        return instRepo.findAll();
    }

    public void save(Instructor inst) {
        instRepo.save(inst);
    }

    public void deleteById(long id) {
        instRepo.deleteById(id);
    }

    public Instructor findById(long id) {
        Optional<Instructor> optInst = instRepo.findById(id);
        return optInst.orElseGet(Instructor::new);
    }
}
