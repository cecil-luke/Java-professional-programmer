public interface OrderDao{
	int queryFoodIdByName(String name);
	int queryUserIdByName(String username);
	boolean addOrder(String orderId,int uid);
	boolean addOrderFood(String orderId,int fid);
}