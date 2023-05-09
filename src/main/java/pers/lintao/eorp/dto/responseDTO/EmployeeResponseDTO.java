package pers.lintao.eorp.dto.responseDTO;

import pers.lintao.eorp.entity.Employee;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: lilintao@163.com
 * @time: 2023/4/30 09时13分 周一
 */
public class EmployeeResponseDTO implements Serializable {

    private static final long serialVersionUID = -8628250903793507613L;

    private Employee employee;

    private List<ActivePositionListResponseDTO> activePositionList ;

    private List<ActiveDepartmentListResponseDTO> activeDepartmentList;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<ActivePositionListResponseDTO> getActivePositionList() {
        return activePositionList;
    }

    public void setActivePositionList(List<ActivePositionListResponseDTO> activePositionList) {
        this.activePositionList = activePositionList;
    }

    public List<ActiveDepartmentListResponseDTO> getActiveDepartmentList() {
        return activeDepartmentList;
    }

    public void setActiveDepartmentList(List<ActiveDepartmentListResponseDTO> activeDepartmentList) {
        this.activeDepartmentList = activeDepartmentList;
    }
}
