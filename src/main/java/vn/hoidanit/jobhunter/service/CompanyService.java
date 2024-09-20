package vn.hoidanit.jobhunter.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.response.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {

    public final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company handleCreateCompany(Company company) {
        return this.companyRepository.save(company);
    }

    public ResultPaginationDTO handleGetCompany(Specification<Company> specification,Pageable pageable){
      Page<Company> PageCompany = this.companyRepository.findAll(specification,pageable);
          ResultPaginationDTO rs = new ResultPaginationDTO();
          ResultPaginationDTO.Meta meta = new ResultPaginationDTO.Meta();

       meta.setPage(pageable.getPageNumber() + 1);
       meta.setPageSize(pageable.getPageSize());
       
       meta.setPages(PageCompany.getTotalPages());
       meta.setTotal(PageCompany.getTotalElements());

       rs.setMeta(meta);
       rs.setResult(PageCompany.getContent());
       
        return  rs;
        
    }

    public Company handleUpdateCompany(Company company){
        Optional<Company> companyOptional = this.companyRepository.findById(company.getId());
        if(companyOptional.isPresent()){
            Company currentCompany = companyOptional.get();
            currentCompany.setLogo(company.getLogo());
            currentCompany.setName(company.getName());
            currentCompany.setDescription(company.getDescription());
            currentCompany.setAddress(company.getAddress());

            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    public void handleDeleteCompany(long id){
        this.companyRepository.deleteById(id);
    }
}
