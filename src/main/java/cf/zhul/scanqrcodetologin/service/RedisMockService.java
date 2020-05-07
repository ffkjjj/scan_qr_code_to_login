package cf.zhul.scanqrcodetologin.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟 redis 中的数据
 */
@Service
public class RedisMockService {

    private static final String DEFAULT_VALUE = "default_value";
    private final ConcurrentHashMap<String, String> redis = new ConcurrentHashMap<>();

    public String get(String key) {
        return redis.get(key);
    }

    public boolean containsValueWithKey(String key) {
        return !DEFAULT_VALUE.equals(redis.get(key));
    }

    public void put(String key, String value) {
        if (value == null) {
            value = DEFAULT_VALUE;
        }
        redis.put(key, value);
    }

    public void delete(String uuid) {
        redis.remove(uuid);
    }
}
