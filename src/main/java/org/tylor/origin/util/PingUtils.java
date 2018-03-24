package org.tylor.origin.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liubin10
 *		   2017年6月23日
 */
public class PingUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(PingUtils.class);
	static enum PingSelector{
		SEL;
		private Selector selector;
		private PingSelector(){
			 try {
				 setSelector(Selector.open());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public Selector getSelector() {
			return selector;
		}
		public void setSelector(Selector selector) {
			this.selector = selector;
		}
	}
	public static boolean isPingable(String ip,int port){
		InetSocketAddress address = new InetSocketAddress(ip, port);
		
	/*	 SocketChannel sc = null;
		    try {
				sc = SocketChannel.open();
				sc.configureBlocking(true);
	
				boolean connected = sc.connect(address);
				if (connected) {
				    sc.close();
				    return true;
				} else {
				    // Add the new channel to the pending list
				}
				    // Nudge the selector so that it will process the pending list
			}catch(IOException e){
				e.printStackTrace();
			}*/
		    try {
				SocketChannel sc =  SocketChannel.open(address);
				return true;
			} catch (IOException e) {
				LOG.error("设备:{} 连接失败,原因: {}", ip, e.getMessage());
				return false;
			}
	}
	public static void main(String[] args) {
		System.out.println(PingUtils.isPingable("10.18.34.115",2888));
	}

}
