package com.lang71.server.service.word.impl;

import com.lang71.server.mybatis.alias.Word;
import com.lang71.server.mybatis.mapper.WordMapper;
import com.lang71.server.service.word.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WordServiceImpl implements WordService {

    @Autowired
    private WordMapper wordMapper;

    @Override
    public Word create(Word word) {
        wordMapper.create(word);
        return word;
    }

    @Override
    public List<Word> all() {
        return wordMapper.findWords();
    }

    @Override
    public Word one(Long id) {
        return wordMapper.findWordById(id);
    }

    @Override
    public Word update(Word word) {
        Long count = wordMapper.update(word);
        return word;
    }

    @Override
    public Long delete(Long id) {
        return wordMapper.deleteWord(id);
    }

}
