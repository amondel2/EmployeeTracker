package com.attend

import grails.converters.JSON

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
