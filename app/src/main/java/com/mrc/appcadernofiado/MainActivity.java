package com.mrc.appcadernofiado;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.mrc.appcadernofiado.fragments.ClienteFragment;
import com.mrc.appcadernofiado.fragments.HomeFragment;
import com.mrc.appcadernofiado.fragments.PerfilFragment;
import com.mrc.appcadernofiado.fragments.ProdutoFragment;
import com.mrc.appcadernofiado.fragments.RelatorioFragment;
import com.mrc.appcadernofiado.fragments.VendaFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager gerenciador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        gerenciador = getSupportFragmentManager();
        if (savedInstanceState == null) {
            gerenciador.beginTransaction().replace(R.id.frame_layout, new VendaFragment()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_produto) {
            gerenciador.beginTransaction().replace(R.id.frame_layout, new ProdutoFragment()).commit();
        } else if (id == R.id.nav_cliente) {
            gerenciador.beginTransaction().replace(R.id.frame_layout, new ClienteFragment()).commit();
        } else if (id == R.id.nav_venda) {
            gerenciador.beginTransaction().replace(R.id.frame_layout, new VendaFragment()).commit();
        } else if (id == R.id.nav_relatorio) {
            gerenciador.beginTransaction().replace(R.id.frame_layout, new RelatorioFragment()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
