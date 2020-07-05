package neu.agenda.alura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity
{

	private final String TITULO_APPBAR_ADD = "Novo Aluno";
	private final String TITULO_APPBAR_EDIT = "Editar Aluno";

	private AlunoDAO alunoDAO;
	private Aluno aluno = null;

	private EditText campoNome, campoMatricula, campoEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);



		//init
		alunoDAO = new AlunoDAO(this);
		campoNome = findViewById(R.id.activityadd_EditText_nome);
		campoMatricula = findViewById(R.id.activityadd_EditText_matricula);
		campoEmail = findViewById(R.id.activityadd_EditText_email);

		//putExtra
		Intent editarAluno = getIntent();
		if (editarAluno.hasExtra(AlunoDAO.KEY_ALUNO))
		{
			setTitle(TITULO_APPBAR_EDIT);
			aluno = (Aluno) editarAluno.getSerializableExtra(AlunoDAO.KEY_ALUNO);
			campoNome.setText(aluno.getNome());
			campoMatricula.setText(aluno.getMatricula());
			campoEmail.setText(aluno.getEmail());
		}
		else
		{
			setTitle(TITULO_APPBAR_ADD);
			aluno = new Aluno();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activityadd_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{

		if (item.getItemId() == R.id.menu_salvar)
		{
			alterarAluno();
			salvarAluno();
		}
		return super.onOptionsItemSelected(item);
	}

	private void alterarAluno()
	{
		aluno.setNome(campoNome.getText().toString());
		aluno.setMatricula(campoMatricula.getText().toString());
		aluno.setEmail(campoEmail.getText().toString());
	}

	private void salvarAluno()
	{


		if (aluno.isIdValido())
			alunoDAO.editarAluno(aluno);
		else
			alunoDAO.add(aluno);

		finish();
	}
}
