package defpackage;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

/* loaded from: classes.dex */
public class te {
    private final int b;
    private Base64OutputStream d;
    private ByteArrayOutputStream e;
    private final td c = new ti();
    private final int a = 6;

    public te(int i) {
        this.b = i;
    }

    private String b(String str) {
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return "";
        }
        this.e = new ByteArrayOutputStream();
        this.d = new Base64OutputStream(this.e, 10);
        Arrays.sort(split, new tf(this));
        for (int i = 0; i < split.length && i < this.b; i++) {
            if (split[i].trim().length() != 0) {
                try {
                    this.d.write(this.c.a(split[i]));
                } catch (IOException e) {
                    adn.b("Error while writing hash to byteStream", e);
                }
            }
        }
        try {
            this.d.flush();
            this.d.close();
            return this.e.toString();
        } catch (IOException e2) {
            adn.b("HashManager: Unable to convert to base 64", e2);
            return "";
        }
    }

    String a(String str) {
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return "";
        }
        this.e = new ByteArrayOutputStream();
        this.d = new Base64OutputStream(this.e, 10);
        PriorityQueue priorityQueue = new PriorityQueue(this.b, new tg(this));
        for (String str2 : split) {
            String[] b = th.b(str2);
            if (b.length >= this.a) {
                tj.a(b, this.b, this.a, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                this.d.write(this.c.a(((tk) it.next()).b));
            } catch (IOException e) {
                adn.b("Error while writing hash to byteStream", e);
            }
        }
        try {
            this.d.flush();
            this.d.close();
            return this.e.toString();
        } catch (IOException e2) {
            adn.b("HashManager: unable to convert to base 64", e2);
            return "";
        }
    }

    public String a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toLowerCase());
            stringBuffer.append('\n');
        }
        boolean z = false;
        switch (z) {
            case false:
                return a(stringBuffer.toString());
            case true:
                return b(stringBuffer.toString());
            default:
                return "";
        }
    }
}
