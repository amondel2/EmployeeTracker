package com.attend

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class EmployeeDayController {

    static scaffold=EmployeeDay
    def employeeService
    def createYear() {

        withFormat{
            '*' {
                render   employeeService.createYear(params.year?.toString().toInteger()) as JSON
            }
        }


    }
}
