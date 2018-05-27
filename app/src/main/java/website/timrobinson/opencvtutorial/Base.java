package website.timrobinson.opencvtutorial;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Carmen on 19/05/2018.
 */

public class Base extends AppCompatActivity implements View.OnTouchListener, CameraBridgeViewBase.CvCameraViewListener2 {


    //--- VARIABLES Y CONSTANTES -------------------------------------------------------------------

    //TAG para los Logs.
    private static final String TAG = "OCVSample::Activity";

    //Objeto que permite a OpenCV interaccionar con la camara.
    //Necesita implementarse un CvCameraViewListener
    protected CameraBridgeViewBase OpenCvCamara;

    //El objeto Mat es un vector con los valores numericos de una imagen y el conenedor basico para OpenCV
    //Mat que contendrá el fotograma que envía la camara.
    private Mat fotograma;

    //TV para las coordenadas donde se ha pulsado.
    TextView tvTouchCoordenadas;

    //TV para el color identificado.
    TextView tvMuestra;
    TextView muestra;

    //TV para la muestra del color.
    TextView tvTouchColor;

    //Contendrán los colores, dependiendo del espacio de color en el que se encuentren.
    private Scalar tColorHSV;
    private Scalar tColorRGBa;

    //Contendran las coordenadas sobre las que se ha pulsado en la pantalla.
    double x = -1;
    double y = -1;

    //Color HSL
    float[] tHSL;

    //Colores RGB
    double r, g, b;

    //Se carga la librería de OpenCV
    static {
        if (!OpenCVLoader.initDebug())
            Log.d("MAL", "No se ha podido cargar OpenCV");
        else
            Log.d("BIEN", "OpenCV se ha cargado");
    }

    //--- METODOS -------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    //Cada fotograma de la camara, se envía a este método. Este ya puede procesarse y
    //enviarse de vuelta para mostrarse en la pantalla
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        //Se guarda el fotograma en la variable global para que se procese.
        //Está en espacio RGBA.
        fotograma = inputFrame.rgba();

        //Una vez guardada, se vuelve a enviar ya que no necesita ser procesada.
        return fotograma;
    }

    public void initViews(){
        //Mantiene la pantalla encendida.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Se inician los TV.
        tvTouchCoordenadas = (TextView) findViewById(R.id.tvCoordenadas);
        tvTouchColor = (TextView) findViewById(R.id.tvColor);
        tvMuestra = (TextView) findViewById(R.id.tvMuestra);
        muestra = (TextView) findViewById(R.id.muestra);

        //Inicializa la camara de OpenCV.
        OpenCvCamara = (CameraBridgeViewBase) findViewById(R.id.opencvCamara);
        OpenCvCamara.setVisibility(SurfaceView.VISIBLE);
        OpenCvCamara.setCvCameraViewListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //--- COORDENADAS DE LA PANTALLA ---

        //Total de columnas y filas del fotograma.
        int cols = fotograma.cols();
        int rows = fotograma.rows();

        //Coge la altura maxima y minima de la camara.
        double yBajo = (double) OpenCvCamara.getHeight() * 0.2401961;
        double yAlto = (double) OpenCvCamara.getHeight() * 0.7696078;

        //Coge el ancho total de la camara y lo parte del total de columnas.
        double xEscala = (double) cols / (double) OpenCvCamara.getWidth();

        //Parte el largo de la pantalla del total de filas.
        double yEscala = (double) rows / (yAlto - yBajo);

        //Se recuperan las coordenadas en las que se han pulsado en la pantalla.
        x = event.getX();
        y = event.getY();

        //A la Y se le resta la altura minima.
        y = y - yBajo;

        //Por ultimo, se multiplica la X e Y por la escala de la camara.
        x = x * xEscala;
        y = y * yEscala;

        //Se se produce el error de que las coordenadas están a 0 o son mayores que el tamaño de la
        //imagen, se devuelve false.
        if ((x < 0) || (y < 0) || (x > cols) || (y > rows)) {
            return false;
        }

        //Añade el texto de las coordenadas.
        tvTouchCoordenadas.setText("X: " + Math.round(Double.valueOf(x)) + ", Y: " + Math.round(Double.valueOf(y)));

        //--- COLOR ---

        //Rect es un objeto que contiene diferentes tipos de objetos 2D.
        //Se declara e inicializa e inicializa el objeto con las coordenadas tocadas.
        Rect touchRegion = new Rect();

        //Se establecen las coordenadas X e Y en el Rect según las calculadas antes.
        touchRegion.x = (int) x;
        touchRegion.y = (int) y;

        //Se establece  el ancho y el alto del ara en 8 desde las coordenadas indicadas arriba.
        touchRegion.width = 8;
        touchRegion.height = 8;

        //Se crea un Mat más pequeño con la región que se ha tocado, haciendo un recorte al fotograma.
        Mat touchRecorte = fotograma.submat(touchRegion);

        //Se crea un contenedor para el recorte.
        Mat recorteHSV = new Mat();

        //Se cambia el espacio de color a HSV.
        Imgproc.cvtColor(touchRecorte, recorteHSV, Imgproc.COLOR_RGB2HSV_FULL);

        //Se suman los elementos del Mat en HSV para obtener el color predominante en la región.
        tColorHSV = Core.sumElems(recorteHSV);

        //Se calcula el total de puntos de la región.
        int puntos = touchRegion.width * touchRegion.height;

        //Para hacer la media del color, finalmente se establece cada punto del
        //color como la suma de los valores partida del total de puntos de la región.
        for (int i = 0; i < tColorHSV.val.length; i++) {
            tColorHSV.val[i] /= puntos;
        }

        //Se invoca al método que devuelve el color de HSV a RGB.
        tColorRGBa = convertScalarHsv2Rgba(tColorHSV);

        //Se formatea el color como un trio hexadecimal.
        String hex = "#" + String.format("%02X", (int) tColorRGBa.val[0])
                + String.format("%02X", (int) tColorRGBa.val[1])
                + String.format("%02X", (int) tColorRGBa.val[2]);

        //Se transforma a RGB.
        String rgb = hex2Rgb(hex);
        muestra.setText(rgb);

        //Se establece el texto con el valor del color.
        tvTouchColor.setText("Color Hex: "+hex+"  Color RGB: "+ rgb);


        r = tColorRGBa.val[0];
        g = tColorRGBa.val[1];
        b = tColorRGBa.val[2];

        //Se establece el color del cuadro de muestra.
        muestra.setBackgroundColor(Color.rgb((int)r, (int)g, (int)b));

        tHSL = new float[3];

        ColorUtils.RGBToHSL((int)r, (int)g, (int)b, tHSL);

        //-------------------------------------------------------


        return false;
    }

    //Transforma un conjunto Scalar con una representación de color en HSV a RGB.
    private Scalar convertScalarHsv2Rgba(Scalar hsvColor) {

        //Inicializa el objeto que contendrá el RGB.
        Mat colorRGB = new Mat();

        //Inicializa el objeto como un nuevo Mat con el color HSV e indicando el tipo, que es de un
        //rango de 0 a 255.
        Mat colorHSV = new Mat(1, 1, CvType.CV_8UC3, hsvColor);

        //Convierte la imagen de HSV a RGB
        Imgproc.cvtColor(colorHSV, colorRGB, Imgproc.COLOR_HSV2RGB_FULL, 4);

        //Devuelve un nuevo Scalar con el color RGB.
        return new Scalar(colorRGB.get(0, 0));
    }

    //Transforma HEX a RGB
    private String hex2Rgb(String hex) {

        int  r=  Integer.valueOf( hex.substring( 1, 3 ), 16 );
        int  g=  Integer.valueOf( hex.substring( 3, 5 ), 16 );
        int  b=  Integer.valueOf( hex.substring( 5, 7 ), 16 );

        return Integer.toString(r)+", "+Integer.toString(g)+", "+Integer.toString(b);
    }


    //--- OTROS METODOS ----------------------------------------------------------------------------

    //Deshabilita la vista cuando la app se queda en pausa.
    @Override
    public void onPause() {
        super.onPause();
        if (OpenCvCamara != null)
            OpenCvCamara.disableView();
    }

    //Vuelve a cargar la librería cuando sale de la pausa.
    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "No se ha encontrado la librería OpenCV. Se usará OpenCV Manager para la inicialización");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "Librería de OpenCV encontrada.");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    //Desactiva la vista cuando se va a destruir la actividad.
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (OpenCvCamara != null)
            OpenCvCamara.disableView();
    }

    //Llamado cuando se inicializa la camara a traves del intermediario de OpenCV.
    @Override
    public void onCameraViewStarted(int width, int height) {

        //Inicializa el Mat del color.
        fotograma = new Mat();

        //Se inicia el Scalar del RGB
        tColorRGBa = new Scalar(255);

        //Se inicia el Scalar del HSV
        tColorHSV = new Scalar(255);
    }

    //Metodo que se ejecuta cuando se dentiene la vista de la camara.
    @Override
    public void onCameraViewStopped() {
        fotograma.release();
    }

    //Método de CallBack que se llama después de la inicialización de la librería OpenCV
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {

            switch (status) {

                //En caso de que se inicializase correctamente:
                case LoaderCallbackInterface.SUCCESS: {

                    //Se actuva la vista de camara de OpenCV.
                    OpenCvCamara.enableView();

                    //Se añade a esta un touch listener, implementado en esta misma clase.
                    OpenCvCamara.setOnTouchListener(Base.this);

                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    //Creación del menú.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    //Selector de opciones del menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_analogo:{
                Intent i = new Intent(this, AnalogaActivity.class);
                startActivity(i);
                break;
                }
            case R.id.menu_complementario:{
                Intent i = new Intent(this, ComplementarioActivity.class);
                startActivity(i);
                break;
                }
            case R.id.menu_complementariops:{
                Intent i = new Intent(this, ComplementariaPorSeparadoActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_tetrada:{
                Intent i = new Intent(this, TetradaActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_triada:{
                Intent i = new Intent(this, TriadaActivity.class);
                startActivity(i);
                break;
            }
            case R.id.menu_cuadrado:{
                Intent i = new Intent(this, CuadradoActivity.class);
                startActivity(i);
                break;
            }
        }
        return super.onOptionsItemSelected(item);

}
}
