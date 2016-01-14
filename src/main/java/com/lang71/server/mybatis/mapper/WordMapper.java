package com.lang71.server.mybatis.mapper;

import com.lang71.server.mybatis.alias.Word;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WordMapper {

    @Insert("INSERT INTO words (russian, english) VALUES (#{russian}, #{english})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Long create(Word word);

    @Select("SELECT * FROM words WHERE id=#{wordId}")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="russian", property="russian"),
            @Result(column="english", property="english")
    })
    Word findWordById(Long wordId);

    @Select("SELECT * FROM words")
    @Results({
            @Result(id=true, column="id", property="id"),
            @Result(column="russian", property="russian"),
            @Result(column="english", property="english")
    })
    List<Word> findWords();

    @Update("UPDATE words SET russian=#{russian}, english=#{english} WHERE id=#{id}")
    Long update(Word word);

    @Delete("DELETE FROM words WHERE id=#{wordId}")
    Long deleteWord(Long wordId);

}
