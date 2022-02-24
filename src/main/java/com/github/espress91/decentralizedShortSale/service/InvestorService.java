package com.github.espress91.decentralizedShortSale.service;

import com.github.espress91.decentralizedShortSale.data.model.Investor;
import com.github.espress91.decentralizedShortSale.repository.InvestorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class InvestorService {
    private final InvestorRepository investorRepository;

    public InvestorService(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public Optional<Investor> getInvestor(Long investorId) {
        return investorRepository.findById(investorId);
    }

    @Transactional
    public Investor enrollInvestor(Investor investor) {
        return investorRepository.save(investor);
    }
}
