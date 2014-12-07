package squeak.desktop.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import squeak.desktop.model.BD;
import squeak.desktop.model.Mensagem;
import squeak.desktop.model.Usuario;

public class MensagemDAO {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
	private BD db;

	public MensagemDAO(BD db) throws Exception {

		if (db == null)
			throw new Exception("Acesso ao banco nulo");

		this.db = db;
	}

	public void criar(Mensagem m) throws Exception {

		if (m == null)
			throw new Exception("Mensagem nula");

		try {
			db.execComando(String.format(
					"INSERT INTO mensagem VALUES (null,'%s', '%s' ,%d)",
					m.getTexto(), sdf.format(m.getData()), m.getUsuario().getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Mensagem> buscaMensagens() throws Exception {

		ResultSet rs = null;
		List<Mensagem> mensagens = new ArrayList<Mensagem>();

		try {
			rs = db.execConsulta("SELECT u.id as usuid, u.nome as usunome, u.sexo as ususexo, m.texto as mentexto,"
					+ " m.data as mendata, m.id as menid FROM usuario u, mensagem m WHERE m.usuario = u.id");
		} catch (Exception e) {
		}

		if (rs != null) {

			while (rs.next()) {
				Usuario u = new Usuario(rs.getInt("usuid"),
						rs.getString("usunome"), null, null, rs.getString(
								"ususexo").charAt(0), null, null);

				Mensagem m = new Mensagem(rs.getInt("menid"),
						rs.getString("mentexto"), rs.getTime("mendata"), u);

				mensagens.add(m);
			}

		}

		return mensagens;
	}
        
        public void deletarMensagem(Integer id) throws Exception {
            
            if(id == null)
                throw new Exception("Mensagem nula");
            
            try {
		db.execComando("DELETE FROM mensagem WHERE id = " + id);
		} catch (Exception e) {
                    e.printStackTrace();
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
