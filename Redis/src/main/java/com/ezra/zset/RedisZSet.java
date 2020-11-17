package com.ezra.zset;

import com.ezra.constant.SystemConstant;
import com.ezra.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(SystemConstant.API_URL + "/redis/zset")
public class RedisZSet {
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/set")
    public Result set(@RequestParam("key") String key, @RequestBody Map map,@RequestParam("score") Long score ){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(key,map,score);
        return Result.SUCCESS;
    }


    /**
     * 排行榜前10
     */
    @PostMapping("/rank")
    public Result rank(@RequestParam("key") String key){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        Set set = zSetOperations.reverseRange(key,0,9);
        return Result.data(set);
    }

    /**
     * 2000-3000 降序
     */
    @PostMapping("/top")
    public Result top(@RequestParam("key") String key){
        Set set = redisTemplate.opsForZSet().reverseRangeByScore(key, 2000, 3000);
        return Result.data(set);
    }
}
