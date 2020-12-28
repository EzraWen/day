local key = "rate:limit:" .. KEYS[1]  --限流的Key
local limit = tonumber(ARGV[1])
local time =  tonumber(ARGV[2])
local current = tonumber(redis.call('get',key) or "0")
if current + 1 > limit then
    return 0
else
    redis.call('INCRBY',key,1)
    redis.call('expire',key,time)
    return current +1
end
