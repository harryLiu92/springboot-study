import com.liuhao.springboot.demo.SpringBootMain;
import com.liuhao.springboot.demo.configurationProperties.UserProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: liuhao
 * @Date: 2018/9/5 16:05
 * @Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootMain.class)
public class ApplicationTest {

    @Autowired
    private UserProperties userProperties;

    @Test
    public void logger() {
        System.out.println(userProperties);
    }


    @Test
    public void getResource() {
        // D:\workspace\study\springboot\src\main\resources\static\scripts\jquery.min.js
        // D:\apache-maven-3.2.2\repository\org\webjars\jquery\3.1.1\jquery-3.1.1.jar!\META-INF\resources\webjars\jquery\${version.unsnapshot}\webjars-requirejs.js
        // D:/apache-maven-3.2.2/repository/org/webjars/jquery/3.1.1/jquery-3.1.1.jar!/META-INF/resources/webjars/jquery/3.1.1/jquery.min.map
//        URL url = ApplicationTest.class.getClassLoader().getResource("/META-INF/resources/webjars/jquery/3.1.1/jquery.min.map");
//        System.out.println(url);
        ApplicationTest.class.getClassLoader().getSystemResourceAsStream("jar:file:\\D:\\apache-maven-3.2.2\\repository\\org\\webjars\\jquery\\3.1.1\\jquery-3.1.1.jar!\\META-INF\\resources\\webjars\\jquery\\3.1.1\\jquery.min.map");
        ResourceReader.loadLocalFile();
        ResourceReader.loadJarFileByResource();
        ResourceReader.loadJarFileByFile();
    }

}
