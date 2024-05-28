package org.dnyanyog.entity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cases {
  @Id @GeneratedValue @Column private long patientId;

  @Column private String patientNameInEnglish;

  @Column private String patientNameInMarathi;

  @Column private long caseNumber;

  @Column private String examinationDate;

  @Column private String symptoms;

  @Column private String prescription;

  public static Cases getInstanace() {
    return new Cases();
  }

  public long getPatientId() {
    return patientId;
  }

  public Cases setPatientId(long patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getPatientNameInEnglish() {
    return patientNameInEnglish;
  }

  public Cases setPatientNameInEnglish(String patientNameInEnglish) {
    this.patientNameInEnglish = patientNameInEnglish;
    return this;
  }

  public String getPatientNameInMarathi() {
    return patientNameInMarathi;
  }

  public Cases setPatientNameInMarathi(String patientNameInMarathi) {
    this.patientNameInMarathi = patientNameInMarathi;
    return this;
  }

  public long getCaseNumber() {
    return caseNumber;
  }

  public Cases setCaseNumber(long caseNumber) {
    this.caseNumber = caseNumber;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Cases setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public Cases setSymptoms(String symptoms) {
    this.symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return prescription;
  }

  public Cases setPrescription(String prescription) {
    this.prescription = prescription;
    return this;
  }
}