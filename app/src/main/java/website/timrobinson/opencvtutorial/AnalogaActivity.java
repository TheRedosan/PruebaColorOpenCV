package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.opencv.android.CameraBridgeViewBase;

public class AnalogaActivity extends Base implements View.OnTouchListener, CameraBridgeViewBase.CvCameraViewListener2 {

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvColor1;
    TextView tvColor2;
    TextView tvColor3;
    TextView tvColor4;
    TextView color1;
    TextView color2;
    TextView color3;
    TextView color4;

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analoga);

        initViews();

        tvColor1 = (TextView) findViewById(R.id.tvColor1);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        tvColor3 = (TextView) findViewById(R.id.tvColor3);
        tvColor4 = (TextView) findViewById(R.id.tvColor4);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
        color3 = (TextView) findViewById(R.id.color3);
        color4 = (TextView) findViewById(R.id.color4);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        float hue=0;
        hue = tHSL[0];

        if (tHSL[0]+30.0 > 360){
            tHSL[0] = (tHSL[0] + 30) % 360;
        }else{
            tHSL[0]+= 30.0;
        }

        int analogo1 = ColorUtils.HSLToColor(tHSL);
        String rgb1 = Color.red(analogo1)+", "+Color.green(analogo1)+", "+Color.blue(analogo1);
        System.out.println(rgb1);

        tHSL[0] = hue;

        if (tHSL[0]+ 60.0 > 360){
            tHSL[0] = (tHSL[0] + 60) % 360;
        }else{
            tHSL[0]+= 60.0;
        }

        int analogo2 = ColorUtils.HSLToColor(tHSL);
        String rgb2 = Color.red(analogo2)+", "+Color.green(analogo2)+", "+Color.blue(analogo2);
        System.out.println(rgb2);

        tHSL[0] = hue;

        if (tHSL[0]+ 90.0 > 360){
            tHSL[0] = (tHSL[0] + 90) % 360;
        }else{
            tHSL[0]+= 90.0;
        }

        int analogo3 = ColorUtils.HSLToColor(tHSL);
        String rgb3 = Color.red(analogo3)+", "+Color.green(analogo3)+", "+Color.blue(analogo3);
        System.out.println(rgb3);

        tHSL[0] = hue;

        if (tHSL[0]+ 120.0 > 360){
            tHSL[0] = (tHSL[0] + 120) % 360;
        }else{
            tHSL[0]+= 120.0;
        }

        int analogo4 = ColorUtils.HSLToColor(tHSL);
        String rgb4 = Color.red(analogo4)+", "+Color.green(analogo4)+", "+Color.blue(analogo4);
        System.out.println(rgb4);

        muestra.setTextColor(analogo1);

        color1.setBackgroundColor(analogo1);
        color1.setText(Color.red(analogo1)+", "+Color.green(analogo1)+", "+Color.blue(analogo1));
        color1.setTextColor(analogo2);

        color2.setBackgroundColor(analogo2);
        color2.setText(Color.red(analogo2)+", "+Color.green(analogo2)+", "+Color.blue(analogo2));
        color2.setTextColor(analogo3);

        System.out.println(analogo3);
        color3.setBackgroundColor(analogo3);
        color3.setText(Color.red(analogo3)+", "+Color.green(analogo3)+", "+Color.blue(analogo3));
        color3.setTextColor(analogo4);

        color4.setBackgroundColor(analogo4);
        color4.setText(Color.red(analogo4)+", "+Color.green(analogo4)+", "+Color.blue(analogo4));
        color4.setTextColor(Color.rgb((int)r, (int)g, (int)b));

        return false;
    }

}
