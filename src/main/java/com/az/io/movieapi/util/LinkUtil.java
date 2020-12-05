package com.az.io.movieapi.util;

import com.az.io.movieapi.controller.MovieController;
import com.az.io.movieapi.controller.TvController;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class LinkUtil {

    public static String nextPageForMovies(Pageable pageable) {
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(MovieController.class).getMovies(pageable)))
                .append("?page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageSearchMovies(String q,Pageable pageable){
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(MovieController.class).searchMovies(q,pageable)))
                .append("&page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageMoviesByGenre(List<String> genreName, Pageable pageable) {
        StringBuilder link=new StringBuilder();

        link.append(linkTo(methodOn(MovieController.class).getMoviesByGenre(genreName,pageable)))
                .append("&page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageSimilarMovies(String movieId,Pageable pageable) {
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(MovieController.class).getSimilarMovies(movieId,pageable)))
                .append("?page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageMoviesByLang(String lang,Pageable pageable) {
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(MovieController.class).getMoviesByLanguage(lang,pageable)))
                .append("?page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageForTvs(Pageable pageable) {
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(TvController.class).getTvs(pageable)))
                .append("?page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageTvsByGenre(List<String> genreName, Pageable pageable) {
        StringBuilder link=new StringBuilder();

        link.append(linkTo(methodOn(TvController.class).getTvsByGenre(genreName,pageable)))
                .append("&page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    public static String nextPageSimilarTvs(String movieId,Pageable pageable) {
        StringBuilder link=new StringBuilder();
        link.append(linkTo(methodOn(TvController.class).getSimilarTvs(movieId,pageable)))
                .append("?page=").append(pageable.next().getPageNumber())
                .append("&size=").append(pageable.getPageSize());
        getPageableParams(link,pageable);
        return link.toString();
    }

    private static void getPageableParams(StringBuilder link,Pageable pageable) {
        pageable.getSort().forEach(order -> link
                .append("&sort=")
                .append(order.getProperty())
                .append(",")
                .append(order.getDirection()));
    }
}
