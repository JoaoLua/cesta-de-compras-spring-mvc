package br.com.arq.cesta_de_compras.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.service.CarrinhoService;
import jakarta.servlet.http.HttpSession;



@RestController
@RequestMapping("/api/carrinho")
public class CarrinhoAPIController {

@Autowired
private CarrinhoService carrinhoService;

@PostMapping("/adicionar")

public ResponseEntity<Map<String, String>> adicionarProdutoAoCarrinho(@RequestParam Integer produtoId, @RequestParam int quantidade,HttpSession session) {
	Clientes cliente = (Clientes) session.getAttribute("clienteLogado");

	// Verifica se o usuário está logado
	if (cliente == null) {
		Map<String, String> response = new HashMap<>();
	    response.put("mensagem", "Usuário não está logado");
	    return ResponseEntity.status(403).body(response);
	}

	// Adiciona o produto ao carrinho
	carrinhoService.adicionarProduto(cliente.getId(), produtoId, quantidade);

	// Retorna a mensagem de sucesso
	Map<String, String> response = new HashMap<>();
	response.put("mensagem", "Produto adicionado ao carrinho com sucesso");
	return ResponseEntity.ok(response);
	}
}
	

