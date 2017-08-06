package com.qunews.psd.rsi.noticiaapp.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.NoticiaAPI;
import com.qunews.psd.rsi.noticiaapp.R;
import com.qunews.psd.rsi.noticiaapp.dominio.Usuario;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CadastroActivity extends AppCompatActivity {

    private static Context contexto;
    private EditText editLogin;
    private EditText editEmail;
    private EditText editSenha;
    private EditText editConfirmarSenha;
    private Button btnCadastrar;
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    NoticiaAPI noticiaAPI = connectiontoAPI.CreateRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        contexto = this;

        editLogin = (EditText) findViewById(R.id.edtLoginCadastro);
        editEmail = (EditText) findViewById(R.id.edtEmail);
        editSenha = (EditText) findViewById(R.id.edtSenhaCadastro);
        editConfirmarSenha = (EditText) findViewById(R.id.edtConfirmarSenhaCadastro);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrarCadastro);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = editLogin.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                String confirmarsenha = editConfirmarSenha.getText().toString().trim();
                String email = editEmail.getText().toString().trim();

                Usuario usuario = new Usuario(login,senha,email);

                if(validarCampos(usuario,confirmarsenha)){
                    Call<Usuario> call = noticiaAPI.saveUsuario(usuario);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Response<Usuario> response, Retrofit retrofit) {

                            Usuario u = response.body();
                            if(response.isSuccess()){
                                Toast.makeText(CadastroActivity.this, "Cadastro efetuado com sucesso.", Toast.LENGTH_SHORT).show();

                            }else{

                                String json = null;
                                try {
                                    json = response.errorBody().string();
                                    Toast.makeText(CadastroActivity.this, json, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                json = json.replace("[","").replace("]","");
                                Gson gson = new GsonBuilder().create();
                                Usuario suer = new Usuario();
                                suer = gson.fromJson(json, Usuario.class);
                                validarAPI(suer);



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

    public void validarAPI(Usuario usuario){
        if(usuario.getUsername() != null){
            editLogin.setError("Usuario já existe");
        }
        if(usuario.getEmail() != null){
            editEmail.setError("Email invalido");
        }
    }

    public boolean validarCampos(Usuario usuario, String senhaConfirmar){
        String msg = "Campo vazio";
        boolean result = true;
        if(!usuario.getPassword().equals(senhaConfirmar)){
            editSenha.setError("Senha não confere");
            editConfirmarSenha.setError("Senha não confere");
            result = false;

        }
        if(usuario.getPassword().equals("")){
            editSenha.setError(msg);
            result = false;
        }
        if(usuario.getUsername().equals("")){
            editLogin.setError(msg);
            result = false;
        }
        if(usuario.getEmail().equals("")){
            editEmail.setError(msg);
            result = false;
        }

        if(senhaConfirmar.equals("")){
            editConfirmarSenha.setError(msg);
            result = false;
        }
        return result;

    }
}
