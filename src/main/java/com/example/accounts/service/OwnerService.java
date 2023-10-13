package com.example.accounts.service;

import com.example.accounts.model.Owner;
import com.example.accounts.repository.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner create(String login){
        Owner owner = new Owner();
        owner.setLogin(login);
        return ownerRepository.save(owner);
    }

}
