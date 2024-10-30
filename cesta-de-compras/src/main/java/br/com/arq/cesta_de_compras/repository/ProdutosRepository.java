package br.com.arq.cesta_de_compras.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, UUID> {

}
