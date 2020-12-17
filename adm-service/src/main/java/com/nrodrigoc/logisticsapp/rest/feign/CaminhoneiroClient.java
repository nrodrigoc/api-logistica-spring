package com.nrodrigoc.logisticsapp.rest.feign;

import com.nrodrigoc.logisticsapp.rest.dto.FreteDTO;
import com.nrodrigoc.logisticsapp.rest.dto.InformacoesFreteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://localhost:1245/caminhoneiro", name = "caminhoneiro-service")
public interface CaminhoneiroClient {

    @PostMapping("/frete/")
    InformacoesFreteDTO addFrete(@RequestBody FreteDTO frete);
}
