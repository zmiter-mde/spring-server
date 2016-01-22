package com.lang71.server.rest;

import com.lang71.server.converter.WordConverter;
import com.lang71.server.dto.WordDTO;
import com.lang71.server.service.word.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/words")
public class WordController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordController.class);

    @Autowired
    private WordService wordService;

    @Autowired
    private WordConverter converter;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<WordDTO> all() {
        LOGGER.debug("Getting all words");
        return converter.convertToDTO(wordService.all());
    }

    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public WordDTO one(@PathVariable("id") Long id) {
        LOGGER.debug("Getting word with id = {}", id);
        return converter.convertToDTO(wordService.one(id));
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public WordDTO create(@RequestBody WordDTO wordDTO) {
        LOGGER.debug("Creating new word");
        return converter.convertToDTO(wordService.create(converter.convertToEntity(wordDTO)));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public WordDTO update(@RequestBody WordDTO wordDTO) {
        LOGGER.debug("Updating word with id = {}", wordDTO.getId());
        return converter.convertToDTO(wordService.update(converter.convertToEntity(wordDTO)));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) {
        LOGGER.debug("Deleting word with id = {}", id);
        Long count = wordService.delete(id);
        return "{status: \"OK\", count: \"" + count + "\"}";
    }

}
