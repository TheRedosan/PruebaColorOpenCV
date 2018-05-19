package website.timrobinson.opencvtutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Carmen on 19/05/2018.
 */

public class Base extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_analogo:{
                Intent i = new Intent(this, AnalogaActivity.class);
                startActivity(i);
                }
            case R.id.menu_complementario:{
                Intent i = new Intent(this, ComplementarioActivity.class);
                startActivity(i);
                }
            case R.id.menu_complementariops:{
                Intent i = new Intent(this, ComplementariaPorSeparadoActivity.class);
                startActivity(i);
            }
            case R.id.menu_tetrada:{
                Intent i = new Intent(this, TetradaActivity.class);
                startActivity(i);
            }
            case R.id.menu_triada:{
                Intent i = new Intent(this, TriadaActivity.class);
                startActivity(i);
            }
            default:{
                return super.onOptionsItemSelected(item);}
        }
    }
}
