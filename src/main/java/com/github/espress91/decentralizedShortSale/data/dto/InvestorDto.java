package com.github.espress91.decentralizedShortSale.data.dto;

import com.github.espress91.decentralizedShortSale.data.model.Investor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvestorDto {
    private Long id;

    private Long currentEtherPrice;

    private Long benefitFee;

    private LocalDateTime regTime;

    private LocalDateTime delTime;

    public Investor toEntity() {
        Investor investor = new Investor();

        investor.setRegTime(LocalDateTime.now());
        investor.setDelTime(LocalDateTime.now());

        return investor;
    }

    public static InvestorDto convertToDto(Investor investor) {
        InvestorDto investorDto = new InvestorDto();

        investorDto.setId(investor.getId());

        return investorDto;
    }
}
