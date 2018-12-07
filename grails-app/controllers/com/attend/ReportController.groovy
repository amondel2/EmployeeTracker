package com.attend
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ReportController {

    def index() {

        def data = [:]
        def idToName = [:]
        def wts = []
        def emps = Employee.list()
        WorkType.each{ wt ->
            wts.add(wt)
            emps.each{ emp ->
                idToName[emp.id] = emp.toString()
                def s  = EmployeeDay.countByEmpAndType(emp,wt)
                //                empNums.add(new Report(wt,s))

                if(!data[emp.id]){
                    data[emp.id] = []
                }
                data[emp.id].add(s)
            }
        }
        //        withFormat{
        //            "*"{
        //                render(data as JSON)
        //            }
        //        }
        render(view:'index',model:[data:data,wts:wts,idToName:idToName])
    }

    def show() {
        def emp = Employee.load(params.id)
        def wts = [:]
        def nom_normal = 0
        def non_normie = 0
        WorkType.each{ wt ->
            def name = wt.toString()
            if(wt != WorkType.Normal) {
                def data = []
                EmployeeDay.findAllByEmpAndType(emp,wt).each{ s ->
                    data.add(s?.day?.myday)
                    non_normie++
                }
                wts[name] = data
            } else {
                nom_normal = EmployeeDay.countByEmpAndType(emp,wt)
            }
        }
        render(view:'show',model:[wts:wts,emp:emp,nom_normal:nom_normal,non_normie:non_normie])
    }
}
