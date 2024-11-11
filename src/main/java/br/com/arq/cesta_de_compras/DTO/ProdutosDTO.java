package br.com.arq.cesta_de_compras.DTO;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProdutosDTO {
	private Integer id;
	private String nome;
	private String descricao;
	private String categoria;
	private Double preco;
	private Date data;
	private MultipartFile fileImagem;
	
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
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public MultipartFile getFileImagem() {
		return fileImagem;
	}
	public void setFileImagem(MultipartFile fileImagem) {
		this.fileImagem = fileImagem;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	

}