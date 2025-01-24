package com.example.deshimarket.service;

import com.example.deshimarket.model.GigModel;
import com.example.deshimarket.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GigService {
    @Autowired
    private final GigRepository gigRepository;

    public GigService(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    public List<GigModel> searchGigs(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return gigRepository.findAll();
        }
        return gigRepository.searchByTitleOrKeywords(searchTerm);
    }

    public void createGig(GigModel gigModel) {
        gigRepository.save(gigModel);
    }

    public Optional<GigModel> findById(int id) {
        return gigRepository.findById(id);
    }

/*    @Autowired
    private GigRepository gigRepository;

    public void createGig(GigModel gigModel) {
        gigRepository.save(gigModel);

    }*/
}
