package com.mikkeyf.bsmall.util;

import com.mikkeyf.bsmall.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.util
 * @className: HostHolder
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/7 0:07
 * @version: 1.0
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
