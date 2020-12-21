package com.example.demo.service;

import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorDetail;
import com.example.demo.repository.InstructorRepository;
import com.example.demo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public void save(Instructor inst, Map<String, String> postData) {
        if (!StringUtil.isBlank(postData.get("website"))) {
            InstructorDetail detail = new InstructorDetail(postData.get("website"));
            inst.setDetail(detail);
            log.info("inst detail: {}", detail);
        }
        log.info("instructor: {}", inst);
        instRepo.save(inst);
    }

    public void save(Instructor inst) {
        // TODO: 12/21/20 - can we save below line from the InstructionDetailService?
//        inst.setInstructorDetail(inst.getDetail());

        /*InstructorDetail detail = inst.getDetail();
        detail.setInstructor(inst);
        instDetailService.save(detail);*/

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
