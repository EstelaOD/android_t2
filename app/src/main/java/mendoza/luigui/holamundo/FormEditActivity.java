package mendoza.luigui.holamundo;

import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FormEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.update);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInApi();
            }
        });

        String Id = getIntent().getExtras().getString("CurrentPostId");
        String titulo = getIntent().getExtras().getString("CurrentPostUser");

        EditText id = (EditText) findViewById(R.id.txtId);
        id.setText(Id);
    }

    private void UpdateInApi() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPut httpPut = new HttpPut("http://api.lumenagile.com/posts");
        JSONObject dato = new JSONObject();

        try {

            dato.put("user", ((EditText)findViewById(R.id.txtUsuario)).getText().toString());
            dato.put("name", ((EditText)findViewById(R.id.txtTitle)).getText().toString());
            dato.put("content", ((EditText)findViewById(R.id.txtContent)).getText().toString());

            StringEntity entity = new StringEntity(dato.toString());
            httpPut.setEntity(entity);
            httpClient.execute(httpPut);

            Toast.makeText(getApplicationContext(), "Se actualizo correctamente", Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
