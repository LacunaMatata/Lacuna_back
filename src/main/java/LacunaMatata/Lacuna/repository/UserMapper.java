package LacunaMatata.Lacuna.repository;

import LacunaMatata.Lacuna.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUserId(int userId);
}
