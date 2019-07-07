
package com.srmasset.thcepdetails.rest;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.srmasset.thcepdetails.model.cep.Cep;

@RestController
@CacheConfig(cacheNames="mycache")
public class ApiStateComponent {

	
	@CrossOrigin
	@Cacheable
	@GetMapping(value = "/buscacep/{CEP}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cep buscaCep(@PathVariable("CEP") String CEP){
		RestTemplate rest = new RestTemplate();
		String uri = "https://zuul.trusthub.com.br/orchestrator/v1/obter-endereco-por-cep/{CEP}";
		return rest.getForObject(uri, Cep.class,CEP);
		
	}
	
	@CrossOrigin
	@PostMapping(value = "/enviacep", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cep enviaCep(@RequestBody Cep cep){
		RestTemplate rest = new RestTemplate();
		String uri = "https://zuul.trusthub.com.br/orchestrator/v1/obter-endereco-por-cep/{CEP}";
		HttpEntity<Cep> request = new HttpEntity<Cep>(new Cep());
		return rest.postForObject(uri, request, Cep.class);
		
	}
		

}
