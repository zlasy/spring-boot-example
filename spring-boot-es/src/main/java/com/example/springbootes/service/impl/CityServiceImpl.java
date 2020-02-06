package com.example.springbootes.service.impl;

import com.example.springbootes.domain.City;
import com.example.springbootes.domain.repository.CityRepository;
import com.example.springbootes.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES 业务逻辑实现类
 */
@Service
public class CityServiceImpl implements CityService {

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);

    // ES 操作类
    @Autowired
    CityRepository cityRepository;

    public Long saveCity(City city) {
        City cityResult = cityRepository.save(city);
        return cityResult.getId();
    }

    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    public List<City> findByDescriptionOrScore(String description, Integer score) {
        return cityRepository.findByDescriptionOrScore(description, score);
    }

    public List<City> findByDescription(String description) {
        return cityRepository.findByDescription(description, pageable).getContent();
    }

    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description, pageable).getContent();
    }

    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description, pageable).getContent();
    }

}
