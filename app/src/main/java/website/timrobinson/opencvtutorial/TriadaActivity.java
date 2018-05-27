package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class TriadaActivity extends Base{

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvColor1;
    TextView tvColor2;
    TextView color1;
    TextView color2;
    int[] tTriada;

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triada);

        initViews();

        tTriada = new int[2];

        tvColor1 = (TextView) findViewById(R.id.tvColor1);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
    }

    private void setText() {
        muestra.setTextColor(tTriada[0]);

        color1.setBackgroundColor(tTriada[0]);
        color1.setText(Color.red(tTriada[0])+", "+Color.green(tTriada[0])+", "+Color.blue(tTriada[0]));
        color1.setTextColor(tTriada[1]);

        color2.setBackgroundColor(tTriada[1]);
        color2.setText(Color.red(tTriada[1])+", "+Color.green(tTriada[1])+", "+Color.blue(tTriada[1]));
        color2.setTextColor(Color.rgb((int)r, (int)g, (int)b));

    }

    //*** ALGORITMO ******************************************************************
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v,event);

        for (int i = 0; i < 2; i++) {

            if (tHSL[0] + 120.0 > 360) {
                tHSL[0] = (tHSL[0] + 120) % 360;
            } else {
                tHSL[0] += 120.0;
            }

            tTriada[i] = ColorUtils.HSLToColor(tHSL);
        }

        setText();
        return false;
    }

}

