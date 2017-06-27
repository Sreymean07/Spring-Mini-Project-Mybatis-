package org.kshrd.repositories;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository {

    @Select("SELECT "
            + "	U.id, "
            + "	U.username, "
            + "	U.password, "
            + "	U.email, "
            + "	U.gender, "
            + "	U.user_hash,"
            + "	S.id as sid,"
            + "	S.device_name "
            + " FROM "
            + "	users U INNER JOIN signupwith S ON U.signupwith_id = S.id")
    @Results(value = {
            @Result(property = "userHash", column = "user_hash"),
            @Result(property = "signUpWith.id", column = "sid"),
            @Result(property = "signUpWith.deviceName", column = "device_name")
    })
    public List<User> findAll();

    @Select("SELECT "
            + "	U.id, "
            + "	U.username, "
            + "	U.password, "
            + "	U.email, "
            + "	U.gender, "
            + "	U.user_hash,"
            + "	S.id as sid,"
            + "	S.device_name "
            + " FROM "
            + "	users U INNER JOIN signupwith S ON U.signupwith_id = S.id"
            + " WHERE U.user_hash = #{user_Hash}")
    @Results(value = {
            @Result(property = "userHash", column = "user_hash"),
            @Result(property = "signUpWith.id", column = "sid"),
            @Result(property = "signUpWith.deviceName", column = "device_name")
    })
    public User search(@Param("user_Hash") String userHash);

    /**
     * Save user to database
     *
     * @param user
     * @return
     */
    @Insert("INSERT INTO users ("
            + "	username, "
            + "	password, "
            + "	email, "
            + "	gender, "
            + "	user_hash, "
            + " signupwith_id"
            + "	) VALUES ("
            + "	#{user.username},"
            + "	#{user.password},"
            + "	#{user.email},"
            + "	#{user.gender},"
            + "	#{user.userHash},"
            + " 2"
            + ")")
    @SelectKey(
            statement = "SELECT last_value FROM users_id_seq",
            keyProperty = "user.id", keyColumn = "last_value",
            before = false,
            resultType = int.class
    )
    public boolean save(@Param("user") User user);

    @Delete("DELETE FROM users WHERE user_hash=#{user_hash}")
    public boolean delete(@Param("user_hash") String userHash);

    @Update("UPDATE users SET "
            + "username=#{user.username},"
            + "password=#{user.password},"
            + "email=#{user.email},"
            + "gender=#{user.gender}"
            + " WHERE user_hash=#{user.userHash}")
            
    public boolean update(@Param("user") User user);

    @Insert("<script>"
            + "	INSERT INTO users ("
            + "		username, "
            + "		password, "
            + "		email, "
            + "		gender"
            + "	) VALUES "
            + " <foreach collection='users' item='user' separator=','>("
            + "	#{user.username},"
            + "	#{user.password},"
            + "	#{user.email},"
            + "	#{user.gender}"
            + "	) "
            + "</foreach>"
            + "</script>")
    public boolean saveBatch(@Param("users") List<User> users);

    @Select("SELECT COUNT(*) FROM users;")
    public int countTotal();

    @Select("SELECT COUNT(*) FROM users WHERE gender='M';")
    public int countMale();

    @Select("SELECT COUNT(*) FROM users WHERE gender='F';")
    public int countFemale();


}
