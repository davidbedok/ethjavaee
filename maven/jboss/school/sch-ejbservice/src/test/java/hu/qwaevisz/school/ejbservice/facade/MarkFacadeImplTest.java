package hu.qwaevisz.school.ejbservice.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ericsson.school.ejbservice.converter.MarkConverter;
import com.ericsson.school.ejbservice.domain.MarkDetailStub;
import com.ericsson.school.ejbservice.exception.AdaptorException;
import com.ericsson.school.ejbservice.facade.MarkFacadeImpl;
import com.ericsson.school.persistence.entity.Subject;
import com.ericsson.school.persistence.entity.trunk.Institute;
import com.ericsson.school.persistence.exception.PersistenceServiceException;
import com.ericsson.school.persistence.result.MarkDetailResult;
import com.ericsson.school.persistence.service.MarkService;
import com.ericsson.school.persistence.service.StudentService;
import com.ericsson.school.persistence.service.SubjectService;

public class MarkFacadeImplTest {

	private static final String SUBJECT_NAME = "LoremIpsumSubject";
	private static final Long SUBJECT_ID = 42L;

	@InjectMocks
	private MarkFacadeImpl facade;

	@Mock
	private StudentService studentService;

	@Mock
	private SubjectService subjectService;

	@Mock
	private MarkService markService;

	@Mock
	private MarkConverter markConverter;

	@BeforeMethod
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createListOfMarkDetailsFromSubjectName() throws AdaptorException, PersistenceServiceException {
		final Subject subject = Mockito.mock(Subject.class);
		Mockito.when(this.subjectService.read(SUBJECT_NAME)).thenReturn(subject);
		Mockito.when(subject.getId()).thenReturn(SUBJECT_ID);
		final List<MarkDetailResult> results = new ArrayList<>();
		results.add(new MarkDetailResult(Institute.UNIOBUDA, new Date(), 0));
		results.add(new MarkDetailResult(Institute.BME, new Date(), 0));
		Mockito.when(this.markService.read(SUBJECT_ID)).thenReturn(results);
		final List<MarkDetailStub> stubs = new ArrayList<>();
		final MarkDetailStub neumannStub = Mockito.mock(MarkDetailStub.class);
		stubs.add(neumannStub);
		final int yearKando = 2014;
		final double averageGradeKando = 2.4142;
		stubs.add(new MarkDetailStub(Institute.BME.toString(), yearKando, averageGradeKando));
		Mockito.when(this.markConverter.to(results)).thenReturn(stubs);

		final List<MarkDetailStub> markDetailStubs = this.facade.getMarkDetails(SUBJECT_NAME);

		Mockito.verify(this.markService).read(SUBJECT_ID);

		Assert.assertEquals(markDetailStubs.size(), stubs.size());
		Assert.assertEquals(markDetailStubs.get(0), neumannStub);
		Assert.assertEquals(markDetailStubs.get(1).getInstitute(), Institute.BME.toString());
		Assert.assertEquals(markDetailStubs.get(1).getYear(), yearKando);
		Assert.assertEquals(markDetailStubs.get(1).getAverageGrade(), averageGradeKando);
	}

}
