package br.com.arq.cesta_de_compras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PoliticaController {
	@GetMapping("/termos-de-uso")
	public String termosDeUso() {
		return "termos";
	}
	@GetMapping("/politica-de-privacidade")
	public String politicaDePrivacidade() {
		return "politica";
	}
}
