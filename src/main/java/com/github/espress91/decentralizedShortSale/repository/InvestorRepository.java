package com.github.espress91.decentralizedShortSale.repository;

import com.github.espress91.decentralizedShortSale.data.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
