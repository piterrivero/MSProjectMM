package com.order.service.feignclients;

import com.order.service.model.DiscDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "disc-service")
@RequestMapping("/disc")
public interface DiscFeignClient {

    @GetMapping("/{id}")
    public DiscDTO findById(@PathVariable("id") long id);

}
