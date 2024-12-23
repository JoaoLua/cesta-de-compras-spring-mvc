package br.com.arq.cesta_de_compras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.entity.Usuarios;

import br.com.arq.cesta_de_compras.repository.ProdutosRepository;
import br.com.arq.cesta_de_compras.repository.UsuariosRepository;

import br.com.arq.cesta_de_compras.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UsuariosRepository usuarioRepository;
	@Autowired
	private ProdutosRepository produtosRepository;

	@Autowired
	private UsuarioService service;

	@GetMapping("/")
	public String home(Model model) {
		List<Produtos> produtos = produtosRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("produtos", produtos);
		return "home";
	}

	@GetMapping("/login/")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuarios());
		return "index";
	}

	@PostMapping("/logar")
	public String logarUsuario(@ModelAttribute Usuarios usuario, Model model, BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession session) {
		try {
			Usuarios respUsuario = usuarioRepository.findByEmail(usuario.getEmail()).get();

			if (respUsuario == null) {
				throw new Exception("Usuario nao encontrado ...");

			}
			Boolean resp = service.checkPassword(usuario.getSenha(), respUsuario.getSenha());
			if (resp) {
				session.setAttribute("usuarioLogado", respUsuario);
				model.addAttribute("message", "logado");
				redirectAttributes.addFlashAttribute("message", "logado com sucesso");
				return "redirect:/";
			} else {
				model.addAttribute("message", "nao logado");
				redirectAttributes.addFlashAttribute("message", "Nao logado");
				return "index";
			}

		} catch (Exception ex) {
			model.addAttribute("message", "Login ou senha incorretos!");
			redirectAttributes.addFlashAttribute("message", "Error Nao ENcontrado");
		}
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
			usuario.setSenha(service.cryptPassword(usuario.getSenha()));
			usuario.setStatus("CREATED");
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
}