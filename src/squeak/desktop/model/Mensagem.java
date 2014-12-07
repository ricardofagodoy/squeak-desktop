package squeak.desktop.model;

import java.util.Date;

public class Mensagem {

	private Integer id;
	private String texto;
	private Date data;
	private Usuario usuario;

	public Mensagem() {
	}

	public Mensagem(Integer id, String texto, Date data, Usuario usuario) {
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.usuario = usuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
