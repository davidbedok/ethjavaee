package com.ericsson.diskstore.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.diskstore.ejbserviceclient.domain.DiskStub;
import com.ericsson.diskstore.persistence.entity.Disk;

@Local
public interface DiskConverter {

	DiskStub to(Disk disk);

	List<DiskStub> to(List<Disk> disks);

}
