import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.Test;

/**
 * @Author: liuhao
 * @Date: 2018/9/7 15:25
 * @Description:
 **/
public class ResourceTest2 {

    @Test
    public void test1() throws Exception {


        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        //1.从外部jar包文件读取配置文件内容:
        //假设存在文件: E:/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/lib/log4j-1.2.16.jar
        //你可以这样读出jar包中的org/apache/log4j/xml/log4j.dtd文件的内容;
        byte[] data1 = readResource("E:/apache-tomcat-7.0.20/webapps/ROOT", "log4j-1.2.16.jar:org/apache/log4j/xml/log4j.dtd");
        System.out.println("E:/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/lib/log4j-1.2.16.jar包里的org/apache/log4j/xml/log4j.dtd内容如下: ");
        System.out.println(new String(data1));
        System.out.println();
        System.out.println();

        //2.从classes路径读取配置文件内容:
        //假设存在文件: E:/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/conf/log4j.properties,
        //你可以这样读出/conf/log4j.properties文件的内容;
        byte[] data2 = readResource("E:/apache-tomcat-7.0.20/webapps/ROOT", "/conf/log4j.properties");
        System.out.println("E:/apache-tomcat-6.0.18/webapps/ROOT/WEB-INF/classes/conf/log4j.properties的内容如下: ");
        System.out.println(new String(data2));

    }

    /**
     * 从外部jar包或classes类路径下读取配置文件
     *
     * @param applicationRootPath Web应用目录
     * @param className           jar包及其资源文件相对路径或classes类路径下配置文件的相对路径
     * @return 配置文件的2进制内容
     */
    public static byte[] readResource(String applicationRootPath, String className) {
        String[] classNames = className.split(":");
        if (classNames[0].endsWith(".jar")) {
            if (classNames.length == 1) {
                String jarFilePath = applicationRootPath + File.separator + "WEB-INF" + File.separator + "lib" + File.separator + classNames[0];
                return readResourceOfFile(jarFilePath);
            }

            String jarFilePath = applicationRootPath + File.separator + "WEB-INF" + File.separator + "lib" + File.separator + classNames[0];
            return readResourceFromJar(jarFilePath, classNames[1]);
        }

        String classesFilePath = applicationRootPath + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + classNames[0];
        return readResourceOfFile(classesFilePath);
    }

    private static byte[] readResourceOfFile(String className) {
        byte[] arr_result = null;
        InputStream in = null;
        ByteArrayOutputStream fout = new ByteArrayOutputStream(8192);
        try {
            in = new FileInputStream(className);
            BufferedInputStream fin = new BufferedInputStream(in, 8192);

            byte[] data = new byte[8192];
            int count = 0;

            while (count >= 0) {
                count = fin.read(data);
                if (count > 0) {
                    fout.write(data, 0, count);
                }
            }

            arr_result = fout.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) try {
                in.close();
            } catch (Exception ex) {
            }
            if (fout != null) try {
                fout.close();
            } catch (Exception ex) {
            }
        }

        return arr_result;
    }

    private static byte[] readResourceFromJar(String jarFile, String className) {
        byte[] arr_result = null;
        try {
            ZipFile zipFile = new ZipFile(jarFile);
            try {
                ZipEntry zipEntry = zipFile.getEntry(className);
                if (zipEntry == null) return null;
                BufferedInputStream fin = new BufferedInputStream(zipFile.getInputStream(zipEntry), 8192);
                ByteArrayOutputStream fout = new ByteArrayOutputStream(8192);
                try {
                    byte[] data = new byte[8192];
                    int count = 0;

                    while (count >= 0) {
                        count = fin.read(data);
                        if (count > 0) {
                            fout.write(data, 0, count);
                        }
                    }
                    arr_result = fout.toByteArray();
                } finally {
                    fin.close();
                    fout.close();
                }
            } finally {
                zipFile.close();
            }
        } catch (Exception e) {
        }

        return arr_result;
    }

}
