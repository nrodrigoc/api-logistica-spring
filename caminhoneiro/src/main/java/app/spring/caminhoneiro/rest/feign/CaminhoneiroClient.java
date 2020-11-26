package app.spring.caminhoneiro.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:80/v1/api/caminhoneiro", name = "caminhoneiro-service")
public interface CaminhoneiroClient {

    @PostMapping("/{id}/")
    void salvar(@PathVariable("id") Integer caminhoneiro_id);

}
