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

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triada);

        initViews();

        tvColor1 = (TextView) findViewById(R.id.tvColor1);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v,event);

        float hue=0;
        hue = tHSL[0];

        if (tHSL[0]+120.0 > 360){
            tHSL[0] = (tHSL[0] + 120) % 360;
        }else{
            tHSL[0]+= 120.0;
        }

        int rgbTriada1 = ColorUtils.HSLToColor(tHSL);

        tHSL[0] = hue;

        if (tHSL[0]-120.0 < 0){
            tHSL[0] = Math.abs(tHSL[0] - 120);
        }else{
            tHSL[0]-= 120.0;
        }

        int rgbTriada2 = ColorUtils.HSLToColor(tHSL);

        muestra.setTextColor(rgbTriada1);

        color1.setBackgroundColor(rgbTriada1);
        color1.setText(Color.red(rgbTriada1)+", "+Color.green(rgbTriada1)+", "+Color.blue(rgbTriada1));
        color1.setTextColor(rgbTriada2);

        color2.setBackgroundColor(rgbTriada2);
        color2.setText(Color.red(rgbTriada2)+", "+Color.green(rgbTriada2)+", "+Color.blue(rgbTriada2));
        color2.setTextColor(Color.rgb((int)r, (int)g, (int)b));

        return false;
    }

}

