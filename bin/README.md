# 헤렌 과제
## 1. Quiz
```
package com.example.demo.quiz;
```
- 문제를 제대로 이해하지 못해 풀지 못했습니다. 정수로 구성된 리스트를 받아서 문자열로 바꾸는 것이 숫자 3개는 ~로 표시하고 4번째 숫자는 일반 출력하라는 것인지..
------------

## 2. API
### TABLE 구조

![img1](https://user-images.githubusercontent.com/19643716/131803631-fd467176-2f40-4c1d-b62c-5c3d84f4b5ef.JPG)

![img](https://user-images.githubusercontent.com/19643716/131803630-b2b47457-bbc7-41de-8100-e06376630b30.JPG)

![img2](https://user-images.githubusercontent.com/19643716/131803634-549007cc-bc48-47d0-9f1f-d11ada68a3df.JPG)

```
샵 조회 
Controller

@GetMapping("/shop")
	public Map<String, Object> getShopInfo(){
		return testService.getShopInfo();
	}
  
Service

public Map<String, Object> getShopInfo(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Employee> employees = testDao.getEmployeeList();
		
		map.put("shop", testDao.getShopInfo());
		map.put("employee", employees);
		return map;
	}
  
Mapper

 <select id="getShopInfo" resultType="com.example.demo.dto.Shop">
       SELECT *
          FROM shop
        WHERE shop_seq = #{shopSeq}
</select>
<select id="getEmployeeList" resultType="map">
		SELECT *
		FROM phone
		WHERE shop_seq = #{shopSeq}
</select>
```
#### 고유 아이디 하나로 샵의 정보를 불러오고 샵에 등록된 직원 정보를 불러오기 위해서 Service에서 map을 통해 먼저 샵의 정보를 가져오고, 
#### List를 통해 샵에 등록된 직원의 정보를 가져오도록 했습니다.

```
샵 삭제
Controller

@DeleteMapping("/deleteShop")
	public boolean deleteShop(@RequestParam Shop seq) {
		
		return testService.deleteShop(seq);
}

Service

public boolean deleteShop(Shop p) {
		return testDao.deleteShop(p);
}
	
	
dao

public boolean deleteShop(Shop s) {
		try {
			sqlSession.delete(ns + "deleteShop", s);
		}catch(Exception e) {
			return false;
		}
		return true;
}
	
Mapper

<delete id="deleteShop">
		DELETE FROM shop
		WHERE shop_seq=#{shopSeq}
</delete>
```

#### 마찬가지로 고유 아이디를 전달 받아 샵과 관련된 정보를 삭제하기 위해 shopSeq를 @RequestParam으로 받도록 했습니다.

```
샵 등록, 직업 등록

Controller

@GetMapping("/insertShop")
	@ResponseBody
	public boolean insertShop(@RequestBody Shop shop) {
		return testService.insertShop(shop);
}
	
@GetMapping("/insertEmployee")
	@ResponseBody
	public boolean insertEmployee(@RequestBody Employee employee) {
		return testService.insertEmployee(employee);
}

Service

public boolean insertShop(Shop s) {
	return testDao.addShop(s);
}
	
public boolean insertEmployee(Employee e) {
	return testDao.addEmployee(e);
}

dao

public boolean addShop(Shop s) {
	if(s == null) {
		return false;
	}
	try {
		sqlSession.insert(ns + "insertShop", s); 
	} catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
}
	
public boolean addEmployee(Employee employee) {
	if(employee == null) {
		return false;
	}
	try {
		sqlSession.insert(ns + "insertShop", employee); 
	} catch(Exception e) {
		e.printStackTrace();
		return false;
	}
	return true;
}

Mapper

<insert id="insertShop" useGeneratedKeys="true" keyProperty="model">
	INSERT INTO shop (name, business_number, tel, kakao_id, status) VALUES
	(#{name}, #{business_number}, #{tel}, #{kakao_id}, #{status})
</insert>
<insert id="insertEmployee" useGeneratedKeys="true" keyProperty="model">
	INSERT INTO employee (name, tel, kakao_id, status, shop_seq) VALUES
	(#{name}, #{tel}, #{kakao_id}, #{status}, #{shop_seq})
</insert>
```

#### 등록을 하기 위한 정보를 받아서 샵과 직원을 등록할 수 있도록 하였습니다.
#### 사업자번호가 중복됐을 때 409 에러가 발생하도록 하는 것은 구현하지 못했습니다.
