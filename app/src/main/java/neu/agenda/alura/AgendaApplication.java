package neu.agenda.alura;

import android.app.Application;
import android.content.Context;

public class AgendaApplication extends Application
{
	public static Context contexto;

	@Override
	public void onCreate()
	{

		if (getApplicationContext() != null)
			contexto = getApplicationContext();
		testes();
		super.onCreate();
	}
	private void testes()
	{
		AlunoDAO alunoDAO = new AlunoDAO(this);
		alunoDAO.add(new Aluno("Irineu", "18382891", "mmmiri"));
		alunoDAO.add(new Aluno("Ganriel", "81727272", "mmmbiel"));
		alunoDAO.add(new Aluno("Estefany", "82822278", "mmmtef"));
	}
}
