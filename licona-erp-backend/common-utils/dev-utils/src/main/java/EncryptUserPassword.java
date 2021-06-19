import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author licona
 */
public class EncryptUserPassword {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("superadmin-password"));
        System.out.println(new BCryptPasswordEncoder().encode("liconamoyi-password"));
        System.out.println(new BCryptPasswordEncoder().encode("useradmin-password"));
    }
}
