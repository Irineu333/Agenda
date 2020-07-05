package neu.agenda.alura;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MyBaseAdapter extends BaseAdapter
{

	private final Context contexto;
	private final List<Aluno> alunos = new ArrayList<>();

	@Override
	public int getCount()
	{

		return alunos.size();
	}

	@Override
	public Aluno getItem(int pos)
	{
		if (pos < alunos.size()) 
			return alunos.get(pos);
		else
			return null;
	}

	@Override
	public long getItemId(int pos)
	{
		if (pos < alunos.size())
			return alunos.get(pos).getId();
		else 
			return Aluno.ID_INVALIDO;
	}
	@Override
	public View getView(int pos, View view, ViewGroup viewGroup)
	{

		View viewCriada = LayoutInflater.from(contexto).inflate(R.layout.item_aluno, viewGroup, false);
		Aluno aluno = alunos.get(pos);

		TextView nome = viewCriada.findViewById(R.id.itemaluno_TextView_nome);
		TextView matricula = viewCriada.findViewById(R.id.itemaluno_TextView_matricula);

		nome.setText(aluno.getNome());
		matricula.setText(aluno.getMatricula());

		return viewCriada;
	}

	public void setLista(List<Aluno> alunos)
	{
		this.alunos.clear();
		this.alunos.addAll(alunos);
		notifyDataSetChanged();
	}
	public void clear()
	{
		alunos.clear();
		notifyDataSetChanged();
	}
	public void removerAluno(Aluno aluno)
	{
		alunos.remove(aluno);
		notifyDataSetChanged();
	}
	public MyBaseAdapter(Context contexto)
	{
		this.contexto = contexto; 
	}
	public MyBaseAdapter(Context contexto, List<Aluno> alunos)
	{
		this.contexto = contexto; 
		this.alunos.clear();
		this.alunos.addAll(alunos);
	}

}
