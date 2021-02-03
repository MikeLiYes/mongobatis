package com.github.mikeliyes.mongobatis;

import java.util.List;
import com.mongodb.DBObject;

public interface UserMapper {
    List<DBObject> queryAllUser();
    List<DBObject> queryAllUserPage();
}
