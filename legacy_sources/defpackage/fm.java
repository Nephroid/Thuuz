package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import com.samsung.android.sdk.richnotification.Utilities;
import defpackage.fl;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class fm<T extends fl> {
    Context a;
    fn<T> b;

    public fm(Context context, fn<T> fnVar) {
        this.a = context;
        this.b = fnVar;
    }

    private T a(XmlResourceParser xmlResourceParser) {
        try {
            xmlResourceParser.next();
            int eventType = xmlResourceParser.getEventType();
            while (eventType != 1) {
                if (xmlResourceParser.getEventType() == 2) {
                    String lowerCase = xmlResourceParser.getName().toLowerCase();
                    if (lowerCase.equals("screenname")) {
                        String attributeValue = xmlResourceParser.getAttributeValue(null, Utilities.DB_KEY_IMAGE_NAME);
                        String trim = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue) && !TextUtils.isEmpty(trim)) {
                            this.b.a(attributeValue, trim);
                        }
                    } else if (lowerCase.equals("string")) {
                        String attributeValue2 = xmlResourceParser.getAttributeValue(null, Utilities.DB_KEY_IMAGE_NAME);
                        String trim2 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue2) && trim2 != null) {
                            this.b.b(attributeValue2, trim2);
                        }
                    } else if (lowerCase.equals("bool")) {
                        String attributeValue3 = xmlResourceParser.getAttributeValue(null, Utilities.DB_KEY_IMAGE_NAME);
                        String trim3 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue3) && !TextUtils.isEmpty(trim3)) {
                            try {
                                this.b.a(attributeValue3, Boolean.parseBoolean(trim3));
                            } catch (NumberFormatException e) {
                                gn.a("Error parsing bool configuration value: " + trim3);
                            }
                        }
                    } else if (lowerCase.equals("integer")) {
                        String attributeValue4 = xmlResourceParser.getAttributeValue(null, Utilities.DB_KEY_IMAGE_NAME);
                        String trim4 = xmlResourceParser.nextText().trim();
                        if (!TextUtils.isEmpty(attributeValue4) && !TextUtils.isEmpty(trim4)) {
                            try {
                                this.b.a(attributeValue4, Integer.parseInt(trim4));
                            } catch (NumberFormatException e2) {
                                gn.a("Error parsing int configuration value: " + trim4);
                            }
                        }
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e3) {
            gn.a("Error parsing tracker configuration file: " + e3);
        } catch (XmlPullParserException e4) {
            gn.a("Error parsing tracker configuration file: " + e4);
        }
        return this.b.a();
    }

    public T a(int i) {
        try {
            return a(this.a.getResources().getXml(i));
        } catch (Resources.NotFoundException e) {
            gn.d("inflate() called with unknown resourceId: " + e);
            return null;
        }
    }
}
