package app.spring.caminhoneiro.rest.feign;

import app.spring.caminhoneiro.rest.dto.AtualizaStatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://localhost:80/v1/api/pedidos", name = "pedidos")
public interface PedidoClient {

    @PostMapping("/atualizar")
    void atualizarStatus(@RequestBody AtualizaStatusDTO dto);

}
