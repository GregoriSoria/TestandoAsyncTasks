package admin.com.testandoasynctasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button meuBotao;
    private ImageView minhaImagem;

    String urlImagem = "http://androidwalls.net/wp-content/uploads/2016/03/Darth%20Vader%20Walking%20AT-AT%20Pet%20Android%20Wallpaper-342x640.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuBotao = (Button) findViewById(R.id.button);
        minhaImagem = (ImageView) findViewById(R.id.imagem);

        meuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagemDownloader imagemDownloader = new ImagemDownloader();
                imagemDownloader.execute(urlImagem);
            }
        });
    }

    public class ImagemDownloader extends AsyncTask<String, String, Bitmap> {

        AlertDialog alerta = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Carregando Imagem...")
                .show();

        @Override
        protected Bitmap doInBackground(String... args) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                minhaImagem.setImageBitmap(bitmap);
                alerta.hide();
            }
        }
    }
}
