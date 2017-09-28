package com.yll.demo.socket;

import com.yll.sharde.util.ReleaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author：linlin.yang
 * @date：2017/9/12 10:56
 */
public class GreetingServer extends Thread{
    private static final Logger logger = LoggerFactory.getLogger(GreetingServer.class);
    private ServerSocket serverSocket;

    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000000);
    }

    @Override
    public void run() {
        while (true) {
            Socket server = null;
            try {
                logger.info("waiting for client on port " + serverSocket.getLocalPort());
                server = serverSocket.accept();

                //接收消息
                logger.info("connected to " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());
                logger.info(in.readUTF());

                //发送消息
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF("thank you for connecting to " + server.getLocalSocketAddress()+"\ngoodbye");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }finally {
                ReleaseUtil.close(server);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new GreetingServer(8087);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
