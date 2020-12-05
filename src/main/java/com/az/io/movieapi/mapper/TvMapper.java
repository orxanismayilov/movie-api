package com.az.io.movieapi.mapper;

import com.az.io.movieapi.dto.*;
import com.az.io.movieapi.model.*;
import com.az.io.movieapi.projections.TvProjection;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TvMapper {

    public static TvDTO convertValue(TvProjection projection) {
        TvDTO tvDTO=new TvDTO();
        tvDTO.setImdbId(projection.getImdbId());
        tvDTO.setImdbRating(projection.getImdbRating());
        tvDTO.setLanguages(projection.getEpisodes().get(0)
                .getVideos().stream().map(TvVideoProjection::getLanguage).collect(Collectors.toList()));
        tvDTO.setPosterPath(projection.getPosterPath());
        return tvDTO;
    }

    public static List<TvDTO> convertValue(List<TvProjection> projections) {
        return projections.stream().map(TvMapper::convertValue).collect(Collectors.toList());
    }

    public static TvDetails convertDetails(Tv tv) {
        TvDetails details=new TvDetails();
        details.setImdbId(tv.getImdbId());
        details.setTitle(tv.getName());
        details.setBackdropPath(tv.getBackdropPath());
        details.setImdbRating(tv.getImdbRating());
        details.setOverview(tv.getOverview());
        details.setReleaseDate(tv.getReleaseDate());
        details.setPosterPath(tv.getPosterPath());
        details.setTrailerPath(tv.getTrailerPath());
        details.setNumberOfSeasons(tv.getSeasons().size());
        details.setSeasons(mapSeasonDetail(tv.getSeasons()));
        details.setGenres(tv.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        details.setCast(tv.getCast().stream().sorted(Comparator.comparingInt(Cast::getOrder)).limit(5).map(Cast::getName).collect(Collectors.toList()));
        details.setCompanies(tv.getNetworks().stream().map(TvNetwork::getName).collect(Collectors.toList()));
        details.setCountries(tv.getCountries().stream().map(Country::getName).collect(Collectors.toList()));
        details.setDirector(tv.getCrew().stream()
                .filter(crew -> crew.getJob().equals("Director"))
                .map(Crew::getName).collect(Collectors.toList()));
        return details;
    }

    private static SeasonDTO mapSeasonDetail(Season season){
        SeasonDTO seasonDTO=new SeasonDTO();
        seasonDTO.setId(season.getId());
        seasonDTO.setNumber(season.getSeasonNumber());
        seasonDTO.setEpisodes(mapEpisodeDetail(season.getEpisodes()));
        return seasonDTO;
    }

    private static List<SeasonDTO> mapSeasonDetail(Set<Season> seasons){
        return seasons.stream().map(TvMapper::mapSeasonDetail).collect(Collectors.toList());
    }

    private static EpisodeDetail mapEpisodeDetail(Episode episode){
        EpisodeDetail episodeDetail=new EpisodeDetail();
        episodeDetail.setTitle(episode.getName());
        episodeDetail.setNumber(episode.getEpisodeNumber());
        episodeDetail.setAirDate(episode.getAirDate());
        episodeDetail.setBackdropPath(episode.getStillPath());
        episodeDetail.setOverview(episode.getOverview());
        episodeDetail.setImdbRating(episode.getImdbRating());
        episodeDetail.setVideoUrl(episode.getVideos().iterator().next().getVideoPath());
        episodeDetail.setSubtitles(mapSubtitle(episode.getSubtitleList()));
        return episodeDetail;
    }

    private static List<EpisodeDetail> mapEpisodeDetail(Set<Episode> episodes){
        return episodes.stream().map(TvMapper::mapEpisodeDetail).collect(Collectors.toList());
    }

    private static TvSubtitleDetail mapSubtitle(TvSubtitle subtitle){
        TvSubtitleDetail detail=new TvSubtitleDetail();
        detail.setLang(subtitle.getLanguage());
        detail.setPath(subtitle.getPath());
        return detail;
    }

    private static List<TvSubtitleDetail> mapSubtitle(Set<TvSubtitle> subtitles){
        return subtitles.stream().map(TvMapper::mapSubtitle).collect(Collectors.toList());
    }
}
