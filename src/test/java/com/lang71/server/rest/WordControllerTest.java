package com.lang71.server.rest;


import com.lang71.server.converter.WordConverter;
import com.lang71.server.dto.WordDTO;
import com.lang71.server.mybatis.alias.Word;
import com.lang71.server.service.word.WordService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static com.lang71.server.util.Util.*;

public class WordControllerTest {

    @InjectMocks
    private WordController controller;

    @Mock
    private WordService wordService;

    @Mock
    private WordConverter converter;


    private MockMvc mockMvc;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnListOfWords() throws Exception {

        List<Word> words = createWords(1L);
        List<WordDTO> dtos = createDTOs(1L);

        when(wordService.all()).thenReturn(words);
        when(converter.convertToDTO(words)).thenReturn(dtos);

        mockMvc.perform(get("/words/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        verify(wordService, times(1)).all();
        verify(converter, times(1)).convertToDTO(words);

    }

    @Test
    public void shouldReturnOneWord() throws Exception {

        Word word = createWord(3L);
        WordDTO wordDTO = createWordDTO(3L);

        when(wordService.one(3L)).thenReturn(word);
        when(converter.convertToDTO(word)).thenReturn(wordDTO);

        mockMvc.perform(get("/words/one/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.russian", is("Слово3")))
                .andExpect(jsonPath("$.english", is("Word3")))
                .andExpect(jsonPath("$.id", is(3)));

        verify(wordService, times(1)).one(3L);
        verify(converter, times(1)).convertToDTO(word);

    }

    @Test
    public void shouldCreateWord() throws Exception {

        Word word = createWord(7L);
        WordDTO wordDTO = createWordDTO(7L);

        when(converter.convertToEntity(any(WordDTO.class))).thenReturn(word);
        when(wordService.create(word)).thenReturn(word);
        when(converter.convertToDTO(word)).thenReturn(wordDTO);

        mockMvc.perform(post("/words/new")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(wordDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.russian", is("Слово7")))
                .andExpect(jsonPath("$.english", is("Word7")))
                .andExpect(jsonPath("$.id", is(7)));

        verify(converter, times(1)).convertToEntity(any(WordDTO.class));
        verify(wordService, times(1)).create(word);
        verify(converter, times(1)).convertToDTO(word);
    }

    @Test
    public void shouldUpdateWord() throws Exception {

        Word word = createWord(5L);
        WordDTO wordDTO = createWordDTO(5L);

        when(converter.convertToEntity(any(WordDTO.class))).thenReturn(word);
        when(wordService.update(word)).thenReturn(word);
        when(converter.convertToDTO(word)).thenReturn(wordDTO);

        mockMvc.perform(put("/words/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(wordDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.russian", is("Слово5")))
                .andExpect(jsonPath("$.english", is("Word5")))
                .andExpect(jsonPath("$.id", is(5)));

        verify(converter, times(1)).convertToEntity(any(WordDTO.class));
        verify(wordService, times(1)).update(word);
        verify(converter, times(1)).convertToDTO(word);
    }

    @Test
    public void shouldDeleteWord() throws Exception {

        mockMvc.perform(delete("/words/delete/9")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("OK")));

        verify(wordService, times(1)).delete(9L);
    }
}
