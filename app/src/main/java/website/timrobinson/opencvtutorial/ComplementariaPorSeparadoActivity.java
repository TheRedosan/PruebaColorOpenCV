package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ComplementariaPorSeparadoActivity extends Base{

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvColor1;
    TextView tvColor2;
    TextView color1;
    TextView color2;
    int complementarioPs1, complementarioPs2;

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

    private void setText() {
        muestra.setTextColor(complementarioPs1);

        color1.setBackgroundColor(complementarioPs1);
        color1.setText(Color.red(complementarioPs1)+", "+Color.green(complementarioPs1)+", "+Color.blue(complementarioPs1));
        color1.setTextColor(complementarioPs2);

        color2.setBackgroundColor(complementarioPs2);
        color2.setText(Color.red(complementarioPs2)+", "+Color.green(complementarioPs2)+", "+Color.blue(complementarioPs2));
        color2.setTextColor(Color.rgb((int)r, (int)g, (int)b));
    }

    //*** ALGORITMO ******************************************************************
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        if (tHSL[0]+180.0 > 360){
            tHSL[0] = (tHSL[0] + 180) % 360;
        }else{
            tHSL[0]+= 180;
        }

        float complementario = tHSL[0];

        if ( tHSL[0] + 30.0 > 360) {
            tHSL[0] = ( tHSL[0] + 30) % 360;
        } else {
            tHSL[0] += 30.0;
        }

        complementarioPs1 = ColorUtils.HSLToColor(tHSL);

        tHSL[0] = complementario;

        if (tHSL[0] - 30.0 < 0) {
            tHSL[0] = Math.abs(tHSL[0]);
        } else {
            tHSL[0] -= 30.0;
        }

        complementarioPs2 = ColorUtils.HSLToColor(tHSL);

        setText();
        return false;
    }

}

