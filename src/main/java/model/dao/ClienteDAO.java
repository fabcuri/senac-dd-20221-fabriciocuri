package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Endereco;

public class ClienteDAO implements BaseDAO<Cliente>{
	
	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME,CPF,ID_ENDERECO)" 
					+ "VALUES (?, ?, ?);";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setInt(3, novoCliente.getEndereco().getId());
		
			
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novoCliente.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir cliente. Causa:" + e.getMessage());
		}
		
		return novoCliente;
}
	
	public boolean atualizar(Cliente cliente) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE "
					+" SET NOME=?, CPF=?, ID_ENDERECO=? "
					+" WHERE ID=?";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getEndereco().getId());
			stmt.setInt(4, cliente.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	public boolean excluir(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM CLIENTE "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover cliente. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}


	public Cliente consultar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Cliente> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	}
