import com.chiho.itchat4java.Shell;
import com.chiho.itchat4java.model.WebInitDO;
import org.junit.Test;

public class SocketTest {

	Shell shell = Shell.getInstance();

	@Test
	public void socketTest() {
		shell.start();
		login();
		while ( true ) {

		}
	}

	private void login() {
		try {
			shell.autoLogin(true, null, false, null, null, null, null);

//			shell.login(false, null, null, () -> System.out.println("loginCallback"), () -> System.out.println("exitCallback"));
//			String uuid = shell.getQRUuid();
//			Assert.assertNotNull(uuid);
//			shell.getQR(uuid, false, null, qrCodeResponse -> {
//				System.out.println("qrCallback");
//			});
//			shell.checkLogin(uuid);
			WebInitDO webInitDO = shell.webInit();
//			shell.showMobileLogin();
//			shell.startReceiving(param -> {
//				System.out.println("exitCallback");
//			});
			shell.getMsg();
//			shell.logout();
//			shell.updateChatroom("userName", true);
//			shell.updateFriend("userName");
//			shell.getContact(true);
//			shell.getFriends(true);
//			shell.getChatrooms(true, true);
//			shell.getMps(true);
//			shell.setAlias("userName", "alias");
//			shell.setPinned("userName", true);
//			shell.addFriend("userName", AddFriendStatusEnum.ADD, "verifyContent", true);
//			shell.getHeadImg("userName", "chatroomUserName", null);
//			List<ContactDO> memberList = new ArrayList<>();
//			ContactDO contactDO = new ContactDO();
//			contactDO.setNickName("nickname");
//			contactDO.setAlias("alias");
//			memberList.add(contactDO);
//			contactDO = new ContactDO();
//			contactDO.setNickName("nickname");
//			contactDO.setAlias("alias");
//			memberList.add(contactDO);
//			shell.createChatroom(memberList, "topic");
//			shell.setChatroomName("chatroomUserName", "name");
//			shell.deleteMemberFromChatroom("chatroomUserName", memberList);
//			shell.addMemberIntoChatroom("chatroomUserName", memberList, true);
//			shell.sendRawMsg("msgType", "content", "toUserName");
//			shell.sendMsg("msg", "toUserName");
//			shell.uploadFile("src/main/resources/QR.png", true, false, "toUserName");
//			shell.sendFile("src/main/resources/QR.png", "toUserName", "mediaId");
//			shell.sendImage("src/main/resources/QR.png", "toUserName", "mediaId");
//			shell.sendVideo("src/main/resources/QR.png", "toUserName", "mediaId");
//			shell.send("message", "toUserName", "mediaId");
//			shell.revoke("msgId", "toUserName", "localId");
//			shell.dumpLoginStatus(null);
//			shell.loadLoginStatus(null, param -> {
//				System.out.println("loginCallback");
//			}, param -> {
//				System.out.println("exitCallback");
//			});
//			shell.autoLogin(null, null, null, null, null, null, null);
//			shell.configuredReply();
//			shell.msgRegister("msgType", true, false, false);
//			shell.run(true, true);
//			shell.searchFriends("name", "userName", "remarkName", "wechatAccount");
//			shell.searchChatrooms("name", "userName");
//			shell.searchMps("name", "userName");
//			shell.searchMps("name", "userName");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
