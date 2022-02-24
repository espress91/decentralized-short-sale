package com.github.espress91.decentralizedShortSale.controller;

import com.github.espress91.decentralizedShortSale.data.dto.InvestorDto;
import com.github.espress91.decentralizedShortSale.data.dto.response.ApiResult;
import com.github.espress91.decentralizedShortSale.service.InvestorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.espress91.decentralizedShortSale.data.dto.response.ApiResult.OK;

@Slf4j
@RestController
@RequestMapping("investor")
public class InvestorRestController {

    private final InvestorService investorService;

    public InvestorRestController(InvestorService investorService) {
        this.investorService = investorService;
    }

    @PostMapping
    public ApiResult<InvestorDto> enrollInvestor(@RequestBody InvestorDto investorDto) {
        return OK(InvestorDto.convertToDto(investorService.enrollInvestor(investorDto.toEntity())));
    }

}
