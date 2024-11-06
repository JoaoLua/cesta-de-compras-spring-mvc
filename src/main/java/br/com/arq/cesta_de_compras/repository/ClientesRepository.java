package br.com.arq.cesta_de_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.Clientes;

import java.util.Optional;
import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    Optional<Clientes> findByUsuarioId(UUID usuarioId);
}