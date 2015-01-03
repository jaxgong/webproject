package nagaseiori.interceptor;




public interface CurrentUserAware {
	
	//public void setCurrentUser(UserInfo userInfo);
	
	public void setCurrentUserId(int userId);
	
	public void setBusinessId(String businessId);
}
