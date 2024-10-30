package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;

@Controller
public class ProdutosController {
    @Autowired
    private ProdutosRepository produtosRepository;

    // Exibe o formulário para criação de um novo produto
    @GetMapping("/produtos/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new Produtos());
        return "produto-form";
    }

    // Processa o envio do formulário e salva no banco de dados
    @PostMapping("/produtos/novo")
    public String salvarProduto(@ModelAttribute Produtos produto) {
        produtosRepository.save(produto);  // Salva o produto usando JPA
        return "redirect:/produtos";
    }

    // Exibe todos os produtos
    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtosRepository.findAll());  // Busca todos os produtos
        return "produto-list";
    }
}

