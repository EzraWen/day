package com.ezra.feign;

import com.ezra.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SysFeignFallback;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-test")
public interface TestFeign {

    @GetMapping(value = "/test")
    Result runException();
}
