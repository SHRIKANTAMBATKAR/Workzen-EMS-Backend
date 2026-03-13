package com.workzen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workzen.ems.entity.Analyst;
import com.workzen.ems.repository.AnalystRepository;

@Service
public class AnalystService {

    @Autowired
    private AnalystRepository analystRepository;

    public Analyst addAnalyst(Analyst analyst) {
        return analystRepository.save(analyst);
    }

    public List<Analyst> getAllAnalysts() {
        return analystRepository.findAll();
    }

    public void deleteAnalyst(Long id) {
        analystRepository.deleteById(id);
    }
}