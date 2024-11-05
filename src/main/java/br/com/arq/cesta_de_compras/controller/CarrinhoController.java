package br.com.arq.cesta_de_compras.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.entity.Usuarios;
//import br.com.arq.cesta_de_compras.repository.UsuariosRepository;
import br.com.arq.cesta_de_compras.service.ClienteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarrinhoController {
    @Autowired
    private ClienteService clienteService;
	//@Autowired
  //  private UsuariosRepository usuarioRepository;
	
    @GetMapping("/carrinho/novo")
    public String novoClienteForm(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuarios usuarioLogado = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
            UUID usuarioId = usuarioLogado.getId();
            if (clienteService.clienteExiste(usuarioId)) {
            	redirectAttributes.addFlashAttribute("message", "Cliente ja cadastrado " + usuarioLogado.getNome());
                return "redirect:/";
            } else {
                return "redirect:/carrinho/novo";
            }
        } else {
            return "redirect:/login/"; 
        }
    }
    
    
    @PostMapping("/carrinho/novo")
    public String salvarCliente(RedirectAttributes redirectAttributes, @ModelAttribute Clientes cliente, HttpSession session) {

        Usuarios usuarioLogado = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuarioLogado != null) {
        	redirectAttributes.addFlashAttribute("nome", usuarioLogado.getNome());
            // Usa o UUID do usuário logado para salvar o cliente
            clienteService.salvarCliente(cliente, usuarioLogado.getId());
            return "redirect:/";
        } else {
            return "redirect:/login/";
        }
    }
}
