package pers.lintao.eorp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.lintao.eorp.dao.DepartmentDao;
import pers.lintao.eorp.entity.Department;
import pers.lintao.eorp.entity.ExpenseReport;
import pers.lintao.eorp.service.DepartmentService;
import pers.lintao.eorp.service.ExpenseReportService;

import java.util.List;
import java.util.Map;

@SpringBootTest
class EorpApplicationTests {

    @Autowired
    private DepartmentService departmentService;
    
    @Autowired
    private ExpenseReportService expenseReportService;

    @Test
    void contextLoads() {
//        Department department = departmentDao.queryById(1);
        Department department = departmentService.queryById(1);
//        long count = departmentDao.count(null);
        System.out.println(department.toString());
        Map<String, Object> map = expenseReportService.queryExamineList(8);
        List<ExpenseReport> data = (List<ExpenseReport>) map.get("data");
        for (ExpenseReport datum : data) {
            System.out.println(datum);
        }
    }

}
