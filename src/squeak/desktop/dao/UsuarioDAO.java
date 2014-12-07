package squeak.desktop.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import squeak.desktop.model.BD;
import squeak.desktop.model.Status;
import squeak.desktop.model.Usuario;

public class UsuarioDAO {

	private BD db;

	public UsuarioDAO(BD db) throws Exception {

		if (db == null)
			throw new Exception("Acesso ao banco nulo");

		this.db = db;
	}

	public void criar(Usuario u) throws Exception {

		if (u == null)
			throw new Exception("Usuario nulo");

		try {
			db.execComando(String
					.format("INSERT INTO usuario VALUES (null,'%s','%s','%s','%c',%d,%d)",
							u.getNome(), u.getSenha(), u.getEmail(),
							u.getSexo(), u.getIdade(), u.getStatus().getId()));
		} catch (Exception e) {
		}
	}

	public Usuario procuraUsuario(String nome, String senha) {

		if (nome == null || senha == null)
			return null;

		ResultSet rs = null;

		try {
			rs = db.execConsulta(String.format(
					"SELECT * FROM usuario WHERE nome = '%s' AND senha = '%s'",
					nome, senha));

			if (rs.first()) {

				StatusDAO statusDAO = new StatusDAO(new BD(
						"com.mysql.jdbc.Driver",
						"jdbc:mysql://127.0.0.1:3306/squeak", "root", "root"));
				Status status = statusDAO.buscaStatus(rs.getInt("status"));

				return new Usuario(rs.getInt("id"), rs.getString("nome"),
						rs.getString("senha"), rs.getString("email"), rs
								.getString("sexo").charAt(0),
						rs.getInt("idade"), status);
			}

		} catch (Exception e) {
		}

		return null;
	}
        
        public List<Usuario> buscaTodos() throws Exception {
            
            ResultSet rs = null;
            List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
                    rs = db.execConsulta("SELECT u.id as uid, u.nome, u.senha, u.email, "
                            + "u.sexo, u.idade , u.status, s.id as sid, s.tipo FROM usuario u, "
                            + "status s WHERE u.status = s.id");
		} catch (Exception e) {
		}

		if (rs != null) {

			while (rs.next()) {
                            
                                Status status = new Status(rs.getInt("sid"), rs.getString("tipo"));
                            
				Usuario u = new Usuario(rs.getInt("uid"),
                                                        rs.getString("nome"), 
                                                        null, 
                                                        rs.getString("email"), 
                                                        rs.getString("sexo").charAt(0), 
                                                        rs.getInt("idade"),
                                                        status);

				usuarios.add(u);
			}

		}

		return usuarios;
        }                

	public Usuario procuraUsuario(Integer id) {

		if (id == null)
			return null;

		ResultSet rs = null;

		try {
			rs = db.execConsulta(String.format(
					"SELECT * FROM usuario WHERE id = %d", id));

			rs.first();

			StatusDAO statusDAO = new StatusDAO(new BD("com.mysql.jdbc.Driver",
					"jdbc:mysql://127.0.0.1:3306/squeak", "root", "root"));
			Status status = statusDAO.buscaStatus(rs.getInt("status"));

			return new Usuario(rs.getInt("id"), rs.getString("nome"),
					rs.getString("senha"), rs.getString("email"), rs.getString(
							"sexo").charAt(0), rs.getInt("idade"), status);

		} catch (Exception e) {
		}

		return null;
	}
        
        public void alteraStatus(Integer id, Integer status) {
                       
                try {
                    db.execComando(String.format(
			"UPDATE usuario SET status = %d WHERE id = %d", status, id));
		} catch (Exception e) {
                    System.out.println(e.getMessage());
		}
        }

	public void fecharConexao() {
		try {
			db.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
