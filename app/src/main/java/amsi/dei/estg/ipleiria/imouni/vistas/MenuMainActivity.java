package amsi.dei.estg.ipleiria.imouni.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import amsi.dei.estg.ipleiria.imouni.R;

public class MenuMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    public static final String USERNAME = "USERNAME";
    public static final String TOKEN = "TOKEN";
    Fragment fragment = null;
    private String token;
    public static final String PREF_INFO_USER = "PREF_INFO_USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.ndOpen, R.string.ndClose);
        toggle.syncState();
        drawer.addDrawerListener(toggle);

        fragmentManager = getSupportFragmentManager();
        navigationView.setNavigationItemSelectedListener(this);
        carregarFragmento();

    }
    private void carregarFragmento(){
        fragment = new MainFragment();
        setTitle(R.string.main);
            if(fragment != null)
                fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
}
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        SharedPreferences sharedPrefInfoUser = getSharedPreferences(MenuMainActivity.PREF_INFO_USER, Context.MODE_PRIVATE);
        token = sharedPrefInfoUser.getString(TOKEN, null);
        Fragment fragment = null;
        switch (menuItem .getItemId()) {
            case R.id.nav_Inicial:
                setTitle(menuItem.getTitle());
                fragment = new MainFragment();
                break;
            case R.id.nav_PesquisaAnuncio:

                setTitle(menuItem.getTitle());
                fragment = new PesquisaFragment();
                break;
            case R.id.nav_addAnuncio:
                setTitle(menuItem.getTitle());
                fragment = new AddAnuncioFragment();
                break;
            case R.id.nav_editPerfil:
                if(token != null) {
                    fragment = new EditProfileFragment();
                    setTitle(menuItem.getTitle());
                    break;
                }else{
                    fragment = new LoginFragment();
                    setTitle(menuItem.getTitle());
                    break;
                }
            default:
                fragment = new MainFragment();
                setTitle(menuItem.getTitle());
        }
        if(fragment != null)
            fragmentManager.beginTransaction().replace(R.id.contentFragment, fragment).commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        carregarFragmento();
        return;
    }
}
