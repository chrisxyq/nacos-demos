import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author chrisxu
 * @create 2021-10-31 14:26
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class PasswordEncoderUtil {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}
