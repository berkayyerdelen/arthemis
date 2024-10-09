package com.artemis.prepaid.users.services;

import com.artemis.prepaid.users.models.EndUser;
import com.artemis.prepaid.users.repositories.EndUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EndUserService {

    private EndUserRepository endUserRepository;

    public EndUserService(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }

    public List<EndUser> getAllUsers() {
        return endUserRepository.findAll();
    }

    public EndUser saveUser(EndUser endUser){
        return endUserRepository.save(endUser);
    }

    public void delete(UUID uniqueId){
        endUserRepository.deleteById(uniqueId);
    }

    public EndUser findById(UUID uniqueId) {
        return endUserRepository.findById(uniqueId)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Entity not found for id: " + uniqueId));
    }

}
