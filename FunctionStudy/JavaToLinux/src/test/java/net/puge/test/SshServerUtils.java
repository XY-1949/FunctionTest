package net.puge.test;


import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.UUID;
/**
 * @author Yinsd
 * @version 1.0
 * Only code and time last forever
 * @date 2022/3/23 9:57 PM
 */
public class SshServerUtils {

    public static void main(String[] args) {
        String commend = "cd /usr/local && mkdir test01 ";
        String ip = "123.56.191.123";
        int port = 22;
        String username = "root";
        String password = "YSd99817749,.";
        String outFilePath = "/Users/yinsdsmac/Desktop/test";
        execCmd(commend,username,password,ip,port,outFilePath);
    }

    /**
     * Created by joe强 on 2018/10/23 14:58
     */
        private static final Logger lg = LoggerFactory.getLogger(SshServerUtils.class);
        private static Session session;

        //连接服务器
        private static void connect(String username, String passwd, String host, int port) {
            try {
                JSch jsch = new JSch();
                //获取sshSession
                session = jsch.getSession(username, host, port);
                //添加密码
                session.setPassword(passwd);
                Properties sshConfig = new Properties();
                //严格主机密钥检查
                sshConfig.put("StrictHostKeyChecking", "no");
                session.setConfig(sshConfig);
                //开启sshSession连接
                session.connect();
                lg.info("Server connection successful.");
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }

    /**
     *
     * @param command Linux命令
     * @param username  用户名
     * @param passwd
     * @param host
     * @param port
     * @param outFilePath
     * @return
     */
        //执行相关命令
        public static String execCmd(String command, String username, String passwd, String host, int port, String outFilePath) {

            String resultJson = null;
            ChannelExec channelExec = null;
            if (command != null) {
                try {
                    connect(username, passwd, host, port);
                    channelExec = (ChannelExec) session.openChannel("exec");
                    // 设置需要执行的shell命令
                    channelExec.setCommand(command);
                    lg.info("linux命令:" + command);
                    channelExec.setInputStream(null);
                    channelExec.setErrStream(System.err);
                    channelExec.connect();
                    //读数据
                    resultJson = getServerData(host, port, username, passwd, outFilePath);
                } catch (JSchException e) {
                    e.printStackTrace();
                } finally {
                    if (null != channelExec) {
                        channelExec.disconnect();
                    }
                }
            }
            return resultJson;
        }

    /**
     * ssh上传文件
     * @param host ip地址
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     * @param filePath 文件路径
     * @return
     */
        // 从 服务器 获取 数据
        private static String getServerData(String host, int port, String username,
                                            String password, String filePath) {

            ChannelSftp sftp = null;
            StringBuffer buffer = new StringBuffer();
            try {

                if (!session.isConnected()) {
                    connect(username, password, host, port);
                }

                //获取sftp通道
                Channel channel = session.openChannel("sftp");
                //开启
                channel.connect();
                sftp = (ChannelSftp) channel;
                lg.info("Connected to " + host + ".");
                //获取生成文件流
                InputStream inputStream = sftp.get(filePath);
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
                //关闭流
                inputStream.close();
                in.close();

                lg.info(" 执行结果为: " + buffer.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSchException e) {
                e.printStackTrace();
            } catch (SftpException e) {
                e.printStackTrace();
            } finally {
                if (null != sftp) {
                    sftp.quit();
                }
                closeSession();
            }
            return buffer.toString();
        }


        public static void closeSession() {
            if (session != null) {
                session.disconnect();
            }

        }
        public static String getUUID() {
            UUID uuid = UUID.randomUUID();
            String str = uuid.toString();
            return str.replace("-", "");
        }
    }
