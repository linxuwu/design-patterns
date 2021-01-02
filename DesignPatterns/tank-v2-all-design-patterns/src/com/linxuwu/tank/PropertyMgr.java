package com.linxuwu.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件可以使程序更灵活，比如坦克数量使用配置文件
 */
public class PropertyMgr {

    private PropertyMgr(){}

    private static class PropertyMgrHolder {
        static final PropertyMgr INSTANCE  = new PropertyMgr();
    }

    public static PropertyMgr getInstance() {
        return PropertyMgrHolder.INSTANCE;
    }

    private static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String key) {
        if(props == null) {
            return null;
        }
        return props.get(key);
    }

    public static void main(String[] args) {

        System.out.println(PropertyMgr.getInstance().get("initTankCount"));
    }
}
