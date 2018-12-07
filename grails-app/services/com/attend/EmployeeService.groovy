package com.attend

import grails.gorm.transactions.Transactional

@Transactional
class EmployeeService {

    def createYear(int year) {
        def emps = Employee.list().collect{
            it
        }
        def errors = []
        def date = new GregorianCalendar(year,11,31);
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
                        } catch (Exception ex) {
                            errors.add(ex.getMessage())
                        }
                    }
                }
            } catch (Exception e) {}
        }
        errors
    }
}
