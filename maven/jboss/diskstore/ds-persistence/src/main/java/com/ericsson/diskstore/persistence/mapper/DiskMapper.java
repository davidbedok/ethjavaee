package com.ericsson.diskstore.persistence.mapper;

import java.util.List;

import com.ericsson.diskstore.persistence.entity.Disk;

public interface DiskMapper {

	int count(String reference);

	int create(Disk disk);

	Disk readById(Integer id);

	Disk readByReference(String reference);

	List<Disk> readAll();

	int update(Disk disk);

	int delete(Integer id);

}
