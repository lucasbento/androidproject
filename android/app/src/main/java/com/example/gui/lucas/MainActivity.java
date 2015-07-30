package com.example.gui.lucas;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class MainActivity extends AppCompatActivity {

    public EditText editNome, editTelefone, editEmail, editCurso, editSenha;
    public Button btnCadastrar;
    public User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User();

        editNome = (EditText) findViewById(R.id.nome);
        editTelefone = (EditText) findViewById(R.id.telefone);
        editEmail = (EditText) findViewById(R.id.email);
        editCurso = (EditText) findViewById(R.id.curso);
        editSenha = (EditText) findViewById(R.id.senha);

        btnCadastrar = (Button) findViewById(R.id.cadastrar);

        user.setNome(editNome.getText().toString());
        user.setTelefone(editTelefone.getText().toString());
        user.setEmail(editEmail.getText().toString());

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint(ApiService.API_URL)
                        .setConverter(new GsonConverter(new Gson()))
                        .setLog(new RestAdapter.Log() {
                            @Override
                            public void log(String message) {
                                Log.i("Requisição cadastro", message);
                            }
                        })
                        .build();
                ApiService service = restAdapter.create(ApiService.class);
                service.cadastrar(user, new Callback<User>() {
                    @Override
                    public void success(User user, Response response) {
                        Log.i("Cadastro:", "ok");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("Cadastro:", "falha");
                    }
                });
            }
        });

    }

}
