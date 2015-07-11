package baseEngine;

import android.content.res.Resources;

import com.example.des.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class ResourceHelper{

    public static String loadJSONFromResource(Resources res) {
        String json = null;
        try {
            InputStream is = res.openRawResource(R.raw.heroesdata);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
