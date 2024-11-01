package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.arq.cesta_de_compras.entity.Usuarios;
import br.com.arq.cesta_de_compras.repository.UsuariosRepository;


@Controller
public class HomeController {
	@Autowired
    private UsuariosRepository usuarioRepository;
	
    @GetMapping("/")
    public String home() {
        return "home"; 
    }
    
    @GetMapping("/login/")
    public String login() { 
        return "index";
    }
    
    @GetMapping("/registro/")
    public String registro(Model model) { 
    	model.addAttribute("usuario", new Usuarios());
        return "registro";
    }
    
    @PostMapping("/registro/")
    public String criarConta(@ModelAttribute Usuarios usuario, RedirectAttributes redirectAttributes) {
    	try {
            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("usuarioNome", usuario.getNome());
            return "redirect:/sucesso";
		} catch (Exception e) {
	        return "registro"; 
		}
 	
    }
    
    @GetMapping("/sucesso")
    public String sucesso() { 
        return "sucesso";
    }
    
    @GetMapping("/detalhes/")
    public String detalhes() { 
        return "detail";
    }
}
