package com.qunews.psd.rsi.noticiaapp.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qunews.psd.rsi.noticiaapp.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.NoticiaAPI;
import com.qunews.psd.rsi.noticiaapp.R;
import com.qunews.psd.rsi.noticiaapp.dominio.Usuario;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private static Context contexto;
    private EditText editLogin;
    private EditText editSenha;
    private Button btnLogar;
    private Button btnCadastrar;
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    NoticiaAPI noticiaAPI = connectiontoAPI.CreateRetrofit();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contexto = this;
        editLogin = (EditText) findViewById(R.id.edtLogin);
        editSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoMain = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intentGoMain);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editLogin.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                if (validarCampos(login, senha)) {
                    Call<Usuario> call = noticiaAPI.login(login, senha);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Response<Usuario> response, Retrofit retrofit) {
                            Usuario usuario = response.body();
                            if(response.isSuccess()){
                                Intent intentGoMain = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentGoMain);
                                Toast.makeText(LoginActivity.this, "Bem vindo "+usuario.getUsername() + "Token "+usuario.getToken(), Toast.LENGTH_SHORT).show();
                                finish();


                            }else{

                                editLogin.setError("");
                                editSenha.setError("");
                                Toast.makeText(LoginActivity.this, "Usuario ou senha invalida", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {

                        }
                    });



                }
            }



        });

    }
    public boolean validarCampos(String login, String senha){
        String msg = "Campo vazio";
        boolean result = true;
        if(login.equals("")){
            editLogin.setError(msg);
            result = false;
        }
        if(senha.equals("")){
            editSenha.setError(msg);
            result = false;
        }

        return result;
    }


}
