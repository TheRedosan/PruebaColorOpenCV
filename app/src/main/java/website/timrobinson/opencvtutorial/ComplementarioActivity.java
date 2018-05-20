package website.timrobinson.opencvtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ComplementarioActivity extends Base{

    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------
    TextView tvComplementario;
    TextView complementario;

    //--- METODOS -------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complementario);

        initViews();

        tvComplementario = (TextView) findViewById(R.id.tvComplementario);
        complementario = (TextView) findViewById(R.id.complementario);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        super.onTouch(v, event);

        if (tHSL[0]+180.0 > 360){
            tHSL[0] = (tHSL[0] + 180) % 360;
        }else{
            tHSL[0]+= 180;
        }

        int rgbComplementario = ColorUtils.HSLToColor(tHSL);

        complementario.setBackgroundColor(rgbComplementario);

        complementario.setText(Integer.toString(rgbComplementario));

        muestra.setTextColor(rgbComplementario);

        complementario.setText(Color.red(rgbComplementario)+", "+Color.green(rgbComplementario)+", "+Color.blue(rgbComplementario));
        complementario.setTextColor(Color.rgb((int)r, (int)g, (int)b));

        return false;
    }

}
