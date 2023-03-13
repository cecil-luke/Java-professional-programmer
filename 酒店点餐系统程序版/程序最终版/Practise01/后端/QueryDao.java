import java.util.*;
public interface QueryDao{
	List<Map<String,Object>> queryDetailByOrderId(String did);

	List<Map<String,Object>> queryDetailByUsername(String username);

	List<Map<String,Object>> queryDetailByTime(String start,String end);
}