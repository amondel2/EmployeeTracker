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
    String id

    @Override
    String toString(){
        return this.emp.toString() + " " + this.day.toString()
    }

    static belongsTo=[emp:Employee,day:Day]



    static constraints = {
        type nullable:true
        timeIn nullable:true
        timeOut nullable:true
        emp nullable:false, unique: ['day']
        day nullable: false, unique: ['emp']
        type nullable: true
        id display:false
    }

    def beforeValidate(){
        if( id == null || id.size() == 0 ) {
            id = UUID.randomUUID()?.toString().replaceAll("-", "")
        }
    }

    def beforeInsert(){
        if( id == null || id.size() == 0 ) {
            id = UUID.randomUUID()?.toString().replaceAll("-", "")
        }
    }

    static mapping = {
        day cascade: "none"
        emp cascade: "none"
        id generator:'assigned'
        version false


    }
}
