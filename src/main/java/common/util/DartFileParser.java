package common.util;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;
import java.util.Objects;

public class DartFileParser {

    public void parsingCLI() throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StatementEnum[] statements = StatementEnum.values();
        int index = 0;
        for(StatementEnum statement : statements){
            System.out.println(++index+". " +statement.name());
        }

        int selected = reader.read() - '0' -1;
        reader.readLine();
        StatementEnum statement = statements[selected];

        sb.append(statement.path());
        File[] ymds = new File(sb.toString()).listFiles();

        index = 0;
        assert ymds != null;
        for(File file : ymds){
            System.out.println(++index + ". " +file.getName());
        }

        selected = reader.read() - '0' -1;

        String ymd = "/"+ymds[selected].getName();
        sb.append(ymd);

        String path = sb.toString();
        File dir = new File(path);

        parseFile(Objects.requireNonNull(dir.listFiles()));
    }

    public void parseFile(File[] files) throws IOException {
        for(File file : files){
            parseFile(file);
        }
    }

    public void parseFile(File file) throws IOException {
        String encoding = readEncoding(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        String str;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
        }
        reader.close();
    }

    public static String readEncoding(File file) throws IOException {
        byte[] buf = new byte[4096];
        java.io.FileInputStream fis = new java.io.FileInputStream(file);
        UniversalDetector detector = new UniversalDetector(null);
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        fis.close();
        return encoding == null?"UTF-8":encoding;
    }
}
