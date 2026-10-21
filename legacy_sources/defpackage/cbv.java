package defpackage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
public class cbv extends SSLSocket {
    protected final SSLSocket a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cbv(SSLSocket sSLSocket) {
        super(sSLSocket.getInetAddress(), sSLSocket.getPort());
        this.a = sSLSocket;
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledCipherSuites() {
        return this.a.getEnabledCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledCipherSuites(String[] strArr) {
        this.a.setEnabledCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getSupportedProtocols() {
        return this.a.getSupportedProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public String[] getEnabledProtocols() {
        return this.a.getEnabledProtocols();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnabledProtocols(String[] strArr) {
        this.a.setEnabledProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return this.a.getSession();
    }

    @Override // javax.net.ssl.SSLSocket
    public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.a.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    @Override // javax.net.ssl.SSLSocket
    public void startHandshake() {
        this.a.startHandshake();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setUseClientMode(boolean z) {
        this.a.setUseClientMode(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getUseClientMode() {
        return this.a.getUseClientMode();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setNeedClientAuth(boolean z) {
        this.a.setNeedClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setWantClientAuth(boolean z) {
        this.a.setWantClientAuth(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getNeedClientAuth() {
        return this.a.getNeedClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getWantClientAuth() {
        return this.a.getWantClientAuth();
    }

    @Override // javax.net.ssl.SSLSocket
    public void setEnableSessionCreation(boolean z) {
        this.a.setEnableSessionCreation(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public boolean getEnableSessionCreation() {
        return this.a.getEnableSessionCreation();
    }

    @Override // java.net.Socket
    public void bind(SocketAddress socketAddress) {
        this.a.bind(socketAddress);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.a.close();
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress) {
        this.a.connect(socketAddress);
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i) {
        this.a.connect(socketAddress, i);
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        return this.a.getChannel();
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.a.getInetAddress();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() {
        return this.a.getInputStream();
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() {
        return this.a.getKeepAlive();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.a.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.a.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.a.getLocalSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getOOBInline() {
        return this.a.getOOBInline();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() {
        return this.a.getOutputStream();
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.a.getPort();
    }

    @Override // java.net.Socket
    public synchronized int getReceiveBufferSize() {
        return this.a.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.a.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() {
        return this.a.getReuseAddress();
    }

    @Override // java.net.Socket
    public synchronized int getSendBufferSize() {
        return this.a.getSendBufferSize();
    }

    @Override // java.net.Socket
    public int getSoLinger() {
        return this.a.getSoLinger();
    }

    @Override // java.net.Socket
    public synchronized int getSoTimeout() {
        return this.a.getSoTimeout();
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() {
        return this.a.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public int getTrafficClass() {
        return this.a.getTrafficClass();
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.a.isBound();
    }

    @Override // java.net.Socket
    public boolean isClosed() {
        return this.a.isClosed();
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.a.isConnected();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.a.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.a.isOutputShutdown();
    }

    @Override // java.net.Socket
    public void sendUrgentData(int i) {
        this.a.sendUrgentData(i);
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) {
        this.a.setKeepAlive(z);
    }

    @Override // java.net.Socket
    public void setOOBInline(boolean z) {
        this.a.setOOBInline(z);
    }

    @Override // java.net.Socket
    public void setPerformancePreferences(int i, int i2, int i3) {
        this.a.setPerformancePreferences(i, i2, i3);
    }

    @Override // java.net.Socket
    public synchronized void setReceiveBufferSize(int i) {
        this.a.setReceiveBufferSize(i);
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) {
        this.a.setReuseAddress(z);
    }

    @Override // java.net.Socket
    public synchronized void setSendBufferSize(int i) {
        this.a.setSendBufferSize(i);
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i) {
        this.a.setSoLinger(z, i);
    }

    @Override // java.net.Socket
    public synchronized void setSoTimeout(int i) {
        this.a.setSoTimeout(i);
    }

    @Override // javax.net.ssl.SSLSocket
    public void setSSLParameters(SSLParameters sSLParameters) {
        this.a.setSSLParameters(sSLParameters);
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) {
        this.a.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i) {
        this.a.setTrafficClass(i);
    }

    @Override // java.net.Socket
    public void shutdownInput() {
        this.a.shutdownInput();
    }

    @Override // java.net.Socket
    public void shutdownOutput() {
        this.a.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public String toString() {
        return this.a.toString();
    }

    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }
}
