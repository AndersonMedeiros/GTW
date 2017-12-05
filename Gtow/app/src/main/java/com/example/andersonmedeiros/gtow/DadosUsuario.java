package com.example.andersonmedeiros.gtow;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Usuario;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

public class DadosUsuario extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Usuario usuario;
    private UsuarioDao usuarioDao;

    private EditText edt_txt_cpf;
    private EditText edt_txt_nome;
    private EditText edt_txt_email;
    private EditText edt_txt_fone;
    private EditText edt_txt_senha;

    private Button bt_alterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_usuario);

        try {
            bd = new BancoDados(this);
            conn = bd.getWritableDatabase();
            usuarioDao = new UsuarioDao(conn);

        } catch (SQLException ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Falha ao abrir o banco!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }

        edt_txt_cpf = (EditText)findViewById(R.id.edt_txt_cpf);
        edt_txt_nome = (EditText)findViewById(R.id.edt_txt_nome);
        edt_txt_email = (EditText)findViewById(R.id.edt_txt_email);
        edt_txt_fone = (EditText)findViewById(R.id.edt_txt_fone);
        edt_txt_senha = (EditText)findViewById(R.id.edt_txt_senha);

        bt_alterar = (Button)findViewById(R.id.bt_alterar);

        Bundle bundle = getIntent().getExtras();

        if((bundle!=null) && bundle.containsKey("USUARIO")){
            usuario = (Usuario) bundle.getSerializable("USUARIO");
            preencherDados();
        }

        bt_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alterar();
                try {
                    Bundle bundle = getIntent().getExtras();
                    AlertDialog.Builder msg = new AlertDialog.Builder(DadosUsuario.this);
                    msg.setMessage(bundle.get("USUARIO").toString());
                    msg.setNeutralButton("OK", null);
                    msg.show();

                    if ((bundle != null) && bundle.containsKey("USUARIO")) {
                        usuario = (Usuario) bundle.getSerializable("USUARIO");
                        AlertDialog.Builder ms = new AlertDialog.Builder(DadosUsuario.this);
                        ms.setMessage(usuario.toString());
                        ms.setNeutralButton("OK", null);
                        ms.show();
                        preencherDados();
                    }
                }catch (Exception ex){
                    AlertDialog.Builder msg = new AlertDialog.Builder(DadosUsuario.this);
                    msg.setMessage(ex.getMessage());
                    msg.setNeutralButton("OK", null);
                    msg.show();
                }

            }
        });
    }

    public void preencherDados(){
        edt_txt_cpf.setText(usuario.getCpf());
        edt_txt_nome.setText(usuario.getNome());
        edt_txt_email.setText(usuario.getEmail());
        edt_txt_fone.setText(usuario.getFone());
        edt_txt_senha.setText(usuario.getSenha());
    }

    private void alterar(){
        try {
            usuario = new Usuario();
            usuario.setCpf(edt_txt_cpf.getText().toString());
            usuario.setNome(edt_txt_nome.getText().toString());
            usuario.setEmail(edt_txt_email.getText().toString());
            usuario.setEmail(edt_txt_fone.getText().toString());
            usuario.setSenha(edt_txt_senha.getText().toString());
            usuarioDao.alterar(usuario);

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Dados alterados com sucesso!");
            msg.setNeutralButton("OK", null);
            msg.show();

            Intent it = new Intent(DadosUsuario.this, Principal.class);
            finish();
            startActivity(it);

        } catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Erro ao inseir dados do usuário!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }

    public void excluir() {
        try {
            usuarioDao.excluir(usuario.getCpf());

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Usuário excluído com sucesso!");
            msg.setNeutralButton("OK", null);
            msg.show();

            Intent it = new Intent(DadosUsuario.this,Principal.class);
            finish();
            startActivity(it);
        }catch (Exception ex) {
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Erro ao excluir o usuário!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
    }
}
