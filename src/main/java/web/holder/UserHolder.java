package web.holder;

import web.entity.TUser;
/**User对象线程存储
 * 这种方法使得用户对象在线程内共享，可以更方便地在应用程序的不同层级中访问用户信息。
 * 但请注意，由于线程局部变量的特性，确保在每个请求的开始和结束时都正确设置和清除用户对象。
 * */
public class UserHolder {
    private static final ThreadLocal<TUser> userThreadLocal = new ThreadLocal<>();

    public static void setUser(TUser user) {
        userThreadLocal.set(user);
    }

    public static TUser getUser() {
        return userThreadLocal.get();
    }

    public static void clearUser() {
        userThreadLocal.remove();
    }
}

