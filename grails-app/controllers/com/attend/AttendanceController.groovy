package com.attend

import grails.converters.JSON
import groovy.json.JsonSlurper

class AttendanceController {

    def index() {
        def today = new GregorianCalendar()
        def month = params.month ? params.int('month') : today.get(Calendar.MONTH)
        def year = params.int('year') ?: today.get(Calendar.YEAR)
        def day = params.int('day') ?: today.get(Calendar.DAY_OF_MONTH)
        def getDates = new GregorianCalendar(year,month,day)
        def empList = EmployeeDay.findAllByDay(Day.findByMyday(getDates.getTime()))
        render(view:'index',model:[empList:empList,month:month,year:year,day:day])
    }

    def save(){
        def res = []
        def p = params
        def slurper = new JsonSlurper()
        def result = slurper.parseText(params.empList)
        result?.each{k, v ->
            def emd = EmployeeDay.findByIdent(k)
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
        withFormat{
            '*' {
                render(res as JSON)
            }
        }
    }
}
