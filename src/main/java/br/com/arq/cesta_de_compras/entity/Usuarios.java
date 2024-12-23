package br.com.arq.cesta_de_compras.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuarios {
	@Id
	private UUID id;
	
	@Column(length = 100, unique = false)
	private String nome;
	
	@Column(unique = true, length = 255)
	private String email;
	
	@Column(length =250)
	private String senha;

	@Column(length =250)	
	private String status;
	
    @OneToOne(mappedBy = "usuario") 
    private Clientes cliente;
	
	public Usuarios() {
		this.id = UUID.randomUUID();
	}

	public Usuarios(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UUID getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
