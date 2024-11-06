package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping("/carrinho")
	public String carregarCheckout (Model model, Integer id, HttpSession session) {

		Produtos produto = produtosRepository.findById(id).get(); 
		
		if (session.getAttribute("usuarioLogado") != null) {
		    ProdutosDTO produtosdto = new ProdutosDTO();
		    
		    produtosdto.setNome(produto.getNome());
		    produtosdto.setDescricao(produto.getDescricao());
		    produtosdto.setCategoria(produto.getCategoria());
		    produtosdto.setPreco(produto.getPreco());
		    
		    
		    model.addAttribute("produtoImagem", produto.getImagem());
		    model.addAttribute("produtos", produtosdto);
			return "checkout.html";
		} else {
			return "redirect:/login/";
		}
	}
}
