import common.util.DartFileParser;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DartFileParser fileUtil = new DartFileParser();
//        fileUtil.parsingCLI();

        fileUtil.parseFile(new File("/D:/Intellij_projects/dartDataParser/target/classes/dart/BS/20221111/2022_3분기보고서_01_재무상태표_20221111.txt"));
    }
}
