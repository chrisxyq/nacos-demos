import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author chrisxu
 * @create 2021-10-31 11:13
 * Ctrl + Alt + L：格式化代码
 * ctrl + Alt + T：代码块包围
 * ctrl + Y：删除行
 * ctrl + D：复制行
 * alt+上/下：移动光标到上/下方法
 * ctrl+shift+/：注释多行
 */
public class SimpleDemoMain {
    /**
     * 使用nacos client远程获取nacos服务上的配置信息
     *
     * @param args
     */
    public static String dataId = "nacos-simple-demo.yaml";
    public static String group = "DEFAULT_GROUP";
    public static String serverAddr = "127.0.0.1:8848";
    public static String nameSpace = "d9c60b7c-9553-4c31-82e6-383509839cfa";

    public static void main(String[] args) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        //不配置默认是public命名空间
        properties.put("namespace", nameSpace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(dataId, group, 5000);
        System.out.println(config);
    }

    /**
     * 监听服务端的修改
     * "namespace" 区分大小写！
     */
    @Test
    public void test() throws NacosException, InterruptedException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace", nameSpace);
        ConfigService configService = NacosFactory.createConfigService(properties);
        System.out.println(configService.getConfig(dataId, group, 5000));
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            /**
             * 配置有变化时，获取通知
             * @param s
             */
            @Override
            public void receiveConfigInfo(String s) {
                System.out.println(s);
            }
        });
        while (true){
            Thread.sleep(2000);
        }
    }
}
