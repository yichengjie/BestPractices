package java.com.yicj.demo.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class JedisPoolUtil {
/*
    static Logger logger = Logger.getLogger(JedisPoolUtil.class.getName()) ;

    private static JedisSentinelPool pool = null;

    public static Properties getJedisProperties() {
        Properties config = new Properties();
        InputStream is = null;
        try {
            is = JedisPoolUtil.class.getClassLoader().getResourceAsStream("cacheConfig.properties");
            config.load(is);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return config;
    }

    *//**
     * 创建连接池
     *
     *//*
    private static void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        Properties prop = getJedisProperties();
        // 设置最大连接数
        config.setMaxTotal(StringUtil.nullToInteger(prop.getProperty("MAX_ACTIVE")));
        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(StringUtil.nullToInteger(prop.getProperty("MAX_WAIT")));
        // 设置空间连接
        config.setMaxIdle(StringUtil.nullToInteger(prop.getProperty("MAX_IDLE")));
        // jedis实例是否可用
        boolean borrow = prop.getProperty("TEST_ON_BORROW") == "false" ? false : true;
        config.setTestOnBorrow(borrow);
        // 创建连接池
//      pool = new JedisPool(config, prop.getProperty("ADDR"), StringUtil.nullToInteger(prop.getProperty("PORT")), StringUtil.nullToInteger(prop.getProperty("TIMEOUT")));// 线程数量限制，IP地址，端口，超时时间
        //获取redis密码
        String password = StringUtil.nullToString(prop.getProperty("PASSWORD"));

        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.137.128:26379");
        sentinels.add("192.168.137.128:26380");
        sentinels.add("192.168.137.128:26381");
        pool = new JedisSentinelPool(masterName, sentinels, config);
    }

    *//**
     * 在多线程环境同步初始化
     *//*
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    *//**
     * 获取一个jedis 对象
     *
     * @return
     *//*
    public static Jedis getJedis() {
        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    *//**
     * 释放一个连接
     *
     * @param jedis
     *//*
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

    *//**
     * 销毁一个连接
     *
     * @param jedis
     *//*
    public static void returnBrokenRes(Jedis jedis) {
        pool.returnBrokenResource(jedis);
    }


    public static void main(String[] args){
        Jedis jedis=getJedis();

    }*/
}
