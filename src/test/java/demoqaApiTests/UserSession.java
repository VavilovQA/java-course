package demoqaApiTests;

public class UserSession {
    private String userId;
    private String token;
    private String expires;

    // Конструктор
    public UserSession(String userId, String token, String expires) {
        this.userId = userId;
        this.token = token;
        this.expires = expires;
    }

    // Геттеры (именно их ищет компилятор)
    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }
}
