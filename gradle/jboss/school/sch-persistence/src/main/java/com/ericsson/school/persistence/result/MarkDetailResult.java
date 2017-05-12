package com.ericsson.school.persistence.result;

import java.util.Date;

import com.ericsson.school.persistence.entity.trunk.Institute;

public class MarkDetailResult {

	private final Institute institute;

	private final Date year;

	private final double averageGrade;

	public MarkDetailResult(Institute institute, Date year, double averageGrade) {
		this.institute = institute;
		this.year = year;
		this.averageGrade = averageGrade;
	}

	public Institute getInstitute() {
		return this.institute;
	}

	public Date getYear() {
		return this.year;
	}

	public double getAverageGrade() {
		return this.averageGrade;
	}

	@Override
	public String toString() {
		return "MarkDetailResult [institute=" + this.institute + ", year=" + this.year + ", averageGrade=" + this.averageGrade + "]";
	}

}
