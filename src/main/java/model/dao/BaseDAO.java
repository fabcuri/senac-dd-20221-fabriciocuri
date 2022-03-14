package model.dao;

import java.util.ArrayList;

public interface BaseDAO<T> {
	public T inserir(T novoObjeto);
	public boolean atualizar(T novoObjeto);
	public boolean excluir(int id);
	public T consultar(int id);
	public ArrayList<T> consultarTodos();

}
