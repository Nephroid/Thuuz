package defpackage;

import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class cby extends SSLSocketFactory {
    private final SSLSocketFactory a = HttpsURLConnection.getDefaultSSLSocketFactory();

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    private static Socket a(Socket socket) {
        cbz cbzVar;
        if (socket instanceof SSLSocket) {
            try {
                cbzVar = new cbz((SSLSocket) socket);
            } catch (IOException e) {
            }
            try {
                Log.d("NoSSLv3Compat", "Created socket: " + cbzVar);
                return cbzVar;
            } catch (IOException e2) {
                socket = cbzVar;
                Log.w("NoSSLv3Compat", "IOException in makeSocketSafe with socket: " + socket);
                return socket;
            }
        }
        return socket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return a(this.a.createSocket(socket, str, i, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) {
        return a(this.a.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) {
        return a(this.a.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }
}
