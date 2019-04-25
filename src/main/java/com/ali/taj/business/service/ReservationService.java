
package com.ali.taj.business.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ali.taj.business.domain.RoomReservation;
import com.ali.taj.data.entity.Guest;
import com.ali.taj.data.entity.Reservation;
import com.ali.taj.data.entity.Room;
import com.ali.taj.data.repository.GuestRepository;
import com.ali.taj.data.repository.ReservationRepository;
import com.ali.taj.data.repository.RoomRepository;

@Service
public class ReservationService {
	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final ReservationRepository reservationRepository;


	@Autowired
	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	public List<RoomReservation> getRoomReservationsForDate(Date date){
		Iterable<Room>  rooms= this.roomRepository.findAll();
		Map<Long,RoomReservation> roomReservationMap=new HashMap<>();
		rooms.forEach(room->{
			RoomReservation roomReservation = new RoomReservation();
			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getNumber());
			roomReservationMap.put(room.getId(),roomReservation);
		});
		Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
		if(null!=reservations){
			reservations.forEach(reservation -> {
				Optional<Guest> guestResponse = this.guestRepository.findById(reservation.getId());
				if(guestResponse.isPresent()){
					Guest guest = guestResponse.get();
					RoomReservation roomReservation = roomReservationMap.get(guest.getId());
					roomReservation.setDate(date);
					roomReservation.setFirstName(guest.getFirstName());
					roomReservation.setLastName(guest.getLastName());
					roomReservation.setGuestId(guest.getId());
								}
			});
		}

		List<RoomReservation> roomReservations = new ArrayList<>();
		for(Long roomId: roomReservationMap.keySet() ){
			roomReservations.add(roomReservationMap.get(roomId));
		}
		return roomReservations;
	}
}
