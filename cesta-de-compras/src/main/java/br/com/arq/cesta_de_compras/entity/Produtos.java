package br.com.arq.cesta_de_compras.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;


@Entity
public class Produtos {
    @Id
    private UUID id;  

    private String nome;
    private String descricao;
    private String categoria;
    private Double preco;
    private String imagem;
    
    public Produtos() {
      
        this.id = UUID.randomUUID();
    }

	public Produtos(UUID id, String nome, String descricao, String categoria, Double preco, String imagem) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
		this.imagem = imagem;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
    
}
