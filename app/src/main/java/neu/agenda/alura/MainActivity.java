package neu.agenda.alura;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
	private final String TITULO_APPBAR = "Lista de alunos";
	private AlunoDAO alunoDAO;
	private ListView listView;
	private MyBaseAdapter baseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
		setTitle(TITULO_APPBAR);

		//init
		alunoDAO = new AlunoDAO(this);
		listView = findViewById(R.id.activitymain_ListView1);
		registerForContextMenu(listView);
		final FloatingActionButton fab_btn = findViewById(R.id.activitymain_FAB);

		if (savedInstanceState == null)
		{
			//sem estado anterior
		}

		baseAdapter = new MyBaseAdapter(this, alunoDAO.getLista());
		listView.setAdapter(baseAdapter);

		//listeners
		fab_btn.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view)
				{
					startActivity(new Intent(MainActivity.this, AddActivity.class));
				}
			});
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id)
				{
					Aluno aluno = (Aluno) adapterView.getItemAtPosition(pos);

					editarAluno(aluno);
				}

			});
    }

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, view, menuInfo);
		getMenuInflater().inflate(R.menu.activitymain_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{

		AdapterView.AdapterContextMenuInfo adapterMenu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		Aluno aluno = baseAdapter.getItem(adapterMenu.position);

		switch (item.getItemId())
		{
			case R.id.menu_abrir:
				break;
			case R.id.menu_editar:
				editarAluno(aluno);
				break;
			case R.id.menu_remover:
				dialogRemoverAluno(aluno);
				break;
			default:
				break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		refreshAdapter();
	}

	private void dialogRemoverAluno(final Aluno aluno)
	{

		new android.app.AlertDialog.Builder(this)
			.setTitle("Remover " + aluno.getNome())
			.setMessage("Matricula: " + aluno.getMatricula()
						+ "\nEmail: " + aluno.getEmail() +
						"\n\nDeseja realmente remover esse aluno?")
			.setPositiveButton("Sim", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					removerAluno(aluno);
				}
			})
			.setNegativeButton("Não", null)
			.show();
	}

	private void removerAluno(Aluno aluno)
	{

		if (alunoDAO.removerAluno(aluno)) 
			baseAdapter.removerAluno(aluno);

	}
	private void refreshAdapter()
	{
		baseAdapter.setLista(alunoDAO.getLista());
	}
	private void editarAluno(Aluno aluno)
	{
		startActivity(new Intent(MainActivity.this, AddActivity.class).putExtra(alunoDAO.KEY_ALUNO, aluno));
	}
}


