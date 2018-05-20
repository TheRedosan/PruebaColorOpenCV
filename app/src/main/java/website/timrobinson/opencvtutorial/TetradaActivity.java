package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class TetradaActivity extends Base{

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvColor1;
    TextView tvColor2;
    TextView tvColor3;
    TextView color1;
    TextView color2;
    TextView color3;

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        //El primer color que obtenemos es el complementario de la muestra
        float hue=0;
        float analogoTetrada2=0;
        hue = tHSL[0];

        if (tHSL[0]+180.0 > 360){
            tHSL[0] = (tHSL[0] + 180) % 360;
        }else{
            tHSL[0]+= 180;
        }

        int tetrada1 = ColorUtils.HSLToColor(tHSL);
        String rgb1 = Color.red(tetrada1)+", "+Color.green(tetrada1)+", "+Color.blue(tetrada1);
        System.out.println(rgb1);

        //A continuación, partiendo otra vez de la muestra, dejamos un espacio en blanco en el círculo
        //cromático, y obtenemos el segundo color a evaluar

        tHSL[0] = hue;

        if (tHSL[0]+ 60.0 > 360){
            tHSL[0] = (tHSL[0] + 60) % 360;
        }else{
            tHSL[0]+= 60.0;
        }
        analogoTetrada2 = tHSL[0];

        int tetrada2 = ColorUtils.HSLToColor(tHSL);
        String rgb2 = Color.red(tetrada2)+", "+Color.green(tetrada2)+", "+Color.blue(tetrada2);
        System.out.println(rgb2);

        //Finalmente, hacemos el complementario del obtenido en el paso anterior
        tHSL[0] = analogoTetrada2;

        if (tHSL[0]+180.0 > 360){
            tHSL[0] = (tHSL[0] + 180) % 360;
        }else{
            tHSL[0]+= 180;
        }

        int tetrada3 = ColorUtils.HSLToColor(tHSL);
        String rgb3 = Color.red(tetrada3)+", "+Color.green(tetrada3)+", "+Color.blue(tetrada3);

        muestra.setTextColor(tetrada1);

        color1.setBackgroundColor(tetrada1);
        color1.setText(Color.red(tetrada1)+", "+Color.green(tetrada1)+", "+Color.blue(tetrada1));
        color1.setTextColor(tetrada2);

        color2.setBackgroundColor(tetrada2);
        color2.setText(Color.red(tetrada2)+", "+Color.green(tetrada2)+", "+Color.blue(tetrada2));
        color2.setTextColor(tetrada3);

        System.out.println(tetrada3);
        color3.setBackgroundColor(tetrada3);
        color3.setText(Color.red(tetrada3)+", "+Color.green(tetrada3)+", "+Color.blue(tetrada3));
        color3.setTextColor(Color.rgb((int)r, (int)g, (int)b));

        return false;
    }

}
