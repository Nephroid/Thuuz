package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class cd {
    private static cd a = new cd();

    public static cd a() {
        return a;
    }

    private cd() {
    }

    private File c(Context context) {
        return new File(context.getFilesDir(), "AFRequestCache");
    }

    public void a(Context context) {
        try {
            if (!c(context).exists()) {
                c(context).mkdir();
            }
        } catch (Exception e) {
            Log.i("AppsFlyer_3.0", "Could not create cache directory");
        }
    }

    public void a(ce ceVar, Context context) {
        try {
            File c = c(context);
            if (!c.exists()) {
                c.mkdir();
            } else if (c.listFiles().length > 20) {
                Log.i("AppsFlyer_3.0", "reached cache limit, not caching request");
            } else {
                Log.i("AppsFlyer_3.0", "caching request...");
                File file = new File(c(context), Long.toString(System.currentTimeMillis()));
                file.createNewFile();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file.getPath(), true));
                outputStreamWriter.write("version=");
                outputStreamWriter.write(ceVar.a());
                outputStreamWriter.write(10);
                outputStreamWriter.write("url=");
                outputStreamWriter.write(ceVar.c());
                outputStreamWriter.write(10);
                outputStreamWriter.write("data=");
                outputStreamWriter.write(ceVar.b());
                outputStreamWriter.write(10);
                outputStreamWriter.flush();
                outputStreamWriter.close();
            }
        } catch (Exception e) {
            Log.i("AppsFlyer_3.0", "Could not cache request");
        }
    }

    public List<ce> b(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File c = c(context);
            if (!c.exists()) {
                c.mkdir();
            } else {
                File[] listFiles = c.listFiles();
                for (File file : listFiles) {
                    Log.i("AppsFlyer_3.0", "Found cached request" + file.getName());
                    arrayList.add(a(file));
                }
            }
        } catch (Exception e) {
            Log.i("AppsFlyer_3.0", "Could not cache request");
        }
        return arrayList;
    }

    private ce a(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            char[] cArr = new char[(int) file.length()];
            fileReader.read(cArr);
            ce ceVar = new ce(cArr);
            ceVar.a(file.getName());
            fileReader.close();
            return ceVar;
        } catch (Exception e) {
            return null;
        }
    }

    public void a(String str, Context context) {
        File file = new File(c(context), str);
        Log.i("AppsFlyer_3.0", "Deleting " + str + " from cache");
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e) {
                Log.i("AppsFlyer_3.0", "Could not delete " + str + " from cache", e);
            }
        }
    }
}
