package controller;

import model.bo.ClienteBO;
import model.entity.Cliente;

public class ClienteController {

	public ClienteBO bo = new ClienteBO();
	
	public String salvar(Cliente novo) {
		String mensagem = "";
		
		if(novo == null) {
			mensagem = "Informe todos os dados do novo cliente";
		}else if(novo.getCpf().trim().length() != 11){
			try {
				Long.parseLong(novo.getCpf());
			} catch (NumberFormatException excecao) {
				mensagem = "CPF deve somente números \n";
			}
			
			mensagem += "CPF deve conter 11 dígitos";
		}
		
		if(mensagem.isEmpty()) {
			mensagem = bo.salvar(novo);
		}
		
		return mensagem;
	}
	
}
