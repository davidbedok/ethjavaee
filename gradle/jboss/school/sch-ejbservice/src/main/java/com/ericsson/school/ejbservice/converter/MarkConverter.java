package com.ericsson.school.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import com.ericsson.school.ejbservice.domain.MarkDetailStub;
import com.ericsson.school.ejbservice.domain.MarkStub;
import com.ericsson.school.persistence.entity.Mark;
import com.ericsson.school.persistence.result.MarkDetailResult;

@Local
public interface MarkConverter {

	MarkStub to(Mark mark);

	List<MarkDetailStub> to(List<MarkDetailResult> results);

}
