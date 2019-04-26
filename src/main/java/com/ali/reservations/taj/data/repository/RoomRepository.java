package com.ali.reservations.taj.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ali.reservations.taj.data.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {
	Room findByNumber(int number)										;
}
