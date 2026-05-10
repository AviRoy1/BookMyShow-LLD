package com.example.BMS.BookMyShow.Design.dto;

import lombok.Data;

import java.util.List;

@Data
public class BlockSeatsRequestDto {
    private long showId;
    private List<Long> seatIds;
    private long userId;
}
