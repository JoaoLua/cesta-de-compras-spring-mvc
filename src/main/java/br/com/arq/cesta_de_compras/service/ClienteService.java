package br.com.arq.cesta_de_compras.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.entity.Usuarios;
import br.com.arq.cesta_de_compras.repository.ClientesRepository;
import br.com.arq.cesta_de_compras.repository.UsuariosRepository;

@Service
public class ClienteService {

    @Autowired
    private ClientesRepository clienteRepository;

    @Autowired
    private UsuariosRepository usuarioRepository;
    
    public boolean clienteExiste(UUID usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId).isPresent();
    }

    public Clientes salvarCliente(Clientes cliente, UUID usuarioId) {
    	//metodo associa cliente ao usuario
        Usuarios usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        cliente.setUsuario(usuario); 
        return clienteRepository.save(cliente);
    }
}