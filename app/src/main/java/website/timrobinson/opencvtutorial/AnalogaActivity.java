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
    TextView color1;
    TextView color2;
    int analogo1, analogo2, analogo3, analogo4;

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analoga);

        initViews();

        tvColor1 = (TextView) findViewById(R.id.tvColor1);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        color1 = (TextView) findViewById(R.id.color1);
        color2 = (TextView) findViewById(R.id.color2);
    }

    private void setText() {
        muestra.setTextColor(analogo1);

        color1.setBackgroundColor(analogo1);
        color1.setText(Color.red(analogo1) + ", " + Color.green(analogo1) + ", " + Color.blue(analogo1));
        color1.setTextColor(analogo2);

        color2.setBackgroundColor(analogo2);
        color2.setText(Color.red(analogo2) + ", " + Color.green(analogo2) + ", " + Color.blue(analogo2));
        color2.setTextColor(Color.rgb((int) r, (int) g, (int) b));


    }

    //*** ALGORITMO ******************************************************************
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        float hue = tHSL[0];

        if (tHSL[0] + 30.0 > 360) {
            tHSL[0] = (tHSL[0] + 30) % 360;
        } else {
            tHSL[0] += 30.0;
        }

        analogo1 = ColorUtils.HSLToColor(tHSL);

        tHSL[0] = hue;

        if (tHSL[0] - 30.0 < 0) {
            tHSL[0] = Math.abs(tHSL[0]);
        } else {
            tHSL[0] -= 30.0;
        }

        analogo2 = ColorUtils.HSLToColor(tHSL);

        setText();
        return false;
    }

}