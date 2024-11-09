package br.com.arq.cesta_de_compras.DTO;

import java.util.UUID;

public class ClientesDTO {
	private Integer id;
	private String nome;
	private String cpf;
	private String telefone;
	private String cep;
	private String cidade;
	private String bairro;
	private String rua;
	private Integer numero;
	private String complemento;
	private UUID usuarioId;
	
	public ClientesDTO(Integer id, String nome, String cpf, String telefone, String cep, String cidade, String bairro,
			String rua, Integer numero, String complemento, UUID usuarioId) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.usuarioId = usuarioId;
	}

	public ClientesDTO() {
		super();
	}

	@Override
	public String toString() {
		return "ClientesDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + ", cep=" + cep
				+ ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", complemento="
				+ complemento + ", usuarioId=" + usuarioId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public UUID getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UUID usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
}
