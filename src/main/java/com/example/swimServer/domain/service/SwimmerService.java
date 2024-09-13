package com.example.swimServer.domain.service;

import com.example.swimServer.domain.model.entity.swimmer.SwimmerHistory;
import com.example.swimServer.infrastructure.persistance.maria.swimmer.SwimmerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SwimmerService {
   @Autowired
    private SwimmerRepository swimmerRepository;

    public void addSwimmer(SwimmerHistory swimmer){
        swimmerRepository.save(swimmer);
    }

    public SwimmerHistory getSwimmer(Long id){
        return swimmerRepository.findById(id).orElse(null);
    }

    public Iterable<SwimmerHistory> getAllSwimmers(){
        return swimmerRepository.findAll();
    }

    public void deleteSwimmer(Long id){
        swimmerRepository.deleteById(id);
    }

    public void updateSwimmer(SwimmerHistory swimmer){
        swimmerRepository.save(swimmer);
    }
}
