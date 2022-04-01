package net.puge.test;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yinsd
 * @version 1.0
 * Only code and time last forever
 * @date 2022/3/23 10:18 PM
 */
public class rmFile {


    /**

     * 本机调用linux上的命令

     * java 远程执行Shell命令-通过ganymed-ssh2连接

     * JAVA利用SSH2登录LINUX并执行命令_运维_yezhuanxu的博客-CSDN博客

     * Java 远程执行 Linux 的命令

     */
    public static void main(String[] args) {
        //ip地址

        //LinuxInfo linuxInfo = new LinuxInfo();
        //String password1 = linuxInfo.getPassword();
        //System.out.println(password1);


        String ip = "123.56.191.123";
        //用户名
        String username = "root";
        //用户密码
        String password = "YSd99817749,.";
        //需要执行的Linux命令
        String cmd = "cd /usr/local && rm -rf /usr/local/test2001";
        //
        String chartset = "UTF-8";

        Connection connection = null;

        Session session = null;

        //首先构造一个连接器，传入一个需要登陆的ip地址

        try {
            connection = new Connection(ip);

            connection.connect();//连接

            boolean isAuthenticated = connection.authenticateWithPassword(username, password);

            if (isAuthenticated) {
                System.out.println("连接成功");
            } else {
                throw new Exception("连接失败");
            }
            session = connection.openSession();
            //执行该命令
            session.execCommand(cmd);
            //获得标准输出流
            InputStream is = new StreamGobbler(session.getStdout());
            BufferedReader brs = new BufferedReader(new InputStreamReader(is, chartset));
            List result = new ArrayList();
            for (String line = brs.readLine(); line != null; line = brs.readLine()) {
                result.add(line);
                System.out.println(line);
            }
            if(result.size() ==0) {
                System.out.println(result);
            }
            session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 1000 * 3600);
            //得到脚本运行成功与否的标志 ：0 成功,非0 失败
            System.out.println("ExitCode: " + session.getExitStatus());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {

                    connection.close();
                }
        }
    }
}
