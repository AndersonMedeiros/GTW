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

public class CadastroUsuario extends AppCompatActivity {
    private BancoDados bd;
    private SQLiteDatabase conn;

    private Usuario usuario;
    private UsuarioDao usuarioDao;

    private EditText edt_txt_cpf;
    private EditText edt_txt_nome;
    private EditText edt_txt_email;
    private EditText edt_txt_fone;
    private EditText edt_txt_senha;

    private Button bt_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
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

        bt_cadastrar = (Button)findViewById(R.id.bt_salvar);

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 inserir();
            }
        });
    }

    private void inserir(){
        try {
            usuario = new Usuario();
            usuario.setCpf(edt_txt_cpf.getText().toString());
            usuario.setNome(edt_txt_nome.getText().toString());
            usuario.setEmail(edt_txt_email.getText().toString());
            usuario.setFone(edt_txt_fone.getText().toString());
            usuario.setSenha(edt_txt_senha.getText().toString());
            usuarioDao.inserir(usuario);

            Intent it = new Intent(CadastroUsuario.this, Principal.class);
            it.putExtra("USUARIO",  usuario);
            finish();
            startActivity(it);
        } catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Erro ao inseir dados do usuário!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }

    }
}
