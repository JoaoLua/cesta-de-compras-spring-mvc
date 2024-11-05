package br.com.arq.cesta_de_compras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arq.cesta_de_compras.entity.Carrinho;
import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.entity.ItemCarrinho;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.repository.CarrinhoRepository;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    public Carrinho adicionarProduto(Integer clienteId, Integer produtoId, Integer quantidade) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);
        
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setCliente(new Clientes(clienteId));
        }

        Produtos produto = produtosRepository.findById(produtoId).get();
        ItemCarrinho item = new ItemCarrinho();
        
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setCarrinho(carrinho);

        carrinho.getItens().add(item);
        return carrinhoRepository.save(carrinho);
    }
}