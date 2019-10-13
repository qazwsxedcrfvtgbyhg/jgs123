package cn.kgc.mapper;

import cn.kgc.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //根据用户名模糊查询
    List<User> getByUserName(String userName);

    //根据用户名模糊查询和用户角色查 ------对象
    List<User> getByUserNameandByuserRole(User user);
    //根据用户名模糊查询和用户角色查 -----map
    List<User> getByUserNameByMap(Map<String,Object>map);

    //根据用户名和用户角色两表查询--map
    List<User> getByUserNameByUserRoleMap(Map<String,Object>map);


    //添加
    Integer addUser(User user);

    //修改
    Integer updateUser(User user);

    //删除
    Integer delete(Integer id);

    //修改密码
    Integer updatepwd(@Param("id") Integer id,@Param("userPassword") String userPassword );


    //根据用户角色查询role表
    List<User> getByUserRole(Integer userRole);

    //根据user表的id查询address表的地址
    List<User> getByUserIdandAddress(Integer id);

    //根据用户角色和用户名模糊查询
    List<User> getUserRoleanduserName(@Param("userRole") Integer userRole,@Param("userName") String userName);

    //修改
    Integer updatesettrim(User user);

    //根据用户角色查询全部---数组形式
    List<User> get_foreach_array(Integer[] userRoles);

    //根据用户角色查询全部---list形式
    List<User> get_foreach_list(List<Integer> userRoles);

    //根据用户角色和性别查询全部--map形式
    List<User> get_foreach_map(Map<String,Object> userRoles);


    //根据用户角色和用户编号
    List<User> get_foreach_choose(@Param("userCode") String userCode,
                                  @Param("userName") String userName,
                                  @Param("userRole") Integer userRole);

    //分页查询
    List<User> getBypages();



}
