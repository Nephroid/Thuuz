package defpackage;

import java.util.Scanner;

/* loaded from: classes.dex */
public class ce {
    private String a;
    private String b;
    private String c;
    private String d;

    public ce(String str, String str2, String str3) {
        this.c = str;
        this.b = str2;
        this.a = str3;
    }

    public ce(char[] cArr) {
        Scanner scanner = new Scanner(new String(cArr));
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (nextLine.startsWith("url=")) {
                this.c = nextLine.substring("url=".length()).trim();
            } else if (nextLine.startsWith("version=")) {
                this.a = nextLine.substring("version=".length()).trim();
            } else if (nextLine.startsWith("data=")) {
                this.b = nextLine.substring("data=".length()).trim();
            }
        }
        scanner.close();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }
}
