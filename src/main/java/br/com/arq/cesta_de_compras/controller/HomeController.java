package br.com.arq.cesta_de_compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.entity.Usuarios;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;
import br.com.arq.cesta_de_compras.repository.UsuariosRepository;


@Controller
public class HomeController {
	@Autowired
    private UsuariosRepository usuarioRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	
    @GetMapping("/")
    public String home(Model model) {
		List<Produtos> produtos = produtosRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("produtos", produtos);
    	return "home"; 
    }
    
	@GetMapping("/detail")
	public String editarProdutos (Model model, @RequestParam Integer id) {
		Produtos produto = produtosRepository.findById(id).get();
	        
	    ProdutosDTO produtosdto = new ProdutosDTO();
	    
	    produtosdto.setNome(produto.getNome());
	    produtosdto.setDescricao(produto.getDescricao());
	    produtosdto.setCategoria(produto.getCategoria());
	    produtosdto.setPreco(produto.getPreco());
	    
	    
	    model.addAttribute("produtoImagem", produto.getImagem());
	    model.addAttribute("produtos", produtosdto);
		
		return "detail";
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