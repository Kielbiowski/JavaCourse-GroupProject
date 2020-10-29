package com.infoshare.eventmanagers.dto;

import com.infoshare.eventmanagers.models.Ticket;
import com.infoshare.eventmanagers.models.jsonModels.JsonTicket;

public class TicketDto {

    private Integer id;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Ticket toTicket(TicketDto ticketDto){
        Ticket ticket = new Ticket();
        ticket.setType(ticketDto.getType());
        return ticket;
    }

    public static TicketDto toTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setType(ticket.getType());
        return ticketDto;
    }
}
