package com.goparty.xmpp;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.net.SocketFactory;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Session;
import org.jivesoftware.smack.packet.Message.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SmackXMPPTest {

	private Connection connection;

	private ConnectionConfiguration config;

	private final static String server = "goparty";

	private final void fail(Object o) {
		if (o != null) {
			System.out.println(o);
		}

	}

	private final void fail(Object o, Object... args) {
		if (o != null && args != null && args.length > 0) {
			String s = o.toString();
			for (int i = 0; i < args.length; i++) {
				String item = args[i] == null ? "" : args[i].toString();
				if (s.contains("{" + i + "}")) {
					s = s.replace("{" + i + "}", item);
				} else {
					s += " " + item;
				}
			}
			System.out.println(s);
		}

	}

	@Before
	public void init() {
		try {
			// connection = new XMPPConnection(server);
			// connection.connect();
			config = new ConnectionConfiguration(server, 5222);
			config.setCompressionEnabled(true);
			config.setSASLAuthenticationEnabled(true);
			config.setDebuggerEnabled(false);
			// config.setReconnectionAllowed(true);
			// config.setRosterLoadedAtLogin(true);
			connection = new XMPPConnection(config);
			connection.connect();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		fail(connection);
		fail(connection.getConnectionID());

	}

	@After
	public void destory() {
		if (connection != null) {
			connection.disconnect();
			connection = null;
		}
	}

	@Test
	public void testConfig() {
		fail("PKCS11Library: " + config.getPKCS11Library());
		fail("ServiceName: {0}", config.getServiceName());
		fail("TruststorePassword: {0}", config.getTruststorePassword());
		fail("TruststorePath: {0}", config.getTruststorePath());
		fail("TruststoreType: {0}", config.getTruststoreType());
		SocketFactory socketFactory = config.getSocketFactory();
		fail("SocketFactory: {0}", socketFactory);
		
		try {
		    fail("createSocket: {0}", socketFactory.createSocket("localhost",  3333));
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

	}

	@Test
	public void testConnection() {
		AccountManager accountManager = connection.getAccountManager();
		for (String attr : accountManager.getAccountAttributes()) {
			fail("AccountAttribute: {0}", attr);
		}
		fail("AccountInstructions: {0}",accountManager.getAccountInstructions());
		fail("isConnected:", connection.isConnected());
		fail("isAnonymous:", connection.isAnonymous());
		fail("isAuthenticated:", connection.isAuthenticated());
		fail("isSecureConnection:", connection.isSecureConnection());
		fail("isUsingCompression:", connection.isUsingCompression());
	}

	@Test
	public void testAccountManager() {
		AccountManager accountManager = connection.getAccountManager();
		for (String attr : accountManager.getAccountAttributes()) {
			fail("AccountAttribute: {0}", attr);
		}

		fail("AccountInstructions: {0}",accountManager.getAccountInstructions());
		fail("supportsAccountCreation: {0}",accountManager.supportsAccountCreation());

		try {
			accountManager.createAccount("boy", "boy");
			accountManager.changePassword("123456");
		} catch (XMPPException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testUser() {
		try {
			connection.login("ahu", "123456");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		/** 获取当前登陆用户 */
		fail("User:", connection.getUser());
		/** 所有用户组 */
		Roster roster = connection.getRoster();
		/** 好友用户组，你可以用Spark添加用户好友，这样这里就可以查询到相关的数据 */
		Collection<RosterEntry> rosterEntiry = roster.getEntries();
		Iterator<RosterEntry> iter = rosterEntiry.iterator();
		while (iter.hasNext()) {
			RosterEntry entry = iter.next();
			fail("Groups: {0}, Name: {1}, Status: {2}, Type: {3}, User: {4}",
					entry.getGroups(), entry.getName(), entry.getStatus(),
					entry.getType(), entry);
		}

		fail("-------------------------------");
		/** 未处理、验证好友，添加过的好友，没有得到对方同意 */
		Collection<RosterEntry> unfiledEntries = roster.getUnfiledEntries();
		iter = unfiledEntries.iterator();
		while (iter.hasNext()) {
			RosterEntry entry = iter.next();
			fail("Groups: {0}, Name: {1}, Status: {2}, Type: {3}, User: {4}",
					entry.getGroups(), entry.getName(), entry.getStatus(),
					entry.getType(), entry);
		}

	}

	@Test
	@SuppressWarnings("static-access")
	public void testPacket() {
		try {
			connection.login("ahu", "123456");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		// Packet packet = new Data(new DataPacketExtension("jojo@" + server, 2,
		// "this is a message"));
		// connection.sendPacket(packet);
		/**
		 * 更改用户状态，available=true表示在线，false表示离线，status状态签名；当你登陆后，
		 * 在Spark客户端软件中就可以看到你登陆的状态
		 */
		Presence presence = new Presence(Presence.Type.available);
		presence.setStatus("Q我吧33333333333");
		connection.sendPacket(presence);
		Session session = new Session();
		String sessid = session.nextID();
		connection.sendPacket(session);
		/** Type.chat 表示聊天，groupchat多人聊天，error错误，headline在线用户； */
		Message message = new Message("test@" + server, Type.chat);
		// Message message = new Message(sessid, Type.chat);
		message.setBody("h!~ jojo, I'am is hoojo!");
		connection.sendPacket(message);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testChatManager() {
	
		try {
			connection.login("ahu", "123456");
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		Presence presence = new Presence(Presence.Type.available);
		presence.setStatus("Q我吧");
		connection.sendPacket(presence);
		/** 获取当前登陆用户的聊天管理器 */
		ChatManager chatManager = connection.getChatManager();
		/** 为指定用户创建一个chat，MyMessageListeners用于监听对方发过来的消息 */
		Chat chat = chatManager.createChat("test@" + server,new MyMessageListeners());

		try {
			/** 发送消息 */
			chat.sendMessage("h!~ jojo……");
			/** 用message对象发送消息 */
			Message message = new Message();
			message.setBody("message");
			message.setProperty("color", "red");
			chat.sendMessage(message);
		} catch (XMPPException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000 * 30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * <b>function:</b> 消息监听器，用户监听对方发送的消息，也可以想对方发送消息
	 * 
	 */

	class MyMessageListeners implements MessageListener {

		public void processMessage(Chat chat, Message message) {
			try {
				/** 发送消息 */
				chat.sendMessage("dingding……" + message.getBody());
			} catch (XMPPException e) {
				e.printStackTrace();
			}

			/** 接收消息 */
			fail("From: {0}, To: {1}, Type: {2}, Sub: {3}", message.getFrom(),
					message.getTo(), message.getType(), message.toXML());

			/*
			 * Collection<Body> bodys = message.getBodies();
			 * 
			 * for (Body body : bodys) {
			 * 
			 * fail("bodies[{0}]", body.getMessage());
			 * 
			 * }
			 * 
			 * //fail(message.getLanguage());
			 * 
			 * //fail(message.getThread());
			 * 
			 * //fail(message.getXmlns());
			 */

			fail("body: ", message.getBody());
		}
	}

}