package com.attend

import grails.gorm.transactions.Transactional
import grails.orm.HibernateCriteriaBuilder
import org.grails.datastore.mapping.query.api.BuildableCriteria
import org.hibernate.Criteria

@Transactional
class EmployeeService {

    def getEmplLst(Date startDate, Date endDate) {
        BuildableCriteria c = EmployeeDay.createCriteria()
        c.list{
            day {
                between('myday', startDate, endDate)
            }
        }
    }

    def saveEmpDay(result) {
        def res = []
        result?.each{k, v ->
            def emd = EmployeeDay.load(k)
            def success = true
            def msg = "SUCCESS"
            try{
                emd.type = WorkType[v]
                emd.save(flush:true,failOnError:true)
            } catch(Exception e) {
                success = false
                msg = e.getMessage()
            }
            res.add([success,msg])
        }
        res
    }

    def createYear(int year) {
        def emps = Employee.list().collect{
            it
        }
        def errors = []
        def newItems = []
        def date = new GregorianCalendar(year - 1,11,31);
        for(def i=0;i<365;i++) {
            try {
                date.add(Calendar.DAY_OF_MONTH, 1)
                def day = date.get(Calendar.DAY_OF_WEEK)
                if (day == Calendar.SUNDAY || day == Calendar.SATURDAY) {
                    continue
                }
                Day d
                d = Day.findByMyday(date.getTime())
                if (!d) {
                    d = new Day()
                    d.myday = date.getTime()
                    d.save()
                }
                emps.each { emp ->
                    EmployeeDay empr = EmployeeDay.findByDayAndEmp(d, emp)
                    if (!empr) {
                        empr = new EmployeeDay()
                        empr.day = d
                        empr.emp = emp
                        try {
                            empr.save(failOnError: true)
                            newItems.add(empr)
                        } catch (Exception ex) {
                            errors.add(ex.getMessage())
                        }
                    }
                }
            } catch (Exception e) {}
        }
        [errors: errors, newItems: newItems]
    }
}
