package app.spring.caminhoneiro.rest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8182/v1/geral/api/caminhoneiro", name = "app-logistica")
public interface CaminhoneiroClient {

    @PostMapping("/{id}/")
    void salvar(@PathVariable("id") Integer caminhoneiro_id);

}
