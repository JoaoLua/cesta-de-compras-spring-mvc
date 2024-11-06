package br.com.arq.cesta_de_compras.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, UUID> {
	
	Optional<Usuarios> findByEmail(String email);
	Optional<Usuarios> findById(UUID id);
	
}
