package com.attend

import org.apache.commons.lang.builder.HashCodeBuilder
import grails.gorm.DetachedCriteria

class EmployeeDay implements Serializable {

    private static final long serialVersionUID = 1

    Employee emp
    Day day
    WorkType type
    Date timeIn
    Date timeOut
    String ident

    EmployeeDay(Day u, Employee r) {
        this()
        day = u
        emp = r
    }

    @Override
    String toString(){
        return this.emp.toString() + " " + this.day.toString()
    }

    @Override
    boolean equals(other) {
        if (!(other instanceof EmployeeDay)) {
            return false
        }

        other.day?.id == day?.id && other.emp?.id == emp?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (emp) builder.append(emp.id)
        if (day) builder.append(day.id)
        builder.toHashCode()
    }

    static EmployeeDay get(long dayId, long empId) {
        criteriaFor(dayId, empId).get()
    }

    static boolean exists(long dayId, long empId) {
        criteriaFor(dayId, empId).count()
    }

    private static DetachedCriteria criteriaFor(long dayId, long empId) {
        EmployeeDay.where {
            day == Day.load(dayId) &&
            emp == Employee.load(empId)
        }
    }

    EmployeeDay create(Day user, Employee role, boolean flush = false) {
        this.day = user
        this.emp = role
        this.save(flush: flush, insert: true)
        this
    }

    static boolean remove(Day u, Employee r, boolean flush = false) {
        if (u == null || r == null) return false

        int rowCount = EmployeeDay.where { day == u && emp == r }.deleteAll()

        if (flush) { EmployeeDay.withSession { it.flush() } }

        rowCount
    }

    static void removeAll(Day u, boolean flush = false) {
        if (u == null) return

        EmployeeDay.where { day == u }.deleteAll()

        if (flush) { EmployeeDay.withSession { it.flush() } }
    }

    static void removeAll(Employee r, boolean flush = false) {
        if (r == null) return

        EmployeeDay.where { emp == r }.deleteAll()

        if (flush) { EmployeeDay.withSession { it.flush() } }
    }

    static belongsTo=[emp:Employee,day:Day]



    static constraints = {
        type nullable:true
        timeIn nullable:true
        timeOut nullable:true
    }

    def beforeValidate(){
        if( ident == null || ident.size() == 0 ) {
            ident = UUID.randomUUID()?.toString().replaceAll("-", "")
        }
    }

    def beforeInsert(){
        if( ident == null || ident.size() == 0 ) {
            ident = UUID.randomUUID()?.toString().replaceAll("-", "")
        }
    }

    static mapping = {
        id composite: ['day', 'emp']
        version false
    }
}
