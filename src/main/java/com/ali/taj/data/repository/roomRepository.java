package com.ali.taj.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ali.taj.data.entity.Room;

@Repository
public interface roomRepository  extends CrudRepository<Room,Long> {
	Room findByNumber(int number)										;
}
