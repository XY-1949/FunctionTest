package net.puge.inside.service.impl;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.google.gson.Gson;
import com.jcraft.jsch.*;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import net.puge.inside.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import net.puge.inside.entity.Fine;
import net.puge.inside.entity.vo.LinuxInfo;
import net.puge.inside.service.FineService;
import net.puge.inside.mapper.FineMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yinsd
 * @since 2022-03-29
 */
@Service
public class FineServiceImpl extends ServiceImpl<FineMapper, Fine> implements FineService {

    @Autowired
    private LinuxInfo linuxInfo;

    /**
     * 清除内网缓存
     * @return
     * @throws
     */
    @Override
    public void clearIntranet() {
        String cmd = "docker restart 92709c359a62";
        //LinuxPage(cmd);
    }

    /**
     * 清除部署缓存
     * @return
     * @throws
     */
    @Override
    public void clearDeploy() {
        String cmd = "rm -rf /home/gitlab-runnner/buils";
        //LinuxPage(cmd);
    }

    /**
     * 在Linux服务器上创建一个文件
     * @return
     */
    @Override
    public void addOneFile() {
        String cmd = "cd /usr/local/test/test && touch demo991.md";
        //LinuxPage(cmd);
        execCmd(cmd,linuxInfo.getUsername(), linuxInfo.getPassword(), linuxInfo.getIp(), linuxInfo.getPort());
    }

    /**
     * 获取所有的用户信息
     * @return
     */
    @Override
    public List<User> getAllUserInfo() {
        return null;
    }


    //public  void LinuxPage(String cmd){
    //
    //    System.out.println("ip:"+linuxInfo.getIp()+"——port:"+linuxInfo.getPort()+"——username:"+linuxInfo.getUsername()+"password:"+linuxInfo.getPassword());
    //
    //    String ip = linuxInfo.getIp();
    //    //String ip = "123.56.191.123";
    //    //用户名
    //    //String username = "root";
    //    String username = linuxInfo.getUsername();
    //    //用户密码
    //    //String password = "YSd99817749,.";
    //    String password = linuxInfo.getPassword();
    //    //需要执行的Linux命令
    //    //String cmd = "cd /usr/local && rm -rf /usr/local/test2001";
    //    //
    //    String chartset = "UTF-8";
    //    Connection connection = null;
    //    Session session = null;
    //    //首先构造一个连接器，传入一个需要登陆的ip地址
    //
    //    try {
    //        connection = new Connection(ip);
    //        connection.connect();//连接
    //        boolean isAuthenticated = connection.authenticateWithPassword(username, password);
    //
    //        if (isAuthenticated) {
    //            System.out.println("连接成功");
    //        } else {
    //            throw new Exception("连接失败");
    //        }
    //        session = connection.openSession();
    //        //执行该命令
    //        session.execCommand(cmd);
    //        //获得标准输出流
    //        InputStream is = new StreamGobbler(session.getStdout());
    //        BufferedReader brs = new BufferedReader(new InputStreamReader(is, chartset));
    //        List result = new ArrayList();
    //        for (String line = brs.readLine(); line != null; line = brs.readLine()) {
    //            result.add(line);
    //            System.out.println("line："+line);
    //        }
    //        if(result.size() ==0) {
    //            System.out.println("result:"+result);
    //        }
    //        session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 1000 * 3600);
    //        //得到脚本运行成功与否的标志 ：0 成功,非0 失败
    //        System.out.println("ExitCode: " + session.getExitStatus());
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    } finally {
    //        if (session != null) {
    //            session.close();
    //        }
    //        if (connection != null) {
    //            connection.close();
    //        }
    //    }
    //}


    /**
     * 远程调用接口，获取所有的用户信息
     * @return
     */
    public static List<User> getAllUser(){
        String url = String.format("http://testnb.puge.net/system/api/feign/userInfoList");
        HttpResponse<List> httpResponse;
        List<User> userList = new ArrayList<>();
        try {
            httpResponse = Unirest.get(url).asObject(List.class);
            userList = httpResponse.getBody();
        } catch (UnirestException e) {
            return userList;
        }
        return userList;
    }




    /**
     *
     * @param command Linux命令
     * @param username  用户名
     * @param passwd
     * @param host
     * @param port
     * @return
     */
    //执行相关命令
    public static String execCmd(String command, String username, String passwd, String host, int port) {

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
                //resultJson = getServerData(host, port, username, passwd, outFilePath);
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

    private static final Logger lg = LoggerFactory.getLogger(FineServiceImpl.class);

    private static com.jcraft.jsch.Session session;

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
}
