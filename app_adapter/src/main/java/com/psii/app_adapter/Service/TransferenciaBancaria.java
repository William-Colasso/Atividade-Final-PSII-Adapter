package com.psii.app_adapter.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psii.app_adapter.Model.Cliente;
import com.psii.app_adapter.Model.Pagamento;
// Serviço nativo do Banco
@Service
public class TransferenciaBancaria implements Pagamento {
    @Autowired
    private ClienteService clienteService;


    @Override
    public String processarPagamento(double valor, String codigo) {
        Optional<Cliente>clienteDestinoOptional=clienteService.getClienteById(codigo);
        

        if(clienteDestinoOptional.isPresent()){
            Cliente clienteDestino = clienteDestinoOptional.get();
            clienteDestino.setSaldo(clienteDestino.getSaldo()+valor);
            clienteService.createCliente(clienteDestino);
            return "GG";
        }



        return "Não existe um cliente com esse código.";
    }
}
