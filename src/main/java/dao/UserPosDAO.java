package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava user) {

		try {
			// 1- criar a query sql
			String sql = "insert into userposjava(id, nome, email) values(?,?,?)";
			// 2 - prepara a query sql
			PreparedStatement insert = connection.prepareStatement(sql);
			// 3 - seta os dados de acordo com a posição
			insert.setLong(1, user.getId());
			insert.setString(2, user.getNome());
			insert.setString(3, user.getEmail());
			insert.execute();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ev) {
				ev.printStackTrace();
			}
			e.printStackTrace();

		}
	}

	// como é um método que precisa pegar do banco todos os usuários, ele retornará
	// uma lista de usuários
	public List<UserPosJava> listarUsuarios() {

		// instanciando a lista que receberá os dados do banco
		List<UserPosJava> usuarios = new ArrayList<UserPosJava>();

		try {

			String sql = "select * from userposjava";
			PreparedStatement selectAllUsers = connection.prepareStatement(sql);

			// executa a query e recebe a resposta
			ResultSet resultado = selectAllUsers.executeQuery();

			// verifica se há mais algum usuário no banco e retorna um boolean
			while (resultado.next()) {

				UserPosJava userPosJava = new UserPosJava();
				// na coluna id pega o dado do tipo Long ou String, etc..
				userPosJava.setId(resultado.getLong("id"));
				userPosJava.setNome(resultado.getString("nome"));
				userPosJava.setEmail(resultado.getString("email"));

				usuarios.add(userPosJava);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ev) {
				ev.printStackTrace();
			}
			e.printStackTrace();
		}

		return usuarios;

	}

	public UserPosJava buscar(Long id) {

		UserPosJava usuario = new UserPosJava();

		try {

			String sql = "select * from userposjava where id = " + id;
			PreparedStatement buscarId = connection.prepareStatement(sql);
			ResultSet resultado = buscarId.executeQuery();

			while (resultado.next()) {

				usuario.setId(resultado.getLong("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setEmail(resultado.getString("email"));

			}

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ev) {
				ev.printStackTrace();
			}
			e.printStackTrace();
		}

		return usuario;

	}

	public void atualizar(UserPosJava usuario) {

		try {
			String sql = "update userposjava set nome = ? where id = " + usuario.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.execute();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ev) {
				ev.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void deletar(Long id) {

		try {
			String sql = "delete from userposjava where id =" + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception ev) {
				ev.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
