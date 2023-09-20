package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Role;

public interface RoleDaoInterface {

    public Role findRoleByName(String theRoleName);
    
}
