package neu.agenda.alura;
import android.support.annotation.NonNull;
import java.io.Serializable;

public class Aluno implements Serializable
{

	public static final int ID_INVALIDO=0;
	private int id=ID_INVALIDO;

	private String nome;
	private String matricula;
	private String email;

	public Aluno(String nome, String matricula, String email)
	{
		this.nome = nome;
		this.email = email;
		this.matricula = matricula;
	}

	public Aluno()
	{
		nome = null;
		matricula = null;
		email = null;
	}

	public int getId()
	{
		return id;
	}
	public boolean setId(int id)
	{
		if (this.id == 0)
		{
			this.id = id;
			return true;
		}
		else return false;
	}
	public boolean isIdValido()
	{
		if (getId() != ID_INVALIDO)
			return true;
		else 
			return false;
	}

	public String getNome()
	{
		return nome;
	}
	public String getMatricula()
	{
		return matricula;
	}

	public String getEmail()
	{
		return email;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setMatricula(String matricula)
	{
		this.matricula = matricula;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}


	@NonNull
	@Override
	public String toString()
	{

		return nome + " - " + matricula;
	}
}
