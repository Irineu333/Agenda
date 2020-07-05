package neu.agenda.alura;
import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO
{
	public static final String KEY_ALUNO = "aluno";
	public static final int ID_INVALIDO = Aluno.ID_INVALIDO;
	private final static List<Aluno> alunos = new ArrayList<>();
	private int count_id = 1;

	private final Context contexto;

	public void add(Aluno aluno)
	{
		aluno.setId(count_id);
		count_id++;
		alunos.add(aluno);
	}

	public Aluno getAlunoAtPosition(int position)
	{
		return alunos.get(position);
	}

	public Aluno getAlunoById(int id)
	{
		return searchAlunoById(id);
	}
	public boolean editarAluno(Aluno aluno)
	{
		Aluno findAluno = searchAluno(aluno);

		if (findAluno != null)
		{
			int pos = getPosition(findAluno);
			alunos.set(pos, aluno);
			return true;
		}
		return false;
	}
	private Aluno searchAluno(Aluno aluno)
	{
		if (aluno != null)
		{
			Aluno findAluno = searchAlunoById(aluno.getId());

			if (findAluno != null)
				return findAluno;
			else
				return null;
		}
		else return null;
	}
	private Aluno searchAlunoById(int id)
	{
		if (id != Aluno.ID_INVALIDO)
		{
			for (Aluno a: alunos)
			{
				if (a.getId() == id)
				{
					return a;
				}
			}
			Toast.makeText(contexto, "Aluno não encontrado", Toast.LENGTH_SHORT).show();
			return null;
		}
		else return null;
	}

	public String getName(int position)
	{
		return alunos.get(position).getNome();
	}
	public String getMatricula(int position)
	{
		return alunos.get(position).getMatricula();
	}

	public String getEmail(int position)
	{
		return alunos.get(position).getEmail();
	}

	public List<Aluno> getLista()
	{
		return new ArrayList<Aluno>(alunos);
	}
	public boolean removerAluno(int position)
	{

		if (alunos.size() > position)
		{
			alunos.remove(position);
			Toast.makeText(contexto, "Aluno removido", Toast.LENGTH_SHORT).show();
			return true;
		}
		else return false;
	}
	public boolean removerAluno(Aluno aluno)
	{
		Aluno findAluno = searchAluno(aluno);
		if (findAluno != null)
		{
			alunos.remove(findAluno);
			Toast.makeText(contexto, "Aluno removido", Toast.LENGTH_SHORT).show();
			return true;
		}
		else return false;
	}
	public int getPosition(Aluno aluno)
	{
		return alunos.indexOf(aluno);
	}
	public AlunoDAO(Context contexto)
	{
		this.contexto = contexto;
	}
}
