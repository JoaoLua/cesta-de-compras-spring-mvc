package br.com.arq.cesta_de_compras.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

}
