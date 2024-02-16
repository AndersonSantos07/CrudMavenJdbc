package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.UserPosJava;

public class AppTest {

	@Test
	public void initBanco() {
		UserPosDAO userPosDao = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();

		userPosJava.setEmail("joujou@gmail.com");
		userPosJava.setId(3L);
		userPosJava.setNome("joujou souza");

		userPosDao.salvar(userPosJava);

	}

	@Test
	public void initLista() {
		UserPosDAO userPosDAO = new UserPosDAO();

		try {

			List<UserPosJava> lista = userPosDAO.listarUsuarios();

			System.out.println("---------LISTA DE USUÁRIOS---------");
			for (UserPosJava user : lista) {

				System.out.println("ID: " + user.getId());
				System.out.println("Nome: " + user.getNome());
				System.out.println("Email: " + user.getEmail());
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Test
	public void initUsuario() {

		UserPosDAO userPosDAO = new UserPosDAO();
		try {
			UserPosJava usuario = userPosDAO.buscar(2L);

			System.out.println("---------USUARIO---------");
			System.out.println("ID: " + usuario.getId());
			System.out.println("Nome: " + usuario.getNome());
			System.out.println("Email " + usuario.getEmail());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	@Test
	public void atualizarUsuario() {
		UserPosDAO userPosDAO = new UserPosDAO();

		try {
			// 1 jeito
			UserPosJava usuarioAtualizado = new UserPosJava();

			usuarioAtualizado.setEmail("souzasouza@gmail.com");
			usuarioAtualizado.setNome("Souza Atualizado");
			usuarioAtualizado.setId(1L);
			userPosDAO.atualizar(usuarioAtualizado);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 2 jeito
			UserPosJava usuarioBanco = userPosDAO.buscar(2L);
			usuarioBanco.setNome("Outro atualizado");

			userPosDAO.atualizar(usuarioBanco);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void deletarUsuario() {

		UserPosDAO dao = new UserPosDAO();

		try {
			dao.deletar(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
