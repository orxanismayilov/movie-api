package com.az.io.movieapi.controller;

import com.az.io.movieapi.dto.MovieDTO;
import com.az.io.movieapi.model.ResponseObject;
import com.az.io.movieapi.model.User;
import com.az.io.movieapi.service.HistoryService;
import com.az.io.movieapi.service.UserService;
import com.az.io.movieapi.service.WatchListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WatchListService watchService;
    private final HistoryService historyService;

    @GetMapping("/{userId}")
    public ResponseObject<User>  getUser(@PathVariable String userId){
        return ResponseObject.getMovieSuccessResponse(userService.getUser(userId));
    }

    @PostMapping
    public ResponseObject<?> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseObject<?> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @GetMapping("/{userId}/watchlist")
    public ResponseObject<List<MovieDTO>> getUserWatchListResponseObject(@PathVariable String userId){
        return ResponseObject.getMovieSuccessResponse(watchService.getUserWatchList(userId));
    }

    @PostMapping("/{userId}/watchlist/{movieId}")
    public ResponseObject<?> addMovieUserWatchlist(@PathVariable String movieId,@PathVariable String userId) {
        watchService.addMovieWatchList(userId,movieId);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @DeleteMapping("/{userId}/watchlist/{movieId}")
    public  ResponseObject<?> deleteMovieFromWatchList(@PathVariable String movieId,@PathVariable String userId){
        watchService.deleteMovieFromWatchList(userId,movieId);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @GetMapping("/{userId}/history")
    public ResponseObject<List<MovieDTO>> getUserHistoryResponseObject(@PathVariable String userId){
        return ResponseObject.getMovieSuccessResponse(historyService.getUserHistory(userId));
    }

    @PostMapping("/{userId}/history/{movieId}")
    public ResponseObject<?> addMovieUserHistory(@PathVariable String movieId,@PathVariable String userId) {
        historyService.addMovieHistory(userId,movieId);
        return ResponseObject.getMovieSuccessResponse(null);
    }

    @DeleteMapping("/{userId}/history/{movieId}")
    public  ResponseObject<?> deleteMovieFromHistory(@PathVariable String movieId,@PathVariable String userId){
        historyService.removeMovieHistory(userId,movieId);
        return ResponseObject.getMovieSuccessResponse(null);
    }
}
