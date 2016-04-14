package cr.ac.itcr.birdex;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        AboutFragment.OnFragmentInteractionListener,
        BirdInsertionFragment.OnFragmentInteractionListener,
        BirdEditFragment.OnFragmentInteractionListener,
        BirdDeleteFragment.OnFragmentInteractionListener,
        BirdShowFragment.OnFragmentInteractionListener,
        UserInsertionFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*
        * Invoca a cada uno de los fragments asociados a los items del dashboard*/

        //Mostrar especies de aves
        if (id == R.id.nav_add_species) {
            Fragment fragment = new BirdInsertionFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        //Editar especies de aves
        else if (id == R.id.nav_edit_species) {
            Fragment fragment = new BirdEditFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        //Borrar  especies de aves
        else if (id == R.id.nav_delete_species) {
            Fragment fragment = new BirdDeleteFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        //Mostrar especies de aves
        else if (id == R.id.nav_show_species) {
            Fragment fragment = new BirdShowFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        //Registrar usuarios
        else if (id == R.id.nav_reg_user) {
            Fragment fragment = new UserInsertionFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        //Acerca de
        else if (id == R.id.nav_about) {
            Fragment fragment = new AboutFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_dashboard, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
