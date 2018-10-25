import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.URL;

/**
 * @Author: liuhao
 * @Date: 2018/9/7 14:38
 * @Description:
 **/
public class ResourceReader {
    private static final String subMoudlePropertiesFile = "jquery.min.map";//jar下的配置文件
    private static final String innerPropertiesFile = "user.properties";//内部配置文件

    public static void main(String[] args) throws InterruptedException {
//        loadJarFileByConfiguration();
        loadLocalFile();
        loadJarFileByResource();
        loadJarFileByFile();
    }

    /**
     * 通过File类去加载jar包的资源文件
     */
    public static void loadJarFileByFile() {
        System.out.println("----------loadJarFileByFile---- begin------------");
        URL resource = ResourceReader.class.getClassLoader().getResource(subMoudlePropertiesFile);

        try {
            String path = resource.toString();
            System.out.println(path);
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String s = "";
            while ((s = br.readLine()) != null)
                System.out.println(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------loadJarFileByFile---- end------------\n\n");
    }

//    /**
//     * 通过apache configuration包读取配置文件
//     */
//    private static void loadJarFileByConfiguration() {
//        System.out.println("----------loadJarFileByConfiguration---- begin------------");
//        try {
//            Configuration configuration = new PropertiesConfiguration(subMoudlePropertiesFile);
//            Iterator<String> keys = configuration.getKeys();
//            while (keys.hasNext()) {
//                String next = keys.next();
//                System.out.println("key:" + next + "\tvalue:" + configuration.getString(next));
//            }
//        } catch (ConfigurationException e) {
//            e.printStackTrace();
//        }
//        System.out.println("----------loadJarFileByConfiguration---- end------------\n\n");
//    }

    /**
     * 通过类加载器去的getResource方法去读取
     */
    public static void loadJarFileByResource() {
        System.out.println("----------loadJarFileByResource---- begin------------");
        URL resource = ResourceReader.class.getClassLoader().getResource(subMoudlePropertiesFile);

        try {
            String path = resource.toString();
            System.out.println(path);
            InputStream is = resource.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = "";
            while ((s = br.readLine()) != null)
                System.out.println(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------loadJarFileByResource---- end------------\n\n");

    }

    /**
     * 读取当前工程中的配置文件
     */
    public static void loadLocalFile() {
        System.out.println("----------loadLocalFile---- begin------------");
        String path = ResourceReader.class.getClassLoader().getResource(innerPropertiesFile).getPath();

        try {
            System.out.println(path);
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                System.out.println("strLine:" + strLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------loadLocalFile---- end------------\n\n");
    }
}
