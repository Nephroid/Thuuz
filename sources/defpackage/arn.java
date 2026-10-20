package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class arn {
    public static final Object a = new Object();
    static final String[] b = "gtm.lifetime".toString().split("\\.");
    private static final Pattern c = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<arr, Integer> d;
    private final Map<String, Object> e;
    private final ReentrantLock f;
    private final LinkedList<Map<String, Object>> g;
    private final ars h;
    private final CountDownLatch i;

    arn() {
        this(new aro());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public arn(ars arsVar) {
        this.h = arsVar;
        this.d = new ConcurrentHashMap<>();
        this.e = new HashMap();
        this.f = new ReentrantLock();
        this.g = new LinkedList<>();
        this.i = new CountDownLatch(1);
        a();
    }

    static Long a(String str) {
        long j;
        Matcher matcher = c.matcher(str);
        if (!matcher.matches()) {
            asj.c("unknown _lifetime: " + str);
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            asj.b("illegal number in _lifetime value: " + str);
            j = 0;
        }
        if (j <= 0) {
            asj.c("non-positive _lifetime: " + str);
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        switch (group.charAt(0)) {
            case 'd':
                return Long.valueOf(j * 1000 * 60 * 60 * 24);
            case 'h':
                return Long.valueOf(j * 1000 * 60 * 60);
            case 'm':
                return Long.valueOf(j * 1000 * 60);
            case 's':
                return Long.valueOf(j * 1000);
            default:
                asj.b("unknown units in _lifetime: " + str);
                return null;
        }
    }

    private void a() {
        this.h.a(new arp(this));
    }

    private void a(Map<String, Object> map, String str, Collection<arq> collection) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String str2 = str + (str.length() == 0 ? "" : ".") + entry.getKey();
            if (entry.getValue() instanceof Map) {
                a((Map) entry.getValue(), str2, collection);
            } else if (!str2.equals("gtm.lifetime")) {
                collection.add(new arq(str2, entry.getValue()));
            }
        }
    }

    private void b() {
        int i = 0;
        do {
            int i2 = i;
            Map<String, Object> poll = this.g.poll();
            if (poll == null) {
                return;
            }
            g(poll);
            i = i2 + 1;
        } while (i <= 500);
        this.g.clear();
        throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Map<String, Object> map) {
        this.f.lock();
        try {
            this.g.offer(map);
            if (this.f.getHoldCount() == 1) {
                b();
            }
            c(map);
        } finally {
            this.f.unlock();
        }
    }

    private void c(Map<String, Object> map) {
        Long d = d(map);
        if (d == null) {
            return;
        }
        List<arq> f = f(map);
        f.remove("gtm.lifetime");
        this.h.a(f, d.longValue());
    }

    private Long d(Map<String, Object> map) {
        Object e = e(map);
        if (e == null) {
            return null;
        }
        return a(e.toString());
    }

    private Object e(Map<String, Object> map) {
        String[] strArr = b;
        int length = strArr.length;
        int i = 0;
        Map<String, Object> map2 = map;
        while (i < length) {
            String str = strArr[i];
            if (!(map2 instanceof Map)) {
                return null;
            }
            i++;
            map2 = map2.get(str);
        }
        return map2;
    }

    private List<arq> f(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        a(map, "", arrayList);
        return arrayList;
    }

    private void g(Map<String, Object> map) {
        synchronized (this.e) {
            for (String str : map.keySet()) {
                a(a(str, map.get(str)), this.e);
            }
        }
        h(map);
    }

    private void h(Map<String, Object> map) {
        Iterator<arr> it = this.d.keySet().iterator();
        while (it.hasNext()) {
            it.next().a(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, Object> a(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(arr arrVar) {
        this.d.put(arrVar, 0);
    }

    void a(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add(null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return;
            }
            Object obj = list.get(i2);
            if (obj instanceof List) {
                if (!(list2.get(i2) instanceof List)) {
                    list2.set(i2, new ArrayList());
                }
                a((List<Object>) obj, (List<Object>) list2.get(i2));
            } else if (obj instanceof Map) {
                if (!(list2.get(i2) instanceof Map)) {
                    list2.set(i2, new HashMap());
                }
                a((Map<String, Object>) obj, (Map<String, Object>) list2.get(i2));
            } else if (obj != a) {
                list2.set(i2, obj);
            }
            i = i2 + 1;
        }
    }

    public void a(Map<String, Object> map) {
        try {
            this.i.await();
        } catch (InterruptedException e) {
            asj.b("DataLayer.push: unexpected InterruptedException");
        }
        b(map);
    }

    void a(Map<String, Object> map, Map<String, Object> map2) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj instanceof List) {
                if (!(map2.get(str) instanceof List)) {
                    map2.put(str, new ArrayList());
                }
                a((List<Object>) obj, (List<Object>) map2.get(str));
            } else if (obj instanceof Map) {
                if (!(map2.get(str) instanceof Map)) {
                    map2.put(str, new HashMap());
                }
                a((Map<String, Object>) obj, (Map<String, Object>) map2.get(str));
            } else {
                map2.put(str, obj);
            }
        }
    }

    public String toString() {
        String sb;
        synchronized (this.e) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry<String, Object> entry : this.e.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", entry.getKey(), entry.getValue()));
            }
            sb = sb2.toString();
        }
        return sb;
    }
}
