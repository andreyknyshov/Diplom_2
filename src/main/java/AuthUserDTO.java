import java.util.Random;

public class AuthUserDTO {
    private String email;
    private String name;
    private String password;

    public static String createEmail() {
        Random rand = new Random();
        return String.format("mail%d@yandex.ru", rand.nextInt());
    }

    public AuthUserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public AuthUserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
