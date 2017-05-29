package brothersideas.mx.scrumteam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;

import brothersideas.mx.scrumteam.models.Usuario;
import brothersideas.mx.scrumteam.utils.ReadUsuarios;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
//    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Button btnLogin = (Button) findViewById(R.id.btnSignIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        try {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = settings.edit();
            String email = settings.getString("email", "");
            String password = settings.getString("password", "");
            if (!email.isEmpty() && !password.isEmpty()) {
                final Usuario usuario = getUsuario(email, password);
                if(usuario != null && usuario.getNombre() != null){
                    ejecutar(usuario);
                    super.onPause();
                    finish();
                    System.out.println("[Sesión recuperada]");
                }
            }
        } catch (Exception e) {
            Log.e("getUsuario Error", e.getMessage());
        }

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.txtEmail);

        mPasswordView = (EditText) findViewById(R.id.txtPassword);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    try {
                        attemptLogin();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.btnSignIn);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptLogin();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void ejecutar(Usuario usuario) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("nombre", usuario.getNombre());
        i.putExtra("paterno", usuario.getPaterno());
        i.putExtra("materno", usuario.getMaterno());
        i.putExtra("idUsuario", usuario.get_id());
        startActivity(i);
    }

    public Usuario getUsuario(String correo, String pass){
        ConnectServer server = new ConnectServer();
        server.execute(correo, pass);
        Usuario usuario = new Usuario();
        try {
            String json = server.get();
            System.out.println("json = " + json);
            if(json.equals("null")){
                return null;
            } else{
                Gson gson = new Gson();
                Type listType = new TypeToken<Usuario>(){}.getType();
                usuario = gson.fromJson(json, listType);
                try {
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("email", correo);
                    editor.putString("password", pass);
                    editor.putString("nombre", usuario.getNombre());
                    editor.putString("paterno", usuario.getPaterno());
                    editor.putString("materno", usuario.getMaterno());
                    editor.putString("id", usuario.get_id());
                    editor.apply();
                    System.out.println("[Sesión guardada]");
                } catch (Exception e) {
                    Log.e("getUsuario Error", e.getMessage());
                }
            }

        } catch (Exception e){
            Log.e("Error", "No se puede leer el JSON.");
        }

        return usuario;
    }

    public class ConnectServer extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... parameters) {
            String json = ReadUsuarios.logIn(parameters[0], parameters[1]);
            return json;
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() throws MalformedURLException {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Contraseña requerida");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Email Requeridos");
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError("Email invalido");
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            //focusView.requestFocus();
        }else{
            final Usuario usuario = getUsuario(mEmailView.getText().toString(), mPasswordView.getText().toString());
            System.out.println("usuario = " + usuario);
            if(usuario != null && usuario.getNombre() != null){
                ejecutar(usuario);
                super.onPause();
                finish();
            } else {
                Toast.makeText(this, "No se pudo acceder correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

}
