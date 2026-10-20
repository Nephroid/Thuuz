package defpackage;

import java.util.PriorityQueue;

/* loaded from: classes.dex */
public class tj {
    static long a(int i, int i2, long j, long j2, long j3) {
        return ((((((j + 1073807359) - ((((i + 2147483647L) % 1073807359) * j2) % 1073807359)) % 1073807359) * j3) % 1073807359) + ((i2 + 2147483647L) % 1073807359)) % 1073807359;
    }

    static long a(long j, int i) {
        if (i == 0) {
            return 1L;
        }
        return i != 1 ? i % 2 == 0 ? a((j * j) % 1073807359, i / 2) % 1073807359 : ((a((j * j) % 1073807359, i / 2) % 1073807359) * j) % 1073807359 : j;
    }

    static String a(String[] strArr, int i, int i2) {
        if (strArr.length < i + i2) {
            adn.b("Unable to construct shingle");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < (i + i2) - 1; i3++) {
            stringBuffer.append(strArr[i3]);
            stringBuffer.append(' ');
        }
        stringBuffer.append(strArr[(i + i2) - 1]);
        return stringBuffer.toString();
    }

    private static void a(int i, long j, int i2, String[] strArr, int i3, PriorityQueue<tk> priorityQueue) {
        priorityQueue.add(new tk(j, a(strArr, i2, i3)));
        if (priorityQueue.size() > i) {
            priorityQueue.poll();
        }
    }

    public static void a(String[] strArr, int i, int i2, PriorityQueue<tk> priorityQueue) {
        long b = b(strArr, 0, i2);
        a(i, b, 0, strArr, i2, priorityQueue);
        long a = a(16785407L, i2 - 1);
        int i3 = 1;
        while (i3 < (strArr.length - i2) + 1) {
            long a2 = a(th.a(strArr[i3 - 1]), th.a(strArr[(i3 + i2) - 1]), b, a, 16785407L);
            a(i, a2, i3, strArr, i2, priorityQueue);
            i3++;
            b = a2;
        }
    }

    private static long b(String[] strArr, int i, int i2) {
        long a = (th.a(strArr[i]) + 2147483647L) % 1073807359;
        for (int i3 = i + 1; i3 < i + i2; i3++) {
            a = (((a * 16785407) % 1073807359) + ((th.a(strArr[i3]) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return a;
    }
}
