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
        def monthList = [['id':0,'test':1],['id':1,'test':2],['id':2,'test':3],['id':3,'test':4],
            ['id':4,'test':5],['id':5,'test':6],['id':6,'test':7],['id':7,'test':8],
            ['id':8,'test':9],['id':9,'test':10],['id':10,'test':11],['id':11,'test':12]]

        render(view:'index',model:[empList:empList,month:month,year:year,day:day,monthList:monthList])
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
