import com.alibaba.fastjson.JSON;
import com.chiho.itchat4java.Shell;
import com.chiho.itchat4java.exceptions.ItChatException;
import com.chiho.itchat4java.model.ContactDO;
import com.chiho.itchat4java.model.CreateChatroomDO;
import com.chiho.itchat4java.model.MessageDO;
import com.chiho.itchat4java.model.RevokeDO;
import com.chiho.itchat4java.model.StatusResponseDO;
import com.chiho.itchat4java.model.UploadFileDO;
import com.chiho.itchat4java.utils.QRCodeTools;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketTest {

	static Shell shell = Shell.getInstance();
	static String uuid;

	public static void main( String argv[] ) {
		shell.start();

		try {
			shell.autoLogin(true, null, false, null, param -> QRCodeTools.showLoginCode(param.getQrcode()), () -> QRCodeTools.dismissLoginCode(), null);
			Scanner input = new Scanner(System.in);
			String cmd;
			do {
				cmd = input.next();
				switchCmd(cmd);
			} while ( !cmd.equals("#") );
			input.close();
		} catch (ItChatException e) {
			e.printStackTrace();
		}
	}

	private static void switchCmd( String cmd ) {
		try {
			switch ( cmd ) {
//				case "login": {
//					shell.login(false, null, qrCodeResponse -> {
//						uuid = qrCodeResponse.getUuid();
//						System.out.println(JSON.toJSON(qrCodeResponse));
//					}, () -> System.out.println("loginCallback"), () -> System.out.println("exitCallback"));
//				}
//				break;
//				case "getQRUuid": {
//					uuid = shell.getQRUuid();
//					System.out.println("uuid: " + uuid);
//				}
//				break;
//				case "getQR": {
//					shell.getQR(uuid, false, null, qrCodeResponse -> {
//						uuid = qrCodeResponse.getUuid();
//						System.out.println(JSON.toJSON(qrCodeResponse));
//					});
//				}
//				break;
//				case "checkLogin": {
//					String statusCode = shell.checkLogin(uuid);
//					System.out.println("statusCode: " + statusCode);
//				}
//				break;
//				case "webInit": {
//					WebInitDO webInitDO = shell.webInit();
//					System.out.println(JSON.toJSON(webInitDO));
//				}
//				break;
//				case "showMobileLogin": {
//					ShowMobileLoginDO showMobileLoginDO = shell.showMobileLogin();
//					System.out.println(JSON.toJSON(showMobileLoginDO));
//				}
//				break;
//				case "startReceiving": {
//					shell.startReceiving(() -> System.out.println("exitCallback"));
//				}
//				break;
//				case "getMsg": {
//					MsgDO msgDO = shell.getMsg();
//					System.out.println(JSON.toJSON(msgDO));
//				}
//				break;
//				case "logout": {
//					StatusResponseDO response = shell.logout();
//					System.out.println(JSON.toJSON(response));
//				}
//				break;
//				case "updateChatroom": {
//					ContactDO contactDO = shell.updateChatroom("userName", true);
//					System.out.println(JSON.toJSON(contactDO));
//				}
//				break;
//				case "updateFriend": {
//					ContactDO contactDO = shell.updateFriend("userName");
//					System.out.println(JSON.toJSON(contactDO));
//				}
//				break;
//				case "getContact": {
//					shell.getContact(true);
//				}
//				break;
				case "getFriends": {
					List<ContactDO> contactList = shell.getFriends(false);
					System.out.println(JSON.toJSON(contactList));
				}
//				break;
//				case "getChatrooms": {
//					List<ContactDO> contactList = shell.getChatrooms(false, false);
//					System.out.println(JSON.toJSON(contactList));
//				}
//				break;
//				case "getMps": {
//					List<ContactDO> contactList = shell.getMps(false);
//					System.out.println(JSON.toJSON(contactList));
//				}
//				break;
//				case "setAlias": {
//					StatusResponseDO responseDO = shell.setAlias("filehelper", "白痴传输助手");
//					System.out.println(JSON.toJSON(responseDO));
//				}
//				break;
//				case "setPinned": {
//					StatusResponseDO responseDO = shell.setPinned("filehelper", true);
//					System.out.println(JSON.toJSON(responseDO));
//				}
//				break;
//				case "addFriend": {
//					StatusResponseDO responseDO = shell.addFriend("filehelper", AddFriendStatusEnum.ADD, "verifyContent", true);
//					System.out.println(JSON.toJSON(responseDO));
//				}
//				break;
//				case "getHeadImg": {
//					HeadImgDO imgDO = shell.getHeadImg("filehelper", null, null);
//					System.out.println(JSON.toJSON(imgDO));
//				}
//				break;
				case "createChatroom": {
					CreateChatroomDO createChatroomDO = shell.createChatroom(new ArrayList<ContactDO>() {{
						ContactDO contactDO = new ContactDO();
						contactDO.setUserName("@633cead849896f858ed14ae242748fb9");
						add(contactDO);
						contactDO = new ContactDO();
						contactDO.setUserName("filehelper");
						add(contactDO);
					}}, "topic");
					System.out.println(JSON.toJSON(createChatroomDO));
				}
				break;
//				case "setChatroomName": {
//					ModifyChatroomDO modifyChatroomDO = shell.setChatroomName("chatroomUserName", "name");
//					System.out.println(JSON.toJSON(modifyChatroomDO));
//				}
//				break;
//				case "deleteMemberFromChatroom": {
//					ModifyChatroomDO modifyChatroomDO = shell.deleteMemberFromChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>(){{
//						ContactDO contactDO = new ContactDO();
//						contactDO.setUserName("filehelper");
//						add(contactDO);
//					}});
//					System.out.println(JSON.toJSON(modifyChatroomDO));
//				}
//				break;
//				case "addMemberIntoChatroom": {
//					ModifyChatroomDO modifyChatroomDO = shell.addMemberIntoChatroom("@@bd79da9a86aa407fef4faf575dcff5cfde953b449e6af9a181fa5dcf0f7050db", new ArrayList<ContactDO>(){{
//						ContactDO contactDO = new ContactDO();
//						contactDO.setUserName("filehelper");
//						add(contactDO);
//					}}, false);
//					System.out.println(JSON.toJSON(modifyChatroomDO));
//				}
//				break;
				case "sendMsg": {
					MessageDO messageDO = shell.sendMsg("msg", "@633cead849896f858ed14ae242748fb9");
					System.out.println(JSON.toJSON(messageDO));
				}
				break;
				case "uploadFile": {
					UploadFileDO uploadFileDO = shell.uploadFile("src/main/resources/QR.png", true, false, "toUserName");
					System.out.println(JSON.toJSON(uploadFileDO));
				}
				break;
				case "sendFile": {
					MessageDO messageDO = shell.sendFile("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(messageDO));
				}
				break;
				case "sendImage": {
					MessageDO messageDO = shell.sendImage("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(messageDO));
				}
				break;
				case "sendVideo": {
					MessageDO messageDO = shell.sendVideo("src/main/resources/QR.png", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(messageDO));
				}
				break;
				case "send": {
					MessageDO messageDO = shell.send("message", "toUserName", "mediaId");
					System.out.println(JSON.toJSON(messageDO));
				}
				break;
				case "revoke": {
					RevokeDO revokeDO = shell.revoke("5961489659369359345", "filehelper", "15105439316990");
					System.out.println(JSON.toJSON(revokeDO));

				}
				break;
				case "dumpLoginStatus": {
					shell.dumpLoginStatus(null);
				}
				break;
				case "loadLoginStatus": {
					StatusResponseDO statusResponseDO = shell.loadLoginStatus(null, () -> {
						System.out.println("loginCallback");
					}, () -> {
						System.out.println("exitCallback");
					});
					System.out.println(JSON.toJSON(statusResponseDO));
				}
				break;
				case "autoLogin": {
					shell.autoLogin(true, null, false, null, param -> QRCodeTools.showLoginCode(param.getQrcode()), () -> QRCodeTools.dismissLoginCode(), null);
				}
				break;
				case "searchFriends": {
					List<ContactDO> contactList = shell.searchFriends("name", "userName", "remarkName", "wechatAccount");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "searchChatrooms": {
					List<ContactDO> contactList = shell.searchChatrooms("name", "userName");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
				case "searchMps": {
					List<ContactDO> contactList = shell.searchMps("name", "userName");
					System.out.println(JSON.toJSON(contactList));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
