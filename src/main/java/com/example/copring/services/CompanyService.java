package com.example.copring.services;

import com.example.copring.dto.CompanyDTO;
import com.example.copring.models.Company;
import com.example.copring.repos.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public List<Company> getCompanies(int page, int size) {
        return companyRepository.findAllBy(PageRequest.of(page,size)).getContent();
    }

    public Company getCompanyById(long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public Company saveCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDTO, company);

        if (companyRepository.findByName(company.getName()).isPresent()
        || companyRepository.findByPhone(company.getPhone()).isPresent()) return null;

        Company c = companyRepository.save(company);
        return c;
    }

    public Company deleteCompany(long id){
        Company c = companyRepository.findById(id).orElse(null);
        if (c != null) companyRepository.deleteById(id);
        return c;
    }


    public Company updateCompany(long id, CompanyDTO company){
        Company c = companyRepository.findById(id).orElse(null);
        if (c == null) return null;
        BeanUtils.copyProperties(company, c);
        return companyRepository.save(c);
    }

}
