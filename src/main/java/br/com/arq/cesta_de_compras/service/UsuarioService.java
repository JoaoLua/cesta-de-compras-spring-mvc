package br.com.arq.cesta_de_compras.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private String hashPassword;

 //criptografa
	public String cryptPassword(String password) {
		this.hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		return this.hashPassword;
	}

	//verificar se está criptografado
 	public Boolean checkPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
}
