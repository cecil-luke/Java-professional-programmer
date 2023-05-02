public interface UserDao{
	boolean exists(String username);
	boolean addUser(String username,String password);
	boolean checkUser(String usernaem,String password);
}