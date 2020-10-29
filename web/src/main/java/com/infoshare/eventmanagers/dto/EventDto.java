package com.infoshare.eventmanagers.dto;

import com.infoshare.eventmanagers.models.Event;
import com.infoshare.eventmanagers.models.Place;

import java.time.LocalDateTime;

public class EventDto {


    private Integer id;
    private PlaceDto place;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String url;
    private String attachments;
    private String descShort;
    private String descLong;
    private String categoryId;
    private OrganizerDto organizer;
    private String active;
    private TicketDto ticket;

    public EventDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public String getDescShort() {
        return descShort;
    }

    public void setDescShort(String descShort) {
        this.descShort = descShort;
    }

    public String getDescLong() {
        return descLong;
    }

    public void setDescLong(String descLong) {
        this.descLong = descLong;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public OrganizerDto getOrganizer() {
        return organizer;
    }

    public void setOrganizer(OrganizerDto organizer) {
        this.organizer = organizer;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "EventDto{" +
                "id=" + id +
                ", place=" + place +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", url='" + url + '\'' +
                ", attachments='" + attachments + '\'' +
                ", descShort='" + descShort + '\'' +
                ", descLong='" + descLong + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", organizer=" + organizer +
                ", active='" + active + '\'' +
                ", ticket=" + ticket +
                '}';
    }

    public static Event toEvent(EventDto eventDto){
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setPlace(PlaceDto.toPlace(eventDto.getPlace()));
        event.setStartDate(eventDto.getStartDate());
        event.setEndDate(eventDto.getEndDate());
        event.setUrl(eventDto.getUrl());
        event.setAttachments(eventDto.getAttachments());
        event.setDescShort(eventDto.getDescShort());
        event.setDescLong(eventDto.getDescLong());
        event.setCategoryId(eventDto.getCategoryId());
        event.setOrganizer(OrganizerDto.toOrganizer(eventDto.getOrganizer()));
        event.setTicket(TicketDto.toTicket(eventDto.getTicket()));
        return event;
    }

    public static EventDto toEventDto(Event event){
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setPlace(PlaceDto.toPlaceDto(event.getPlace()));
        eventDto.setStartDate(event.getStartDate());
        eventDto.setEndDate(event.getEndDate());
        eventDto.setUrl(event.getUrl());
        eventDto.setAttachments(event.getAttachments());
        eventDto.setDescShort(event.getDescShort());
        eventDto.setDescLong(event.getDescLong());
        eventDto.setCategoryId(event.getCategoryId());
        eventDto.setOrganizer(OrganizerDto.toOrganizerDto(event.getOrganizer()));
        eventDto.setTicket(TicketDto.toTicketDto(event.getTicket()));
        return eventDto;
    }

}
