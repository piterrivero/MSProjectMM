package com.band.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.band.service.model.DiscDTO;

@FeignClient(name = "disc-service")
@RequestMapping("/disc")
public interface DiscFeignClient {

	@PostMapping()
	public DiscDTO saveDisc(@RequestBody DiscDTO disc);
	
	@GetMapping("/band/{idBand}")
	public List<DiscDTO> listDiscsByIdBand(@PathVariable("idBand") long idBand);
	
}
