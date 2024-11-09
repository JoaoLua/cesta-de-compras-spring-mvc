package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;

@Controller
public class DetailController {
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping("/detail")
	public String editarProdutos (Model model, @RequestParam Integer id) {
		Produtos produto = produtosRepository.findById(id).get(); 
		
	    ProdutosDTO produtosdto = new ProdutosDTO();
	    
	    produtosdto.setId(produto.getId());
	    produtosdto.setNome(produto.getNome());
	    produtosdto.setDescricao(produto.getDescricao());
	    produtosdto.setCategoria(produto.getCategoria());
	    produtosdto.setPreco(produto.getPreco());
	    
	    
	    model.addAttribute("produtoImagem", produto.getImagem());
	    model.addAttribute("produtos", produtosdto);
		
		return "detail";
	}
}
