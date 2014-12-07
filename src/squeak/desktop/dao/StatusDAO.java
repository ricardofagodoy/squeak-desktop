package squeak.desktop.dao;

import java.sql.ResultSet;

import squeak.desktop.model.BD;
import squeak.desktop.model.Status;

public class StatusDAO {

	private BD db;

	public StatusDAO(BD db) throws Exception {

		if (db == null)
			throw new Exception("Acesso ao banco nulo");

		this.db = db;
	}

	public Status buscaStatus(Integer id) throws Exception {

		if (id == null)
			return null;

		ResultSet rs = null;

		try {
			rs = db.execConsulta("SELECT * FROM status WHERE id = " + id);
		} catch (Exception e) {
		}

		Status status = null;

		if (rs != null) {

			rs.first();

			status = new Status(id, rs.getString("tipo"));
		}

		return status;
	}
	
	public void fecharConexao() {
		try {
			db.fecharConexao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
