package com.ali.reservations.taj.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ali.reservations.taj.business.domain.RoomReservation;
import com.ali.reservations.taj.business.service.ReservationService;

@RestController
@RequestMapping(value="/api")
public class ReservationServiceController {

	@Autowired
	private ReservationService reservationService;

	@RequestMapping(method= RequestMethod.GET, value = "/reservations/{date}")
	public List<RoomReservation> getAllReservationForDate(@PathVariable(value = "date") String dateString){
return this.reservationService.getRoomReservationsForDate(dateString);
	}
}
