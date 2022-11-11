package common.util;

import java.util.Objects;

public enum StatementEnum {
    BS("BS"), // 재무상태표 경로
    CF("CF"), // 현금흐름표 경로
    CE("CE"), // 자본변동표 경로
    PL("PL"); // 손익계산서 경로

    private final String dir;
    StatementEnum(String dir) {
        this.dir = Objects.requireNonNull(StatementEnum.class.getClassLoader().getResource("dart/" + dir)).getPath();
    }
    public String path() {return dir;}
}
