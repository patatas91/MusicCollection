package com.hiberus.music_collection.services;


import com.hiberus.music_collection.domain.Style;
import com.hiberus.music_collection.repository.StyleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StyleService {

    @Autowired
    private StyleDao styleDao;

    public List<Style> getAllStyle() {
        return styleDao.getAllStyle();
    }

}