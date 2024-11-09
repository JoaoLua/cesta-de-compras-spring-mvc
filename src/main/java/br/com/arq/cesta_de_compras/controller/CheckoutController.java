package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.arq.cesta_de_compras.DTO.ClientesDTO;
import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.entity.Usuarios;

import br.com.arq.cesta_de_compras.repository.ProdutosRepository;
import br.com.arq.cesta_de_compras.service.ClienteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {
	@Autowired
	private ProdutosRepository produtosRepository;

    @Autowired
    private ClienteService clienteservice;
	
	@GetMapping("/carrinho")
	public String carregarCheckout (Model model, Integer id, HttpSession session) {
		Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");
		Produtos produto = produtosRepository.findById(id).get(); 
		
		if (usuario != null) {
			if (clienteservice.clienteExiste(usuario.getId())) {
			    ProdutosDTO produtosdto = new ProdutosDTO();
			    
			    produtosdto.setNome(produto.getNome());
			    produtosdto.setDescricao(produto.getDescricao());
			    produtosdto.setCategoria(produto.getCategoria());
			    produtosdto.setPreco(produto.getPreco());
			    
			    
			    model.addAttribute("produtoImagem", produto.getImagem());
			    model.addAttribute("produtos", produtosdto);
				return "checkout.html";
			} else {
				return "redirect:/carrinho/novo";
			}
		} else {
			return "redirect:/login/";
		}
	}
	@GetMapping("/carrinho/novo")
	public String cadastrarNovoCliente(Model model, HttpSession session) {
	    Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");
	    
	    if (usuario == null) {
	        // Redireciona para a página de login caso o usuário não esteja logado
	        return "redirect:/login/";
	    }
	    ClientesDTO clientesdto = new ClientesDTO();
	    model.addAttribute("clientesdto", clientesdto);
	    return "registroCliente";
	}
	@PostMapping("/carrinho/novo")
	public String salvarNovoCliente(@ModelAttribute ClientesDTO clienteDTO, HttpSession session) {
	    // Obtém o usuário logado a partir da sessão
	    Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");
	    
	    if (usuario == null) {
	        // Redireciona para a página de login caso o usuário não esteja logado
	        return "redirect:/login/";
	    }
	    
	    // Converte o DTO para a entidade Clientes usando o método toEntity
	    Clientes cliente = clienteservice.toEntity(clienteDTO, usuario);
	    
	    // Salva o cliente associado ao usuarioId
	    clienteservice.salvarCliente(cliente, usuario.getId());
	    
	    return "redirect:/";  // Redireciona para a página inicial após salvar
	}
}
