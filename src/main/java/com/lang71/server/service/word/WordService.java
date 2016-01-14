package com.lang71.server.service.word;

import com.lang71.server.mybatis.alias.Word;

import java.util.List;

public interface WordService {

    Word create(Word word);

    List<Word> all();

    Word one(Long id);

    Word update(Word word);

    Long delete(Long id);

}
