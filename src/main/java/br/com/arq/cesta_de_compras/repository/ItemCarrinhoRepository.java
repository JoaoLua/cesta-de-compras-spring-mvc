package br.com.arq.cesta_de_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer> {

}
