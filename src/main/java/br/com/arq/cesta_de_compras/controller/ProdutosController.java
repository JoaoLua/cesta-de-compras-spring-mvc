package br.com.arq.cesta_de_compras.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.arq.cesta_de_compras.DTO.ProdutosDTO;
import br.com.arq.cesta_de_compras.entity.Produtos;
import br.com.arq.cesta_de_compras.repository.ProdutosRepository;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public String mostrarProdutos(Model model) {
		List<Produtos> produtos = produtosRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("produtos", produtos);
		return "produtos/listaProdutos";
	}
	
    @GetMapping("/novoproduto")
    public String novoProduto(Model model) {
    	ProdutosDTO produtosdto = new ProdutosDTO();
    	model.addAttribute("produtosdto", produtosdto);
        return "produtos/adicionarProduto";
    }
    
    @PostMapping("/novoproduto")
    public String criarProduto(@ModelAttribute ProdutosDTO produtosdto, BindingResult result) {
        if (produtosdto.getFileImagem().isEmpty()) {
            result.addError(new FieldError("ProdutosDTO", "fileimagem", "Imagem inválida ou não inserida"));
        }
        if (result.hasErrors()) {
            System.out.println("Produto não gravado");
            return "redirect:/produtos";    
        }

        // Salvar imagem no servidor
        MultipartFile imagem = produtosdto.getFileImagem();
        Date criarData = new Date();
        String nomeimagem = criarData.getTime() + "_" + imagem.getOriginalFilename();

        try {
            // Caminho para a pasta `static/img`
            String diretorioUpload = "src/main/resources/static/img/";
            Path uploadPath = Paths.get(diretorioUpload);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = imagem.getInputStream()) {
                Files.copy(inputStream, uploadPath.resolve(nomeimagem), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        Produtos produto = new Produtos();
        produto.setNome(produtosdto.getNome());
        produto.setDescricao(produtosdto.getDescricao());
        produto.setCategoria(produtosdto.getCategoria());
        produto.setPreco(produtosdto.getPreco());
        produto.setImagem(nomeimagem);

        produtosRepository.save(produto);
        return "redirect:/produtos";
    }
	
	@GetMapping("/editar")
	public String editarProdutos (Model model, @RequestParam Integer id) {
		
		try {
	        Produtos produto = produtosRepository.findById(id).get();
	        
	        ProdutosDTO produtosdto = new ProdutosDTO();
	        produtosdto.setId(produto.getId());
	        produtosdto.setNome(produto.getNome());
	        produtosdto.setDescricao(produto.getDescricao());
	        produtosdto.setCategoria(produto.getCategoria());
	        produtosdto.setPreco(produto.getPreco());


	        // Adiciona o DTO ao modelo
	        model.addAttribute("produtosdto", produtosdto);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		
		return "produtos/editarProdutos";
	}
	
	@PostMapping("/editar")
	public String editarProduto(Model model,  @ModelAttribute ProdutosDTO produtosdto, BindingResult result) {
		try {
			Produtos produto = produtosRepository.findById(produtosdto.getId()).get();
			model.addAttribute("produtosdto", produto);
			
			if(result.hasErrors()) {
				return "produtos/listaProdutos";
			}
            
            if (!produtosdto.getFileImagem().isEmpty()) {
    			//deletar imagem antiga
                String diretorioUpload = "src/main/resources/static/img/";
                Path uploadPath = Paths.get(diretorioUpload + produto.getImagem());
                try {
                	Files.delete(uploadPath);
                } catch (Exception e) {
                	System.out.println("Exception: " + e.getMessage());
                }
                //salvar nova imagem
                MultipartFile imagem = produtosdto.getFileImagem();
                Date criarData = new Date();
                String nomeimagem = criarData.getTime() + "_" + imagem.getOriginalFilename();

                try (InputStream inputStream = imagem.getInputStream()) {
                    Files.copy(inputStream, uploadPath.resolve(nomeimagem), StandardCopyOption.REPLACE_EXISTING);
                }
                produto.setImagem(nomeimagem);
            }
            
            produto.setNome(produtosdto.getNome());
            produto.setDescricao(produtosdto.getDescricao());
            produto.setCategoria(produtosdto.getCategoria());
            produto.setPreco(produtosdto.getPreco());
            produtosRepository.save(produto);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
		return "redirect:/produtos";
	}

	
	@GetMapping("/deletar")
	public String deletarProdutos(@RequestParam Integer id) {
		try {
			
			Produtos produto = produtosRepository.findById(id).get();
			Path imagePath = Paths.get("src/main/resources/static/img/" + produto.getImagem());
			
			try {
				Files.delete(imagePath);
			} catch (Exception ex) {
				System.out.println("Exception: " + ex.getMessage());
			}
			
			produtosRepository.delete(produto);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception" + e.getMessage());
		}
		return "redirect:/produtos";
	}
}
