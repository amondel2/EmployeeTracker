package myapp

import com.attend.Day
import com.attend.Employee
import com.attend.EmployeeDay
import com.attend.Role
import com.attend.User
import com.attend.UserRole

class BootStrap {

    def employeeService

    def init = { servletContext ->
        Role r = Role.findOrCreateByAuthority('ROLE_ADMIN');
        r.save()
        User u = User.findByUsername('aaron')
        if(!u ) {
            u = new User()
            u.username = 'aaron'
            u.enabled = true
            u.accountExpired = false
            u.password = 'changeme'
            u.accountLocked = false
            u.save()
        }
        UserRole ur = UserRole.findOrCreateByUserAndRole(u,r)
        ur.save(flush:true)

        def empList = System.getenv("emtrack_empList")?.toString() ?: ( System.getProperty("emtrack_empList")?.toString() ?: '')
        def e
        def employeeList = []
        try {
            empList?.split(':')?.each {
                try {
                    def s = it?.split(',')
                    e = new Employee(firstName: s[0], lastName: s[1])
                    e.save()
                    employeeList.add(e)
                } catch (Exception ex) {   }
            }
        } catch(Exception e2) {}
        if(employeeList?.size() > 0 ) {
            employeeService.createYear(2019)
        }

    }
    def destroy = {
    }
}
