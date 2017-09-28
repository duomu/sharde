package com.yll.demo.socket;

import com.yll.sharde.util.ReleaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * @author：linlin.yang
 * @date：2017/9/12 10:46
 */
public class GreetingClient {
    public static Logger logger = LoggerFactory.getLogger(GreetingClient.class);
    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        Integer port = 8087;
        Socket client = null;
        try {
            logger.info("connecting to " + serverName + " on port " + port);
            client = new Socket(serverName, port);

            //发送消息
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            //接收消息
            DataInputStream in = new DataInputStream(client.getInputStream());
            logger.info("Server says " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ReleaseUtil.close(client);
        }
    }
}
