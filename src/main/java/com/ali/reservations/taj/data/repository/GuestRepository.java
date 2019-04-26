package com.ali.reservations.taj.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ali.reservations.taj.data.entity.Guest;

import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

	}
