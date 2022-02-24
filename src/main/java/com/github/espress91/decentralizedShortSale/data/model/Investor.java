package com.github.espress91.decentralizedShortSale.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long currentEtherPrice;

    private Long benefitFee;

    private LocalDateTime regTime;

    private LocalDateTime delTime;

}
