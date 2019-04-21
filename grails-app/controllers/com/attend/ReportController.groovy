package com.attend
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import java.text.SimpleDateFormat

@Secured(["ROLE_ADMIN"])
class ReportController {

    def index() {
        def p = params
        def yearf,endDate

        if(params.week && params.week.toString().trim().size() == 10) {
            yearf = GregorianCalendar.getInstance()
            yearf.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(params.week))
            endDate = yearf
            yearf = yearf.getTime()
            endDate.add(Calendar.DAY_OF_MONTH,6)
            endDate = endDate.getTime()
        } else {
            yearf = params.year && params.year != 'all' ?  new GregorianCalendar( params.year.toInteger(),0,1,0,0,0).getTime() :null
            endDate = yearf ? new GregorianCalendar(params.year.toInteger(),11,31,23,59,59).getTime() : null
        }

        def data = [:]
        def idToName = [:]
        def wts = []
        def emps = Employee.list()
        WorkType.each{ wt ->
            wts.add(wt)
            emps.each{ emp ->
                idToName[emp.id] = emp.toString()
                def criteria = EmployeeDay.createCriteria()
                def s = criteria.list {
                    projections {
                        count()
                    }
                    eq ('emp', emp)
                    eq ('type', wt)
                    if(yearf) {
                        day{
                            between('myday',yearf,endDate  )
                        }
                    }
                }

                if(!data[emp.id]){
                    data[emp.id] = []
                }
                data[emp.id].add(s[0])
            }
        }
        GregorianCalendar cal = GregorianCalendar.getInstance()
        if(yearf instanceof Date) {
            cal.setTime(yearf)
        }
        render(view:'index',model:[week:params.week,data:data,wts:wts,idToName:idToName,yearS:cal.get(Calendar.YEAR), currYear:params.year])
    }

    def show() {
        def yearf,endDate

        if(params.week && params.week.toString().trim().size() == 10) {
            yearf = GregorianCalendar.getInstance()
            yearf.setTime(new SimpleDateFormat("MM/dd/yyyy").parse(params.week))
            endDate = yearf
            yearf = yearf.getTime()
            endDate.add(Calendar.DAY_OF_MONTH,6)
            endDate = endDate.getTime()
        } else {
            yearf = params.year && params.year != 'all' ?  new GregorianCalendar( params.year.toInteger(),0,1,0,0,0).getTime() :null
            endDate = yearf ? new GregorianCalendar(params.year.toInteger(),11,31,23,59,59).getTime() : null
        }
        def emp = Employee.load(params.id)
        def wts = [:]
        def nom_normal = 0
        def non_normie = 0
        WorkType.each{ wt ->
            def name = wt.toString()
            if(wt != WorkType.Normal) {
                def data = []
                EmployeeDay.withCriteria {
                    eq ('emp', emp)
                    eq ('type', wt)
                    if(yearf) {
                        day{
                            between('myday',yearf,endDate)
                        }
                    }
                    order ( 'day', 'desc')
                }?.each{ s ->
                    data.add(s?.day?.myday)
                    non_normie++
                }
                wts[name] = data
            } else {
                def criteria = EmployeeDay.createCriteria()
                def s = criteria.list {
                    projections {
                        count()
                    }
                    eq ('emp', emp)
                    eq ('type', wt)
                    if(yearf) {
                        day{
                            between('myday',yearf, endDate)
                        }
                    }
                    order ( 'day', 'desc')
                }
                nom_normal = s[0]
            }
        }
        GregorianCalendar cal = GregorianCalendar.getInstance()
        if(yearf instanceof Date) {
            cal.setTime(yearf)
        }
        render(view:'show',model:[week:params.week,wts:wts,emp:emp,nom_normal:nom_normal,non_normie:non_normie,yearS:cal.get(Calendar.YEAR), currYear:params.year])
    }
}
