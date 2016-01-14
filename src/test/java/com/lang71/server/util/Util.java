package com.lang71.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lang71.server.dto.WordDTO;
import com.lang71.server.mybatis.alias.Word;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<WordDTO> createDTOs(Long count) {
        List<WordDTO> words = new ArrayList<>(count.intValue());
        for (long i = 0; i < count; ++i) {
            words.add(createWordDTO(i));
        }
        return words;
    }

    public static  WordDTO createWordDTO(Long num) {
        WordDTO word = new WordDTO();
        word.setId(num.longValue());
        word.setEnglish("Word" + num);
        word.setRussian("Слово" + num);
        return word;
    }

    public static  List<Word> createWords(Long count) {
        List<Word> words = new ArrayList<>(count.intValue());
        for (long i = 0; i < count; ++i) {
            words.add(createWord(i));
        }
        return words;
    }

    public static  Word createWord(Long num) {
        Word word = new Word();
        word.setId(num.longValue());
        word.setEnglish("Word" + num);
        word.setRussian("Слово" + num);
        word.setDescription("Это слово с номером " + num);
        return word;
    }

    public static String toJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            Assert.fail("JSON not generated from " + o);
        }
        return null;
    }
}
