package com.tiantiannews.data.bean;

import java.util.List;

public class Explore {

	private Lists list;
	private List<Praise> praise;
	private List<Discuss> discuss;

	public Lists getList() {
		return list;
	}

	public void setList(Lists list) {
		this.list = list;
	}

	public List<Praise> getPraise() {
		return praise;
	}

	public void setPraise(List<Praise> praise) {
		this.praise = praise;
	}

	public List<Discuss> getDiscuss() {
		return discuss;
	}

	public void setDiscuss(List<Discuss> discuss) {
		this.discuss = discuss;
	}

	public class Lists {
		private String id;
		private String chatname;
		private String chatopenid;
		private String chatcontent;
		private String chattime;
		private List<String> chatpic;
		private String facepic;
		private String praisenum;
		private String isable;
		private String abc;

		public String getAbc() {
			return abc;
		}

		public void setAbc(String abc) {
			this.abc = abc;
		}

		public String getIsable() {
			return isable;
		}

		public void setIsable(String isable) {
			this.isable = isable;
		}

		public String getPraisenum() {
			return praisenum;
		}

		public void setPraisenum(String praisenum) {
			this.praisenum = praisenum;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getChatname() {
			return chatname;
		}

		public void setChatname(String chatname) {
			this.chatname = chatname;
		}

		public String getChatcontent() {
			return chatcontent;
		}

		public void setChatcontent(String chatcontent) {
			this.chatcontent = chatcontent;
		}

		public String getChattime() {
			return chattime;
		}

		public void setChattime(String chattime) {
			this.chattime = chattime;
		}

		public List<String> getChatpic() {
			return chatpic;
		}

		public void setChatpic(List<String> chatpic) {
			this.chatpic = chatpic;
		}

		public String getFacepic() {
			return facepic;
		}

		public void setFacepic(String facepic) {
			this.facepic = facepic;
		}

		public String getChatopenid() {
			return chatopenid;
		}

		public void setChatopenid(String chatopenid) {
			this.chatopenid = chatopenid;
		}

	}

	public class Praise {
		private String openname;
		private String openid;
		private String isable;
		private String abc;

		public String getAbc() {
			return abc;
		}

		public void setAbc(String abc) {
			this.abc = abc;
		}

		public String getIsable() {
			return isable;
		}

		public void setIsable(String isable) {
			this.isable = isable;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getOpenname() {
			return openname;
		}

		public void setOpenname(String openname) {
			this.openname = openname;
		}

	}

	public class Discuss {
		private String openname;
		private String discontent;
		private String distime;
		private String openid;
		private String isable;
		private String abc;

		public String getAbc() {
			return abc;
		}

		public void setAbc(String abc) {
			this.abc = abc;
		}

		public String getIsable() {
			return isable;
		}

		public void setIsable(String isable) {
			this.isable = isable;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getOpenname() {
			return openname;
		}

		public void setOpenname(String openname) {
			this.openname = openname;
		}

		public String getDiscontent() {
			return discontent;
		}

		public void setDiscontent(String discontent) {
			this.discontent = discontent;
		}

		public String getDistime() {
			return distime;
		}

		public void setDistime(String distime) {
			this.distime = distime;
		}

	}

//	public void setLikeUsers(Context context, LinearLayout llPraise,
//			TextView tvParise) {
//		if (getPraise() != null && getPraise().size() > 0 && getList() != null
//				&& getList().getPraisenum() != null) {
//			int num = Integer.parseInt(getList().getPraisenum());
//			tvParise.setVisibility(View.VISIBLE);
//			tvParise.setMovementMethod(LinkMovementMethod.getInstance());
//			tvParise.setFocusable(false);
//			tvParise.setLongClickable(false);
//			tvParise.setText(addClickablePart(context, getPraise(), num, true),
//					BufferType.SPANNABLE);
//		} else {
//			tvParise.setText("");
//			tvParise.setVisibility(View.GONE);
//		}
//
//	}

//	private SpannableStringBuilder addClickablePart(final Context context,
//			final List<Praise> praiseUsers, int Num, boolean limit) {
//
//		StringBuilder sbBuilder = new StringBuilder();
//
//		int showCunt = praiseUsers.size();
//		if (limit && showCunt > 5) {
//			showCunt = 5;
//		}
//
//		for (int i = 0; i < showCunt; i++) {
//			sbBuilder.append(praiseUsers.get(i).getOpenname() + "、");
//
//		}
//
//		String likeUsersStr = sbBuilder
//				.substring(0, sbBuilder.lastIndexOf("、")).toString();
//
//		// 第一个赞图标
//		// ImageSpan span = new ImageSpan(AppContext.getInstance(),
//		// R.drawable.ic_unlike_small);
//		SpannableString spanStr = new SpannableString("");
//		// spanStr.setSpan(span, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
//
//		SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
//		ssb.append(likeUsersStr);
//
//		String[] likeUsers = likeUsersStr.split("、");
//
//		if (likeUsers.length > 0) {
//
//			for (int i = 0; i < likeUsers.length; i++) {
//				final String name = likeUsers[i];
//				final int start = likeUsersStr.indexOf(name) + spanStr.length();
//				final int index = i;
//				ssb.setSpan(new ClickableSpan() {
//
//					@Override
//					public void onClick(View widget) {
//						// WarnUtils.toast(context, name);
//						for (Praise parise : praiseUsers) {
//							if (parise.getOpenname().equals(name)) {
//								String id = parise.getOpenid();
//								String able = parise.getIsable();
//								if (BaseApplication.user == null
//										|| BaseApplication.user.getOpenid() == null
//										|| "".equals(BaseApplication.user
//												.getOpenid())) {
//									WarnUtils.toast(context, "请先登录!");
//									ActivityUtils.to(context,
//											LoginActivity.class);
//									return;
//								}
//								if (!StringUtils.isEmpty(id)
//										&& !StringUtils.isEmpty(able)) {
//									if (able.equals("1")) {
//										WarnUtils.toast(context, "不可以与该用户聊天哦!");
//									} else {
//										if (able.equals("0")) {
//											String abc = "";
//											if (!StringUtils.isEmpty(parise
//													.getAbc())) {
//												abc = parise.getAbc();
//											}
//											if (!BaseApplication.user
//													.getOpenid().equals(abc)) {
//												RongIM.getInstance()
//														.setMessageAttachedUserInfo(
//																true);
//												RongIM.getInstance()
//														.startConversation(
//																context,
//																ConversationType.PRIVATE,
//																id, name);
//											} else {
//												WarnUtils.toast(context,
//														"自己不可以与自己聊天哦!");
//											}
//										}
//										if (able.equals("2")) {
//											if (!BaseApplication.user
//													.getOpenid().equals(id)) {
//												RongIM.getInstance()
//														.setMessageAttachedUserInfo(
//																true);
//												RongIM.getInstance()
//														.startConversation(
//																context,
//																ConversationType.PRIVATE,
//																id, name);
//											} else {
//												WarnUtils.toast(context,
//														"自己不可以与自己聊天哦!");
//											}
//										}
//									}
//								}
//
//							}
//						}
//					}
//
//					@Override
//					public void updateDrawState(TextPaint ds) {
//						super.updateDrawState(ds);
//						ds.setColor(context.getResources().getColor(
//								R.color.actionBar)); // 设置文本颜色
//						// 去掉下划线
//						ds.setUnderlineText(false);
//					}
//
//				}, start, start + name.length(), 0);
//			}
//		}
//		if (likeUsers.length < Num) {
//			ssb.append("等");
//			int start = ssb.length();
//			String more = Num + "人";
//			ssb.append(more);
//			// ssb.setSpan(new ClickableSpan() {
//			//
//			// @Override
//			// public void onClick(View widget) {
//			// Bundle bundle = new Bundle();
//			// bundle.putInt(BaseListFragment.BUNDLE_KEY_CATALOG, getId());
//			// UIHelper.showSimpleBack(context,
//			// SimpleBackPage.TWEET_LIKE_USER_LIST, bundle);
//			// }
//			//
//			// @Override
//			// public void updateDrawState(TextPaint ds) {
//			// super.updateDrawState(ds);
//			// // ds.setColor(R.color.link_color); // 设置文本颜色
//			// // 去掉下划线
//			// ds.setUnderlineText(false);
//			// }
//			//
//			// }, start, start + more.length(), 0);
//			return ssb.append("觉得很赞");
//		} else {
//			return ssb.append("觉得很赞");
//		}
//	}

}
