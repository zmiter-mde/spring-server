package com.mde.test.rest;

import com.mde.test.entity.TestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("example")
public class ExampleController {

    private static final int SIZE = 10;

    private static List<TestEntity> entities = new ArrayList<>(SIZE);
    static {
        for (int i = 0; i < SIZE; ++i) {
            entities.add(createTestEntity(i, "Title " + i));
        }
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{id}")
    public TestEntity getOne(@PathVariable(value = "id") Integer id) {
        return entities.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TestEntity> getAll() {
        return entities;
    }

    private static TestEntity createTestEntity(int count, String title) {
        TestEntity res = new TestEntity();
        res.setCount(count);
        res.setTitle(title);
        return res;
    }

}
