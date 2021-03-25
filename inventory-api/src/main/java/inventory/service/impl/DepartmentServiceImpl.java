package inventory.service.impl;


import inventory.api.v1.model.*;
import inventory.entity.Department;
import inventory.repository.DepartmentRepository;
import inventory.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public OfindAllDept findAllDept(IfindAllDept input) {

        List<Department> departments = departmentRepository.findAll();

        return OfindAllDept.returnSuccess(departments,"");
    }


}
