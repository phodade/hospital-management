package org.dnyanyog.service;

import java.util.List;

import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.CaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repo.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CaseManagementServiceImpl implements CaseManagementService {

  @Autowired CaseRepository caseRepo;

  @Autowired CaseResponse caseResponse;

  @Autowired CaseRequest caseRequest;

  public CaseResponse addCases(CaseRequest caseRequest) {
    Cases cases =
        Cases.getInstanace()
            .setPatientNameInEnglish(caseRequest.getPatientNameInEnglish())
            .setPatientNameInMarathi(caseRequest.getPatientNameInMarathi())
            .setCaseNumber(caseRequest.getCaseNumber())
            .setExaminationDate(caseRequest.getExaminationDate())
            .setSymptoms(caseRequest.getSymptoms())
            .setPrescription(caseRequest.getPrescription());
    try {
      caseRepo.save(cases);
    } catch (Exception e) {
      e.printStackTrace();
    }
    caseResponse.setStatus(ResponseCode.ADD_CASES_SUCCESS.getStatus());
    caseResponse.setMessage(ResponseCode.ADD_CASES_SUCCESS.getMessage());

    return caseResponse;
  }

  public CaseResponse searchCases(long patientId) {
    List<Cases> receivedCases = caseRepo.findByPatientId(patientId);
    if (receivedCases.isEmpty()) {
      caseResponse.setStatus(ResponseCode.SEARCH_CASES_FAILURE.getStatus());
      caseResponse.setMessage(ResponseCode.SEARCH_CASES_FAILURE.getMessage());
    } else {
      Cases cases = receivedCases.get(0);
      caseResponse.setPatientNameInEnglish(cases.getPatientNameInEnglish());
      caseResponse.setPatientNameInMarathi(cases.getPatientNameInMarathi());
      caseResponse.setCaseNumber(cases.getCaseNumber());
      caseResponse.setExaminationDate(cases.getExaminationDate());
      caseResponse.setSymptoms(cases.getSymptoms());
      caseResponse.setPrescription(cases.getPrescription());

      caseResponse.setStatus(ResponseCode.SEARCH_CASES_SUCCESS.getStatus());
      caseResponse.setMessage(ResponseCode.SEARCH_CASES_SUCCESS.getMessage());
    }
    return caseResponse;
  }

  public String deleteCases(long patientId) {
    caseRepo.deleteById(patientId);
    return "Case for patientId " + patientId + "  is deleted";
  }
}