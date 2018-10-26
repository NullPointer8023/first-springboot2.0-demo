package com.ice.firstappdemo.repository;

import com.ice.firstappdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 * @author ice
 * @date 2018/10/25
 */
@Repository
public class UserRepository {
    /**
     * 采用内存型存储
     */
    private final ConcurrentHashMap<Integer,Object> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger isGenerator = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user {@link User} 对象
     * @return 如果成功,返回<code>true</code>，
     *          否则,返回<code>flase</code>
     */
    public boolean save(User user){
        Integer id = isGenerator.incrementAndGet();
        user.setId(id);
        return repository.put(id,user) == null;
    }

    public Collection findAll(){
        return repository.values();
    }


}

