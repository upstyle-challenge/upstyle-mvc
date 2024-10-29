package br.com.fiap.upstyle.service;

import br.com.fiap.upstyle.model.Recommendation;
import br.com.fiap.upstyle.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Recommendation> findAll() {
        return recommendationRepository.findAll();
    }

    public Recommendation save(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}