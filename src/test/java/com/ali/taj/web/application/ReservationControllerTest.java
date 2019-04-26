package com.ali.taj.web.application;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ali.reservations.taj.business.domain.RoomReservation;
import com.ali.reservations.taj.business.service.ReservationService;
import com.ali.reservations.taj.data.entity.Reservation;
import com.ali.reservations.taj.web.application.ReservationController;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)

public class ReservationControllerTest {

	@MockBean
	private ReservationService reservationService;
	@Autowired
	private MockMvc mockMvc;

	private static  final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void getReservations() throws Exception{
		Date date = DATE_FORMAT.parse("2017-01-01");
		List<RoomReservation> mockRoomReservationList = new ArrayList<>();
		RoomReservation  mockRoomReservation = new RoomReservation();
		mockRoomReservation.setDate(date);
		mockRoomReservation.setFirstName("Junit");
		mockRoomReservation.setLastName("Test");
		mockRoomReservation.setRoomId(1);
		mockRoomReservation.setRoomNumber("P1");
		mockRoomReservation.setRoomNumber("Test");
		mockRoomReservation.setGuestId(1);
		mockRoomReservationList.add(mockRoomReservation);

		given(reservationService.getRoomReservationsForDate(date)).willReturn(mockRoomReservationList);
		this.mockMvc.perform(get("/reservations?date=27-01-01")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Test , Junit")));





	}
}
