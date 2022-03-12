package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Endereco;
import model.entity.Telefone;

public class TelefoneDAO {
	public Endereco inserir(Telefone novoTelefone) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO TELEFONE(DDD,NUMERO,TIPO,ATIVO)" 
					+ "VALUES (?, ?, ?, ?);";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoTelefone.getDdd());
			stmt.setString(2, novoTelefone.getNumero());
			stmt.setTipo(3, novoTelefone.getTipo());
			stmt.setAtivo(4, novoTelefone.isAtivo());
		
	
		
			
			stmt.execute();
			
			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novoTelefone.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir telefone. Causa:" + e.getMessage());
		}
		
		return novoTelefone;
	}
	
	public boolean atualizar(Telefone telefone) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE TELEFONE "
					+" SET DDD=?, NUMERO=?, TIPO=?, ATIVO=? "
					+" WHERE ID=?";
		
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, telefone.getDdd());
			stmt.setString(2, telefone.getNumero());
			stmt.setTipo(3, telefone.getTipo());
			stmt.setAtivo(4, telefone.isAtivo());
			stmt.setInt(5, telefone.getId());
			
			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar telefone. Causa:" + e.getMessage());
		}
		
		return atualizou;
	}
	
	public boolean remover(int id) {
		boolean removeu = false;
		
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM TELEFONE "
					+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover telefone. Causa:" + e.getMessage());
		}		
		
		return removeu;
	}
}
