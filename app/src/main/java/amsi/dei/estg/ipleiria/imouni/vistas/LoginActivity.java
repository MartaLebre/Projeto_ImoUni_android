package amsi.dei.estg.ipleiria.imouni.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import amsi.dei.estg.ipleiria.imouni.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnLogin_Click(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
        startActivity(intent);
        finish();
    }

}