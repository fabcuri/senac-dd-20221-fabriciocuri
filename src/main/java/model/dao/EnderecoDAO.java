package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.Endereco;

public class EnderecoDAO {

	public Endereco inserir(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO(RUA, UF, CIDADE, NUMERO, CEP)" 
					+ "VALUES (?, ?, ?, ?, ?);";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoEndereco.getRua());
			stmt.setString(2, novoEndereco.getUf());
			stmt.setString(3, novoEndereco.getCidade());
			stmt.setString(4, novoEndereco.getNumero());
			stmt.setString(5, novoEndereco.getCep());
			
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novoEndereco.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço. Causa:" + e.getMessage());
		}
		
		return novoEndereco;
	}
	
	public boolean atualizar(Endereco endereco) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO "
					+" SET RUA=?, UF=?, CIDADE=?, NUMERO=?, CEP=? "
					+" WHERE ID=?";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getUf());
			stmt.setString(3, endereco.getCidade());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getCep());
			stmt.setInt(6, endereco.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar endereço. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	
	public boolean remover(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover endereço. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}
	
	public Endereco consultar(int id) {
		Endereco enderecoConsultado = null;
		//TODO implementar		
		//SELECT * FROM ENDERECO WHERE ID = ?
		
		return enderecoConsultado;
	}
	
	public ArrayList<Endereco> consultarTodos(){
		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();
		//TODO implementar
		//SELECT * FROM ENDERECO
		
		return enderecos;
	}
}
