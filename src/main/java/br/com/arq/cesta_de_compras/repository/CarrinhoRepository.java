package br.com.arq.cesta_de_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arq.cesta_de_compras.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {
    Carrinho findByClienteId(Integer clienteId);
}
