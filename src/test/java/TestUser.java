import cn.kgc.bean.Address;
import cn.kgc.bean.User;
import cn.kgc.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestUser {



    SqlSession sqlSession=null;
    @Before
    public void before(){
        InputStream inputStream = TestUser.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
         sqlSession = sqlSessionFactory.openSession();

    }


    @Test
    public void test(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getByUserName("周星驰");
        /*System.out.println(list.get(0).toString());
        System.out.println(list.get(1).toString());*/
        System.out.println(list.size());
    }

    @Test
    public void test1(){
        User user=new User();
        user.setUserName("周星驰");
        user.setUserRole(1);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getByUserNameandByuserRole(user);
        System.out.println(list.get(0).toString());
    }

    @Test
    public void test2(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map=new HashMap<String,Object>();
        map.put("userName","周星驰");
        map.put("userRole",1);
        List<User> users = mapper.getByUserNameByMap(map);
        System.out.println(users.get(0).toString());
    }

    @Test
    public void  test3(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("userName","周星驰");
        map.put("userRole",1);
        List<User> list = mapper.getByUserNameByUserRoleMap(map);
        System.out.println(list.get(0).toString());
    }

    @Test
    public void  test4(){

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserCode("test001");
        user.setUserName("test");
        user.setUserPassword("123");
        user.setGender(1);
        try {
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2011-05-09"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhone("1234567899");
        user.setAddress("北大资源");
        user.setUserRole(1);
        user.setCreatedBy(1);
        user.setCreationDate(new Date());
        Integer integer = mapper.addUser(user);
        System.out.println(integer);

        //手动提交
        sqlSession.commit();
    }


    @Test
    public void test5(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setId(46);
        user.setUserCode("test002");
        user.setUserName("mlok");
        Integer integer = mapper.updateUser(user);
        System.out.println(integer);
        sqlSession.commit();
    }

    @Test
    public void test6(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.delete(46);
        sqlSession.commit();
    }

    @Test
    public void test7(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer updatepwd = mapper.updatepwd(45, "123456789");
        System.out.println(updatepwd);
        sqlSession.commit();
    }


    @Test
    public void test8(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getByUserRole(2);
        for (User user:userList){
            System.out.println(user.getRole().getRoleName());
        }
    }

    @Test
    public void test9(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getByUserIdandAddress(1);
        for (User user:userList){
            for (Address address:user.getAddresses()){
                System.out.println(address.getAddressDesc());
            }
        }
    }

    @Test
    public void test10(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserRoleanduserName(null, "周星驰");
        System.out.println(userList.get(0).toString());
    }

    @Test
    public void test11(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user=new User();
        user.setId(45);
        user.setUserCode("jun");
        Integer updatesettrim = mapper.updatesettrim(user);
        System.out.println(updatesettrim);
        sqlSession.commit();
    }


    @Test
    public void test12(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer[] user={1,2,3};
        List<User> foreach_array = mapper.get_foreach_array(user);
        System.out.println(foreach_array.size());
    }

    @Test
    public void test13(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*List<Integer> integers = Arrays.asList(1, 2);*/
        List<Integer> wj=new ArrayList<Integer>();
        wj.add(1);
        wj.add(2);
        List<User> foreach_list = mapper.get_foreach_list(wj);
        System.out.println(foreach_list.size());
    }

    @Test
    public void test14(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("gender",1);
        map.put("userRoles",Arrays.asList(1, 2));

        mapper.get_foreach_map(map);
    }

    @Test
    public void test15(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.get_foreach_choose(null,null,1);
    }

    @Test
    public void test16(){
        Page<Object> page = PageHelper.startPage(2, 5);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getBypages();
        for (int i = 0; i <userList.size(); i++) {
            System.out.println(userList.get(i));
        }
    }
    @After
    public void after(){

        sqlSession.close();
    }

}
