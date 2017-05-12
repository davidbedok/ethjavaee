package com.ericsson.magazine.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.magazine.ejbservice.domain.MagazineStub;
import com.ericsson.magazine.persistence.entity.Magazine;

@Local
public interface MagazineConverter {

	MagazineStub to(Magazine book);

	List<MagazineStub> to(List<Magazine> books);

}
