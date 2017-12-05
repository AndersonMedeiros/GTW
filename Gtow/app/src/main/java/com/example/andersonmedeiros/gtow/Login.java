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
import android.widget.TextView;

import com.example.andersonmedeiros.gtow.BancoDeDados.BancoDados;
import com.example.andersonmedeiros.gtow.modelo.Usuario;
import com.example.andersonmedeiros.gtow.modelo.UsuarioDao;

public class Login extends AppCompatActivity {
    BancoDados bd;
    SQLiteDatabase conn;

    UsuarioDao usuarioDao;

    private EditText edt_txt_cpf;
    private EditText edt_txt_senha;

    Button bt_entrar;

    TextView link_cadastro_usuario;
    TextView link_esq_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_txt_cpf = (EditText)findViewById(R.id.edt_txt_cpf);
        edt_txt_senha = (EditText)findViewById(R.id.edt_txt_senha);

        bt_entrar = (Button)findViewById(R.id.bt_entrar);

        link_cadastro_usuario = (TextView)findViewById(R.id.tv_link_cadastro);
        link_esq_senha = (TextView)findViewById(R.id.tv_link_esqSenha);

        try {
            bd = new BancoDados(this);
            conn = bd.getWritableDatabase();
            usuarioDao = new UsuarioDao(conn);

            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("BD criado com sucesso!");
            msg.setNeutralButton("OK", null);
            msg.show();
        } catch (SQLException ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setMessage("Falha ao criar o banco!" + ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }
        try {
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String cpf = null, senha = null;
                    if (edt_txt_cpf.getText().toString() != null && edt_txt_senha.getText().toString() != null) {
                        cpf = edt_txt_cpf.getText().toString();
                        senha = edt_txt_senha.getText().toString();
                        Usuario usuario;
                        if (cpf.equals("123") && senha.equals("admin")) {
                            Intent it = new Intent(Login.this, PrincipalAdm.class);
                            //finish();
                            startActivity(it);
                        } else if (usuarioDao.validacaoLogin(cpf, senha) != null) {
                            usuario = usuarioDao.validacaoLogin(cpf, senha);

                            Intent it = new Intent(Login.this, Principal.class);
                            it.putExtra("USUARIO", usuario);
                            finish();
                            startActivity(it);
                        } else {
                            AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);
                            msg.setMessage("Cpf ou senha Inválido!");
                            msg.setNeutralButton("OK", null);
                            msg.show();
                        }
                    } else {
                        AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);
                        msg.setMessage("Campos em branco!");
                        msg.setNeutralButton("OK", null);
                        msg.show();
                    }

            }
        });
        }catch (Exception ex){
            AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);
            msg.setMessage(ex.getMessage());
            msg.setNeutralButton("OK", null);
            msg.show();
        }

        link_cadastro_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, CadastroUsuario.class);
                startActivity(it);
            }
        });

        link_esq_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpf = edt_txt_cpf.getText().toString();
                String senha = edt_txt_senha.getText().toString();

                Usuario usuario = usuarioDao.validacaoLogin("01807086275", "91800213");
                AlertDialog.Builder msg = new AlertDialog.Builder(Login.this);
                msg.setMessage("CPF: "+cpf+"SENHA: "+senha+usuario.toString());
                msg.setNeutralButton("OK", null);
                msg.show();
            }
        });
    }
}
