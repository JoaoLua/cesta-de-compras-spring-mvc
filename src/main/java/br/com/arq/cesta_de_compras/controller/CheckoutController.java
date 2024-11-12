package br.com.arq.cesta_de_compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.arq.cesta_de_compras.DTO.ClientesDTO;
import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Clientes;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.entity.Usuarios;
import br.com.arq.cesta_de_compras.repository.ClientesRepository;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;
import br.com.arq.cesta_de_compras.service.ClienteService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CheckoutController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClienteService clienteservice;

    @GetMapping("/carrinho")
    public String carregarCheckout(Model model, Integer id, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login/";
        }

        Produtos produto = produtosRepository.findById(id).orElse(null);
        Clientes cliente = clientesRepository.findByUsuarioId(usuario.getId()).orElse(null);

        if (cliente == null) {
            return "redirect:/carrinho/novo";
        }

        if (clienteservice.clienteExiste(usuario.getId())) {
            ProdutosDTO produtosdto = new ProdutosDTO();
            produtosdto.setNome(produto.getNome());
            produtosdto.setDescricao(produto.getDescricao());
            produtosdto.setCategoria(produto.getCategoria());
            produtosdto.setPreco(produto.getPreco());

            model.addAttribute("produtoImagem", produto.getImagem());
            model.addAttribute("produtos", produtosdto);

            ClientesDTO clientesdto = new ClientesDTO();
            clientesdto.setNome(cliente.getNome());
            clientesdto.setCpf(cliente.getCpf());
            clientesdto.setTelefone(cliente.getTelefone());
            clientesdto.setCep(cliente.getCep());
            clientesdto.setCidade(cliente.getCidade());
            clientesdto.setBairro(cliente.getBairro());
            clientesdto.setRua(cliente.getRua());
            clientesdto.setNumero(cliente.getNumero());
            clientesdto.setComplemento(cliente.getComplemento());

            model.addAttribute("dados", clientesdto);

            return "checkout";
        } else {
            return "redirect:/carrinho/novo";
        }
    }

    @GetMapping("carrinho/editar")
    public String editarDadosCliente(Model model, Integer id, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login/";
        }

        Clientes cliente = clientesRepository.findByUsuarioId(usuario.getId()).orElse(null);

        if (cliente != null && clienteservice.clienteExiste(usuario.getId())) {
            ClientesDTO clientesdto = new ClientesDTO();
            clientesdto.setNome(cliente.getNome());
            clientesdto.setCpf(cliente.getCpf());
            clientesdto.setTelefone(cliente.getTelefone());
            clientesdto.setCep(cliente.getCep());
            clientesdto.setCidade(cliente.getCidade());
            clientesdto.setBairro(cliente.getBairro());
            clientesdto.setRua(cliente.getRua());
            clientesdto.setNumero(cliente.getNumero());
            clientesdto.setComplemento(cliente.getComplemento());
            clientesdto.setId(cliente.getId());

            model.addAttribute("dadosdto", clientesdto);
        }

        return "clienteEditarDados";
    }

    @PostMapping("carrinho/editar")
    public String salvarNovoDadosCliente(@ModelAttribute ClientesDTO clientesdto, BindingResult result, Model model, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login/";
        }

        Clientes cliente = clientesRepository.findByUsuarioId(usuario.getId()).orElse(null);

        if (result.hasErrors()) {
            return "clienteEditarDados";
        }

        if (cliente != null) {
            cliente.setNome(clientesdto.getNome());
            cliente.setCpf(clientesdto.getCpf());
            cliente.setTelefone(clientesdto.getTelefone());
            cliente.setCep(clientesdto.getCep());
            cliente.setCidade(clientesdto.getCidade());
            cliente.setBairro(clientesdto.getBairro());
            cliente.setRua(clientesdto.getRua());
            cliente.setNumero(clientesdto.getNumero());
            cliente.setComplemento(clientesdto.getComplemento());

            clienteservice.salvarCliente(cliente, usuario.getId());
        }

        return "redirect:/";
    }

    @GetMapping("/carrinho/novo")
    public String cadastrarNovoCliente(Model model, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login/";
        }

        ClientesDTO clientesdto = new ClientesDTO();
        model.addAttribute("clientesdto", clientesdto);
        return "registroCliente";
    }

    @PostMapping("/carrinho/novo")
    public String salvarNovoCliente(@ModelAttribute ClientesDTO clienteDTO, HttpSession session) {
        Usuarios usuario = (Usuarios) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login/";
        }

        Clientes cliente = clienteservice.toEntity(clienteDTO, usuario);
        clienteservice.salvarCliente(cliente, usuario.getId());
        return "redirect:/";
    }
}
