package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import model.entity.Endereco;
import model.entity.LinhaTelefonica;
import model.entity.Telefone;
import util.DateUtils;

public class LinhaTelefonicaDAO {
	public LinhaTelefonica inserir(LinhaTelefonica novaLinha) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO LINHA_TELEFONICA(ID_CLIENTE, ID_TELEFONE, DT_ATIVACAO, DT_DESATIVACAO)" 
				+ "VALUES (?, ?, ?, ?);";

		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setInt(1, novaLinha.getIdCliente());
			stmt.setInt(2, novaLinha.getTelefone().getId());
			stmt.setObject(3, novaLinha.getDataAtivacao());
			stmt.setObject(4, novaLinha.getDataDesativacao());

			stmt.execute();

			ResultSet chavesGeradas = stmt.getGeneratedKeys();
			if(chavesGeradas.next()) {
				novaLinha.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir linha telefônica. Causa:" + e.getMessage());
		}

		return novaLinha;
	}

	public boolean atualizar(LinhaTelefonica linhaTelefonica) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE LINHA_TELEFONICA "
				+" SET ID_CLIENTE=?, ID_LINHA_TELEFONICA=?, DT_ATIVACAO=?, DT_DESATIVACAO=? "
				+" WHERE ID=? ";

		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setInt(1, linhaTelefonica.getIdCliente());
			stmt.setInt(2, linhaTelefonica.getTelefone().getId());
			stmt.setObject(3, linhaTelefonica.getDataAtivacao());
			stmt.setObject(4, linhaTelefonica.getDataDesativacao());

			int linhasAfetadas = stmt.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar linha telefônica. Causa:" + e.getMessage());
		}

		return atualizou;
	}

	public boolean remover(int id) {
		boolean removeu = false;

		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM LINHA_TELEFONICA "
				+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setInt(1, id);
			removeu = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao remover linha telefônica. Causa:" + e.getMessage());
		}		

		return removeu;
	}

	public LinhaTelefonica consultar(int id) {
		LinhaTelefonica linhaTelefonicaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM LINHA_TELEFONICA "
				+" WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();

			if(resultado.next()) {
				linhaTelefonicaConsultada = construirDoResultSet(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar linha telefônica (id:" + id + ". Causa:" + e.getMessage());
		}

		return linhaTelefonicaConsultada;
	}

	private LinhaTelefonica construirDoResultSet(ResultSet resultado) throws SQLException {
		LinhaTelefonica linhaTelefonicaConsultada = new LinhaTelefonica();
		linhaTelefonicaConsultada.setId(resultado.getInt("id"));
		linhaTelefonicaConsultada.setIdCliente(resultado.getInt("id_cliente"));

		Date dataAtivacao = resultado.getDate("dt_ativacao");
		Date dataDesativacao = resultado.getDate("dt_desativacao");

		if(dataAtivacao != null) {
			linhaTelefonicaConsultada.setDataAtivacao(DateUtils.converterParaLocalDateTime(dataAtivacao));
		}

		if(dataDesativacao != null) {
			linhaTelefonicaConsultada.setDataDesativacao(DateUtils.converterParaLocalDateTime(dataDesativacao));
		}

		TelefoneDAO telefoneDAO = new TelefoneDAO();
		Telefone t = telefoneDAO.consultar(resultado.getInt("id_telefone"));
		linhaTelefonicaConsultada.setTelefone(t);

		return linhaTelefonicaConsultada;
	}

	public ArrayList<LinhaTelefonica> consultarTodos(){
		ArrayList<LinhaTelefonica> linhaTelefonicas = new ArrayList<LinhaTelefonica>();
		String sql = " SELECT * FROM LINHA_TELEFONICA ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = stmt.executeQuery();

			while(resultado.next()) {
				LinhaTelefonica linhaTelefonicaConsultada = construirDoResultSet(resultado);
				linhaTelefonicas.add(linhaTelefonicaConsultada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todas as linhas telefônicas. Causa:" + e.getMessage());
		}

		return linhaTelefonicas;
	}

	public ArrayList<LinhaTelefonica> consultarPorIdCliente(int idCliente) {
		ArrayList<LinhaTelefonica> linhasDoCliente = new ArrayList<LinhaTelefonica>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM LINHA_TELEFONICA "
				+" WHERE ID_CLIENTE=?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, idCliente);
			ResultSet resultado = stmt.executeQuery();
			while(resultado.next()) {
				LinhaTelefonica linha = construirDoResultSet(resultado);
				linhasDoCliente.add(linha);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar linhas telefônicas do cliente (idCliente:" + idCliente + ". Causa:" + e.getMessage());
		}

		return linhasDoCliente;
	}
}
