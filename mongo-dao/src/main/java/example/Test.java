 
package example;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.goparty.data.model.UserData;

 
public class Test {

    EntityManagerFactory factory;
    static String oid;
    
    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.factory = Persistence.createEntityManagerFactory("mongo");
        
        System.out.println("\nTesting find() by Id.\n");
        EntityManager em = test.factory.createEntityManager();
        
        Query query = em.createQuery("Select u from UserData u");
        List<UserData> userList = query.getResultList();
        System.out.println("\nFound UserData: " + userList.size() + "\n");
    	for(UserData user : userList){
			System.out.println(user.getId() + " -- " + user.getNickName());
		} 
    	 	
    	
    	//native
    	query = em.createNativeQuery("db.users.findOne({\"_id\":\"44941bb6-75b1-4950-bc9a-0d111abb9827\"})", UserData.class);
    	UserData ud  = (UserData) query.getSingleResult();    	 
		System.out.println(ud.getId() + " ------------- " + ud.getNickName());
	 
		//don't support
//		Query query2 = em.createNativeQuery("db.users.find();",UserData.class);
//    	List<UserData> userList2 = query2.getResultList();
//    	for(UserData user : userList2){
//			System.out.println(user.getId() + " -- " + user.getNickName());
//		}
		
      
        em.close();
        test.factory.close();
   } 
}
