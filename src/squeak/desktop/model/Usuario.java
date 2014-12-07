package squeak.desktop.model;

public class Usuario {
	
	public Usuario() {}
	
	public Usuario(Integer id) {
		this.id = id;
	}
	
	public Usuario(Integer id, String nome, String senha, String email, char sexo, Integer idade, Status status) {
		this.email = email;
		this.id = id;
		this.idade = idade;
		this.nome = nome;
		this.senha = senha;
		this.sexo = sexo;
		this.status = status;
	}
	
	private Integer id;
	private String nome;
	private String senha;
	private String email;
	private char sexo;
	private Integer idade;
	private Status status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
