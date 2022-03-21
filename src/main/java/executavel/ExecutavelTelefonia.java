package executavel;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.dao.ClienteDAO;
import model.dao.EnderecoDAO;
import model.dao.LinhaTelefonicaDAO;
import model.dao.TelefoneDAO;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.LinhaTelefonica;
import model.entity.Telefone;

public class ExecutavelTelefonia {

	public static void main(String[] argumentos) {
//		testarCrudEndereco();
//		testarCrudCliente();
//		
//		//TODO Exercicio
//		testarCrudTelefone();
		//testarCrudLinhaTelefonica();
		
		testarCadastroClienteComJOptionPane();
	}

	private static void testarCadastroClienteComJOptionPane() {
		String cpf = JOptionPane.showInputDialog("Informe o CPF (somente números)");
		String nome = JOptionPane.showInputDialog("Informe o nome completo");
		
		//TODO Violando o MVC...
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		
		ArrayList<Endereco> enderecos = enderecoDAO.consultarTodos();
		
		Endereco enderecoSelecionado = (Endereco) JOptionPane.showInputDialog(null, "Selecione um endereço",
											"Cadastro de novo cliente",
											JOptionPane.INFORMATION_MESSAGE,
											null,
											enderecos.toArray(),
											null);
		
		Cliente novoCliente = new Cliente(nome, cpf, enderecoSelecionado);
		novoCliente = clienteDAO.inserir(novoCliente);
		
		if(novoCliente.getId() > 0) {
			JOptionPane.showMessageDialog(null, "Novo cliente salvo!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao salvar cliente","Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void testarCrudTelefone() {
		// TODO Auto-generated method stub
		
	}

	private static void testarCrudEndereco() {
		//Violando o MVC, pois o main vai chamar a camada de modelo
		EnderecoDAO dao = new EnderecoDAO();
		Endereco novo = new Endereco("Anita Garibaldi", "300", "Florianópolis", "SC", "88320005");

		System.out.println("##Testes de CRUD de Endereço\n");
		
		System.out.println("#Teste de insert");
		dao.inserir(novo);

		int idDoNovoEndereco = novo.getId();
		System.out.println(idDoNovoEndereco > 0 ? "Salvou novo endereço" : "Não salvou");

		novo.setRua("Rua alterada");

		System.out.println("#Teste de update");
		boolean atualizou = dao.atualizar(novo);

		System.out.println(atualizou ? "Endereço atualizado" : "Endereço NÃO atualizado");

		System.out.println("#Teste de select");
		Endereco enderecoConsultado = dao.consultar(idDoNovoEndereco);
		System.out.println("Endereço consultado por id (" + idDoNovoEndereco + "): " 
				+ enderecoConsultado.toString());

		System.out.println("#Teste de delete");
		dao.remover(novo.getId());

		System.out.println("#Teste de select");
		Endereco enderecoConsultadoAposRemocao = dao.consultar(idDoNovoEndereco);
		System.out.println(enderecoConsultadoAposRemocao == null ? "Endereço não existe (ok)" : 
				"Endereço não foi devidamente excluído");
	}

	private static void testarCrudCliente() {
		//Violando o MVC, pois o main vai chamar a camada de modelo
		ClienteDAO dao = new ClienteDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Cliente novo = new Cliente("Romário Souza", "21200022235", enderecoDAO.consultar(2), null);

		System.out.println("##Testes de CRUD de Cliente\n");
		System.out.println("#Teste de insert");
		dao.inserir(novo);

		int idDoNovoCliente = novo.getId();
		System.out.println(idDoNovoCliente > 0 ? "Salvou novo cliente" : "Não salvou");

		novo.setNome("Romário Souza 11");

		System.out.println("#Teste de update");
		boolean atualizou = dao.atualizar(novo);

		System.out.println(atualizou ? "Cliente atualizado" : "Cliente NÃO atualizado");

		System.out.println("#Teste de select");
		Cliente clienteConsultado = dao.consultar(idDoNovoCliente);
		System.out.println("Cliente consultado por id (" + idDoNovoCliente + "): " 
				+ clienteConsultado.toString());

		System.out.println("#Teste de delete");
		dao.remover(novo.getId());

		System.out.println("#Teste de select");
		Cliente clienteConsultadoAposRemocao = dao.consultar(idDoNovoCliente);
		System.out.println(clienteConsultadoAposRemocao == null ? "Cliente não existe (ok)" : 
				"Cliente não foi devidamente excluído");
	}

	private static void testarCrudLinhaTelefonica() {
		LinhaTelefonicaDAO linhaDAO = new LinhaTelefonicaDAO();
		TelefoneDAO telefoneDAO = new TelefoneDAO();
		
		ArrayList<Telefone> telefones = telefoneDAO.consultarTodos();
		
		//Cria simplesmente linhas disponíveis (1 para cada telefone criado)
		for(Telefone t: telefones) {
			LinhaTelefonica novaLinha = new LinhaTelefonica();
			novaLinha.setTelefone(t);
			linhaDAO.inserir(novaLinha);
		}
		
		System.out.println("#Teste de select");
		LinhaTelefonica linha1 = linhaDAO.consultar(1);
		System.out.println("Linha consultada por id (" + 1 + "): " 
				+ linha1.toString());

		
		System.out.println("#Teste de select (todos)");
		ArrayList<LinhaTelefonica> linhas = linhaDAO.consultarTodos();
		System.out.println("Quantidade de linhas: " + linhas.size());
		
	}
}
