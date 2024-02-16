package conexaoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	// dados de configuração do banco de dados
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String password = "root";
	private static String user = "postgres";
	private static Connection connection = null;

	// BLOCO ESTÁTICO - quando a classe é carregada chama esse método.
	static {
		conectar();
	}

	// construtor que chama o método conectar
	public SingleConnection() {

		conectar();
	}

	private static void conectar() {

		try {

			// verificação para a conexão ser estabelecida apenas uma vez
			if (connection == null) {

				// carrega o driver do postgres
				Class.forName("org.postgresql.Driver");

				// estabelece a conexão
				connection = DriverManager.getConnection(url, user, password);

				System.out.println("conectou com sucesso!");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		return connection;

	}

}
