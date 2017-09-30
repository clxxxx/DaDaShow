package com.xzry.takeshow.entity;

/**
 * 
* @ClassName: UserInfo
* @Description: App用户信息
* @author jade-mg
* @date 2017年3月15日 下午4:17:34
*
 */
public class UserInfo {
	public String id; 			// 用户ID
	public String mobile; 		// 手机
	public int sex; 			// 性别，0，保密；1，男；2，女
	public String headerUrl;	 // 上传用户图像
	public String code; 		//Md5值，用户生成唯一标识 token
	public String martUrl; 		// 用户二维码
	public String visitCount; 	//登录次数
	public String personality; 	// 个性描述
	public String nickname; 		//昵称




}