package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class CuadradoActivity extends Base {

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvColor1;
    TextView tvColor2;
    TextView tvColor3;
    TextView color1;
    TextView color2;
    TextView color3;
    int cuadrado1, cuadrado2, cuadrado3;

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetrada);

        initViews();

        tvColor1 = (TextView) findViewById(R.id.tvColor1);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        tvColor3 = (TextView) findViewById(R.id.tvColor3);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
        color3 = (TextView) findViewById(R.id.color3);
    }

    private void setText() {
        muestra.setTextColor(cuadrado1);

        color1.setBackgroundColor(cuadrado1);
        color1.setText(Color.red(cuadrado1)+", "+Color.green(cuadrado1)+", "+Color.blue(cuadrado1));
        color1.setTextColor(cuadrado2);

        color2.setBackgroundColor(cuadrado2);
        color2.setText(Color.red(cuadrado2)+", "+Color.green(cuadrado2)+", "+Color.blue(cuadrado2));
        color2.setTextColor(cuadrado3);

        System.out.println(cuadrado3);
        color3.setBackgroundColor(cuadrado3);
        color3.setText(Color.red(cuadrado3)+", "+Color.green(cuadrado3)+", "+Color.blue(cuadrado3));
        color3.setTextColor(Color.rgb((int)r, (int)g, (int)b));
    }

    //*** ALGORITMO ******************************************************************
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        float hue = tHSL[0];

        if (tHSL[0] + 90.0 > 360) {
            tHSL[0] = (tHSL[0] + 90) % 360;
        } else {
            tHSL[0] += 90.0;
        }

        cuadrado1 = ColorUtils.HSLToColor(tHSL);

        tHSL[0] = hue;

        if (tHSL[0]+180.0 > 360){
            tHSL[0] = (tHSL[0] + 180) % 360;
        }else{
            tHSL[0]+= 180.0;
        }

        cuadrado2 = ColorUtils.HSLToColor(tHSL);

        tHSL[0] = hue;

        if (tHSL[0]+270.0 > 360){
            tHSL[0] = (tHSL[0] + 270) % 360;
        }else{
            tHSL[0]+= 270.0;
        }

        cuadrado3 = ColorUtils.HSLToColor(tHSL);

        setText();
        return false;
    }

}
