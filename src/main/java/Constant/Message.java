package Constant;

/**
 * @author theco2de
 * @version 1.0
 * @since 1.0
 */
public enum Message {

    CAR_NAME_ERROR("[ERROR] 자동차 이름은 5자 이하만 가능합니다.");
    
    private final String value;

    Message(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
