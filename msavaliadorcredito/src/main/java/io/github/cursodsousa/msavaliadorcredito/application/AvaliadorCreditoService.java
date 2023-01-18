package io.github.cursodsousa.msavaliadorcredito.application;

import io.github.cursodsousa.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.cursodsousa.msavaliadorcredito.domain.model.DadosCliente;
import io.github.cursodsousa.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.cursodsousa.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        // biblioteca nova para fazer a comunicação entre 2 microserviços - FEIGN
        /*
             Objetivos:
             - obserDadosCliente - de MSCLIENTES - ClientesResource.dadosCliente
             - obeterCartoesCliente - de MSCARTOES - CartoesResource.getCartoesByCliente
         */
        ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf);
        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();
    }



}
