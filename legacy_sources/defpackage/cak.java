package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.thuuz.gtv.activity.AbstractActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;

/* loaded from: classes.dex */
public class cak extends bzy {
    public static void a(AbstractActivity abstractActivity, ListView listView) {
        ArrayAdapter arrayAdapter;
        if (listView != null) {
            try {
                if (listView.getVisibility() != 8 && (arrayAdapter = (ArrayAdapter) listView.getAdapter()) != null) {
                    boolean z = false;
                    int count = arrayAdapter.getCount();
                    if (listView.getMeasuredHeight() == 0) {
                        a(listView, count);
                        z = true;
                    }
                    if (abstractActivity != null && !abstractActivity.isDestroyed()) {
                        abstractActivity.aa().postDelayed(new cal(abstractActivity, listView), 50L);
                    }
                    if (!z) {
                        if (listView.getChildCount() != count) {
                            arrayAdapter.notifyDataSetChanged();
                        } else {
                            listView.requestLayout();
                        }
                    }
                }
            } catch (Throwable th) {
                getStaticLogger(cak.class).log(Level.SEVERE, "problem: ", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(AbstractActivity abstractActivity, ListView listView, int i) {
        ViewGroup.LayoutParams layoutParams;
        ListAdapter adapter;
        boolean z = true;
        if (listView != null && abstractActivity != null) {
            try {
                if (!abstractActivity.isDestroyed() && (layoutParams = listView.getLayoutParams()) != null && (adapter = listView.getAdapter()) != null) {
                    if (adapter.getCount() == 0) {
                        layoutParams.height = 0;
                        return;
                    }
                    int makeMeasureSpec = listView.getMeasuredWidth() > 0 ? View.MeasureSpec.makeMeasureSpec(listView.getMeasuredWidth(), 1073741824) : 0;
                    int count = adapter.getCount();
                    int i2 = 0;
                    int i3 = 0;
                    for (int i4 = 0; i4 < count; i4++) {
                        View childAt = listView.getChildAt(i4);
                        if (childAt == null) {
                            i3 += Math.max(52, i2);
                            z = false;
                        } else {
                            childAt.measure(makeMeasureSpec, 0);
                            i3 += childAt.getMeasuredHeight();
                            i2 = i3 / (i4 + 1);
                        }
                    }
                    int dividerHeight = (listView.getDividerHeight() * (count - 1)) + i3;
                    if (layoutParams.height != dividerHeight) {
                        layoutParams.height = dividerHeight;
                        listView.requestLayout();
                        if (!z) {
                            if (i > 10) {
                                getStaticLogger(cak.class).severe("after 10 loops, abandoning expandList for activity: " + abstractActivity.getClass().getSimpleName());
                                return;
                            }
                            int i5 = i + 1;
                            if (listView.getChildCount() != adapter.getCount() && (adapter instanceof ArrayAdapter)) {
                                ((ArrayAdapter) adapter).notifyDataSetChanged();
                            }
                            abstractActivity.aa().postDelayed(new cam(abstractActivity, listView, i5), 50L);
                        }
                    }
                }
            } catch (Throwable th) {
                getStaticLogger(cak.class).log(Level.SEVERE, "problem in listview expansion: ", th);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002a A[Catch: Throwable -> 0x002e, TRY_LEAVE, TryCatch #0 {Throwable -> 0x002e, blocks: (B:5:0x0003, B:7:0x0009, B:9:0x0011, B:11:0x0019, B:12:0x001f, B:14:0x002a, B:16:0x004a, B:19:0x003d), top: B:4:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a A[Catch: Throwable -> 0x002e, TRY_LEAVE, TryCatch #0 {Throwable -> 0x002e, blocks: (B:5:0x0003, B:7:0x0009, B:9:0x0011, B:11:0x0019, B:12:0x001f, B:14:0x002a, B:16:0x004a, B:19:0x003d), top: B:4:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.widget.ListView r4, int r5) {
        /*
            if (r4 != 0) goto L3
        L2:
            return
        L3:
            android.view.ViewGroup$LayoutParams r2 = r4.getLayoutParams()     // Catch: java.lang.Throwable -> L2e
            if (r2 == 0) goto L2
            r1 = 76
            android.content.Context r0 = r4.getContext()     // Catch: java.lang.Throwable -> L2e
            if (r0 == 0) goto L3d
            android.content.Context r0 = r0.getApplicationContext()     // Catch: java.lang.Throwable -> L2e
            com.thuuz.gtv.App r0 = (com.thuuz.gtv.App) r0     // Catch: java.lang.Throwable -> L2e
            if (r0 == 0) goto L48
            r1 = 52
            int r0 = r0.q(r1)     // Catch: java.lang.Throwable -> L2e
        L1f:
            int r0 = r0 * r5
            r2.height = r0     // Catch: java.lang.Throwable -> L2e
            android.widget.ListAdapter r0 = r4.getAdapter()     // Catch: java.lang.Throwable -> L2e
            android.widget.ArrayAdapter r0 = (android.widget.ArrayAdapter) r0     // Catch: java.lang.Throwable -> L2e
            if (r0 == 0) goto L4a
            r0.notifyDataSetChanged()     // Catch: java.lang.Throwable -> L2e
            goto L2
        L2e:
            r0 = move-exception
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem setting listview height by calc: "
            r1.log(r2, r3, r0)
            goto L2
        L3d:
            java.lang.Class<cak> r0 = defpackage.cak.class
            java.util.logging.Logger r0 = getStaticLogger(r0)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r3 = "no context on listview"
            r0.severe(r3)     // Catch: java.lang.Throwable -> L2e
        L48:
            r0 = r1
            goto L1f
        L4a:
            r4.requestLayout()     // Catch: java.lang.Throwable -> L2e
            goto L2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cak.a(android.widget.ListView, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.logging.Logger] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.util.logging.Level] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.logging.Level] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.util.logging.Level] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x002b -> B:15:0x0002). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r6, java.lang.String r7) {
        /*
            if (r7 != 0) goto L3
        L2:
            return
        L3:
            if (r6 == 0) goto L2
            r2 = 0
            java.io.BufferedWriter r1 = new java.io.BufferedWriter     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L6c java.lang.Throwable -> La2
            java.io.FileWriter r0 = new java.io.FileWriter     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L6c java.lang.Throwable -> La2
            r0.<init>(r6)     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L6c java.lang.Throwable -> La2
            r3 = 5000(0x1388, float:7.006E-42)
            r1.<init>(r0, r3)     // Catch: java.io.IOException -> L39 java.lang.Throwable -> L6c java.lang.Throwable -> La2
            r1.write(r7)     // Catch: java.lang.Throwable -> Lb8 java.lang.Throwable -> Lbb java.io.IOException -> Lbe
            if (r1 == 0) goto L2
            r1.close()     // Catch: java.lang.Throwable -> L1b
            goto L2
        L1b:
            r0 = move-exception
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)     // Catch: java.lang.Throwable -> L2a
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L2a
            java.lang.String r3 = "Cannot close team cache writer: "
            r1.log(r2, r3, r0)     // Catch: java.lang.Throwable -> L2a
            goto L2
        L2a:
            r0 = move-exception
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "problem: "
            r1.log(r2, r3, r0)
            goto L2
        L39:
            r0 = move-exception
            r1 = r2
        L3b:
            java.lang.Class<cak> r2 = defpackage.cak.class
            java.util.logging.Logger r2 = getStaticLogger(r2)     // Catch: java.lang.Throwable -> Lb8
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb8
            r3.<init>()     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r4 = "Error caching teams: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> Lb8
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> Lb8
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb8
            r2.warning(r0)     // Catch: java.lang.Throwable -> Lb8
            if (r1 == 0) goto L2
            r1.close()     // Catch: java.lang.Throwable -> L5d
            goto L2
        L5d:
            r0 = move-exception
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)     // Catch: java.lang.Throwable -> L2a
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L2a
            java.lang.String r3 = "Cannot close team cache writer: "
            r1.log(r2, r3, r0)     // Catch: java.lang.Throwable -> L2a
            goto L2
        L6c:
            r0 = move-exception
        L6d:
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)     // Catch: java.lang.Throwable -> La2
            java.util.logging.Level r3 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> La2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La2
            r4.<init>()     // Catch: java.lang.Throwable -> La2
            java.lang.String r5 = "Error caching teams: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> La2
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch: java.lang.Throwable -> La2
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La2
            r1.log(r3, r0)     // Catch: java.lang.Throwable -> La2
            if (r2 == 0) goto L2
            r2.close()     // Catch: java.lang.Throwable -> L92
            goto L2
        L92:
            r0 = move-exception
            java.lang.Class<cak> r1 = defpackage.cak.class
            java.util.logging.Logger r1 = getStaticLogger(r1)     // Catch: java.lang.Throwable -> L2a
            java.util.logging.Level r2 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L2a
            java.lang.String r3 = "Cannot close team cache writer: "
            r1.log(r2, r3, r0)     // Catch: java.lang.Throwable -> L2a
            goto L2
        La2:
            r0 = move-exception
        La3:
            if (r2 == 0) goto La8
            r2.close()     // Catch: java.lang.Throwable -> La9
        La8:
            throw r0     // Catch: java.lang.Throwable -> L2a
        La9:
            r1 = move-exception
            java.lang.Class<cak> r2 = defpackage.cak.class
            java.util.logging.Logger r2 = getStaticLogger(r2)     // Catch: java.lang.Throwable -> L2a
            java.util.logging.Level r3 = java.util.logging.Level.WARNING     // Catch: java.lang.Throwable -> L2a
            java.lang.String r4 = "Cannot close team cache writer: "
            r2.log(r3, r4, r1)     // Catch: java.lang.Throwable -> L2a
            goto La8
        Lb8:
            r0 = move-exception
            r2 = r1
            goto La3
        Lbb:
            r0 = move-exception
            r2 = r1
            goto L6d
        Lbe:
            r0 = move-exception
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cak.a(java.io.File, java.lang.String):void");
    }

    public static String a(File file) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        String str = "";
        try {
            if (file != null) {
                try {
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            getStaticLogger(cak.class).log(Level.SEVERE, "Cannot handle: ", (Throwable) e);
                        }
                    }
                    throw th;
                }
                if (file.exists()) {
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new FileReader(file));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                            sb.append('\n');
                        } catch (Throwable th2) {
                            th = th2;
                            getStaticLogger(cak.class).log(Level.WARNING, "Error using cache: " + th);
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    getStaticLogger(cak.class).log(Level.SEVERE, "Cannot handle: ", (Throwable) e2);
                                }
                            }
                            return str;
                        }
                    }
                    str = sb.toString();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            getStaticLogger(cak.class).log(Level.SEVERE, "Cannot handle: ", (Throwable) e3);
                        }
                    }
                    return str;
                }
            }
            str = "";
            if (0 != 0) {
                try {
                    bufferedReader2.close();
                } catch (IOException e4) {
                    getStaticLogger(cak.class).log(Level.SEVERE, "Cannot handle: ", (Throwable) e4);
                }
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
