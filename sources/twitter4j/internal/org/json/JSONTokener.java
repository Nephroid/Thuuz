package twitter4j.internal.org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

/* loaded from: classes.dex */
public class JSONTokener {
    private int character;
    private boolean eof;
    private int index;
    private int line;
    private char previous;
    private Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader) {
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = (char) 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() {
        int read;
        if (this.usePrevious) {
            this.usePrevious = false;
            read = this.previous;
        } else {
            try {
                read = this.reader.read();
                if (read <= 0) {
                    this.eof = true;
                    read = 0;
                }
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.index++;
        if (this.previous == '\r') {
            this.line++;
            this.character = read != 10 ? 1 : 0;
        } else if (read == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) read;
        return this.previous;
    }

    public char next(char c) {
        char next = next();
        if (next != c) {
            throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
        }
        return next;
    }

    public String next(int i) {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            char next = next();
            switch (next) {
                case 0:
                case '\n':
                case HTTP.CR /* 13 */:
                    throw syntaxError("Unterminated string");
                case '\\':
                    char next2 = next();
                    switch (next2) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            sb.append(next2);
                            break;
                        case 'b':
                            sb.append('\b');
                            break;
                        case HttpStatus.SC_PROCESSING /* 102 */:
                            sb.append('\f');
                            break;
                        case 'n':
                            sb.append('\n');
                            break;
                        case 'r':
                            sb.append('\r');
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                        case 'u':
                            sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                default:
                    if (next == c) {
                        return sb.toString();
                    }
                    sb.append(next);
                    break;
            }
        }
    }

    public Object nextValue() {
        char nextClean = nextClean();
        switch (nextClean) {
            case '\"':
            case '\'':
                return nextString(nextClean);
            case '[':
                back();
                return new JSONArray(this);
            case '{':
                back();
                return new JSONObject(this);
            default:
                StringBuilder sb = new StringBuilder();
                while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                    sb.append(nextClean);
                    nextClean = next();
                }
                back();
                String trim = sb.toString().trim();
                if (trim.equals("")) {
                    throw syntaxError("Missing value");
                }
                return JSONObject.stringToValue(trim);
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}
