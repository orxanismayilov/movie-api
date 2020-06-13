package com.az.io.movieapi;

import com.az.io.movieapi.repo.MovieRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mysql")
class MovieApiApplicationTests {

    private final MovieRepo repo;

    MovieApiApplicationTests(MovieRepo repo) {
        this.repo = repo;
    }

    @Test
    public void test(){
        repo.findAll();
        repo.findAll();
    }
}
