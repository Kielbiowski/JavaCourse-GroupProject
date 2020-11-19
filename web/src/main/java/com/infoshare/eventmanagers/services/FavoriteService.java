package com.infoshare.eventmanagers.services;

import com.infoshare.eventmanagers.dao.Dao;
import com.infoshare.eventmanagers.dao.EventDao;
import com.infoshare.eventmanagers.dto.EventDto;
import com.infoshare.eventmanagers.dto.UserFavoritesDto;
import com.infoshare.eventmanagers.models.User;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.awt.font.TextAttribute;
import java.util.*;

//To consider scope!!
@RequestScoped
public class FavoriteService {

    @Inject
    Dao<User> userDao;
    @Inject
    EventDao eventDao;

    @Transactional
    public EventDto getFavorite(Integer userId, Integer favoriteId) {
        UserFavoritesDto userFavoritesDto = UserFavoritesDto.toUserFavoritesDto(userDao.getById(userId));
        List<EventDto> favoriteList = userFavoritesDto.getFavoriteList();
        return favoriteList.stream()
                .filter(eventDto -> eventDto.getId().equals(favoriteId))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public List<EventDto> getRange(Integer userId, Integer start, Integer range) {
        UserFavoritesDto userFavoritesDto = UserFavoritesDto.toUserFavoritesDto(userDao.getById(userId));
        List<EventDto> favoriteList = userFavoritesDto.getFavoriteList();
        favoriteList.sort(Comparator.comparing(EventDto::getStartDate));
        return favoriteList.subList(start, range);
    }

    @Transactional
    public void addFavorite(Integer userId, Integer eventId) {
        UserFavoritesDto userFavoritesDto = UserFavoritesDto.toUserFavoritesDto(userDao.getById(userId));
        List<EventDto> favoriteList = userFavoritesDto.getFavoriteList();
        EventDto favorite = EventDto.toEventDto(eventDao.getById(eventId));
        favoriteList.add(favorite);
        userFavoritesDto.setFavoriteList(favoriteList);
        userDao.update(userId, UserFavoritesDto.toUser(userFavoritesDto));
    }

    @Transactional
    public void removeFavorite(Integer userId, Integer favoriteId) {
        UserFavoritesDto userFavoritesDto = UserFavoritesDto.toUserFavoritesDto(userDao.getById(userId));
        List<EventDto> favoriteList = userFavoritesDto.getFavoriteList();
        EventDto favoriteToRemove = favoriteList.stream()
                .filter(eventDto -> eventDto.getId().equals(favoriteId))
                .findFirst()
                .orElse(null);
        favoriteList.remove(favoriteToRemove);
        userFavoritesDto.setFavoriteList(favoriteList);
        userDao.update(userId, UserFavoritesDto.toUser(userFavoritesDto));
    }

    @Transactional
    public boolean isFavorite(Integer userId, Integer eventId){
        UserFavoritesDto userFavoritesDto = UserFavoritesDto.toUserFavoritesDto(userDao.getById(userId));
        List<EventDto> favoriteList = userFavoritesDto.getFavoriteList();
        return favoriteList.stream().anyMatch(eventDto -> eventDto.getId().equals(eventId));
    }

    @Transactional
    public List<EventDto> isFavoriteEventDtoList(List<EventDto> eventDtoList, Integer userId){
        for (EventDto e : eventDtoList) {
            e.setIsFavorite(isFavorite(userId,e.getId()));
        }
        return eventDtoList;
    }
}
