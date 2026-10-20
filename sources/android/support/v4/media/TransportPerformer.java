package android.support.v4.media;

import android.os.SystemClock;
import android.view.KeyEvent;

/* loaded from: classes.dex */
public abstract class TransportPerformer {
    static final int AUDIOFOCUS_GAIN = 1;
    static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    static final int AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK = 3;
    static final int AUDIOFOCUS_LOSS = -1;
    static final int AUDIOFOCUS_LOSS_TRANSIENT = -2;
    static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -3;

    public abstract long onGetCurrentPosition();

    public abstract long onGetDuration();

    public abstract boolean onIsPlaying();

    public abstract void onPause();

    public abstract void onSeekTo(long j);

    public abstract void onStart();

    public abstract void onStop();

    public int onGetBufferPercentage() {
        return 100;
    }

    public int onGetTransportControlFlags() {
        return 60;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0004, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onMediaButtonDown(int r3, android.view.KeyEvent r4) {
        /*
            r2 = this;
            r1 = 1
            switch(r3) {
                case 79: goto L11;
                case 85: goto L11;
                case 86: goto Ld;
                case 126: goto L5;
                case 127: goto L9;
                default: goto L4;
            }
        L4:
            return r1
        L5:
            r2.onStart()
            goto L4
        L9:
            r2.onPause()
            goto L4
        Ld:
            r2.onStop()
            goto L4
        L11:
            boolean r0 = r2.onIsPlaying()
            if (r0 == 0) goto L1b
            r2.onPause()
            goto L4
        L1b:
            r2.onStart()
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.TransportPerformer.onMediaButtonDown(int, android.view.KeyEvent):boolean");
    }

    public boolean onMediaButtonUp(int i, KeyEvent keyEvent) {
        return true;
    }

    public void onAudioFocusChange(int i) {
        int i2 = 0;
        switch (i) {
            case -1:
                i2 = TransportMediator.KEYCODE_MEDIA_PAUSE;
                break;
        }
        if (i2 != 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            onMediaButtonDown(i2, new KeyEvent(uptimeMillis, uptimeMillis, 0, i2, 0));
            onMediaButtonUp(i2, new KeyEvent(uptimeMillis, uptimeMillis, 1, i2, 0));
        }
    }
}
