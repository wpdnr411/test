package com.example.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.SampleVO;
import com.example.domain.Ticket;


@RestController
@RequestMapping("/sample")
public class SampleController {
	
	// 단순 문자열 반환
	// 기존의 @Controller는 문자열을 반호나하는 경우에는 JSP 파일의 이름으로 처리하지만,
	// @RestController의 경우에는 순수한 데이터가 된다.
	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		System.out.println("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	
	// 객체의 반환
	// defalut 값이 .json인 듯 -> 따로 url에 안 붙여도 됨.
	@GetMapping(value = "/getSample", produces = {MediaType.APPLICATION_JSON_VALUE })
	public SampleVO getSample() {
		
		return new SampleVO(112, "스타", "로드");
		
	}
	
	// 컬렉션 타입의 객체 반환
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		
		// 1부터 10미만까지의 루프를 처리하면서 SampleVO 객체를 만들어서 List<SampleVO>로 만들어 낸다.
		// []로 싸여져 있으면 JSON 형태의 배열 데이터이다.
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + " First", " Last")).collect(Collectors.toList());
		
	}
	
	// 컬렉션 타입의 객체 반환
	// 맵의 경우 '키'와 '값'을 가지는 하나의 객체로 간주된다.
	// 맵을 이용하는 경우에는 '키'에 속하는 데이터는 XML로 변환되는 경우에 태그의 이름이 되기 때문에 문자열을 지정한다.
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	// ResponseEntity 타입
	// 데이터와 함께 HTTP 헤더의 상태 메시지 등을 같이 전달하는 용도로 사용한다.
	// HTTP의 상태 코드와 에러 메시지 등을 함꼐 데이터를 전달할 수 있기 때문에 받는 입장에서는 확실하게 결과를 알 수 있다.
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);	// height의 값이 150보다 작을 경우 502메시지와 데이터가 전달된다.
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	// @PathVariable
	// @PathVariable은 '{}'의 이름을 처리할 때 사용한다.
	// REST 방식에는 URL 자체에 데이터를 식별할 수 있는 정보들을 표현하는 경우가 많으므로 다양한 방식으로 @PathVariable이 사용된다.
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		
		return new String[] { "category : " + cat, "productid : " + pid };
		
	}
	
	// @RequestBody
	// @RequestBody는 전달된 요청(request)의 내용(body)을 이용해서 해당 파라미터의 타입으로 변환을 요구한다.
	// 내부적으로 HttpMessageConverter 타입의 객체들을 이용해서 다양한 포맷의 입력 데이터를 변환할 수 있다.
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {		//@RequestBody는 말 그대로 요청(request)한 내용(body)을 처리하기 때문에 일반적인 파라미터 전달방식을 사용할 수 없다.
		
		System.out.println("convert............ticket" + ticket);
		
		return ticket;
		
	}
}
