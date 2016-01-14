package com.lang71.server.converter;

import com.lang71.server.dto.WordDTO;
import com.lang71.server.mybatis.alias.Word;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordConverter extends AbstractConverter<Word, WordDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Word convertToEntity(WordDTO dto) {
        return modelMapper.map(dto, Word.class);
    }

    @Override
    public WordDTO convertToDTO(Word entity) {
        return modelMapper.map(entity, WordDTO.class);
    }
}
