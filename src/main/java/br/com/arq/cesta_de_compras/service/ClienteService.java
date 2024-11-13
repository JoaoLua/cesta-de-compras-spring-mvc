package br.com.arq.cesta_de_compras.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arq.cesta_de_compras.DTO.ClientesDTO;
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
    
	
    public ClientesDTO toDTO(Clientes cliente) {
        return new ClientesDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getCpf(),
            cliente.getTelefone(),
            cliente.getCep(),
            cliente.getCidade(),
            cliente.getBairro(),
            cliente.getRua(),
            cliente.getNumero(),
            cliente.getComplemento(),
            cliente.getUsuario().getId()  // Obtem o ID do usuario associado
        );
    }

    public Clientes toEntity(ClientesDTO clienteDTO, Usuarios usuario) {
        Clientes cliente = new Clientes();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCep(clienteDTO.getCep());
        cliente.setCidade(clienteDTO.getCidade());
        cliente.setBairro(clienteDTO.getBairro());
        cliente.setRua(clienteDTO.getRua());
        cliente.setNumero(clienteDTO.getNumero());
        cliente.setComplemento(clienteDTO.getComplemento());
        cliente.setUsuario(usuario); 
        return cliente;
    }
    
}